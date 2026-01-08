# Tests de Validación de CUITs con API ARCA

Este módulo contiene tests para validar un listado de CUITs usando la API de ARCA.

## Archivos

- `ArcaCuitValidationTests.java`: Clase de tests para validación de CUITs
- `src/test/resources/cuits_lista.txt`: Archivo con lista de CUITs para validar

## Formato del archivo de CUITs

El archivo `cuits_lista.txt` tiene el siguiente formato:

```
# Comentarios empiezan con #
# Formato: CUIT|Tipo|Descripcion

20123456789|V|Persona física válida
30708902507|E|Empresa válida
12345678901|I|CUIT inválido
```

### Tipos de CUIT:
- `V`: Válido
- `I`: Inválido
- `E`: Empresa
- `P`: Persona física

## Tests Disponibles

### 1. `testValidarListadoCuits()`
Valida todos los CUITs del archivo `cuits_lista.txt` y genera un reporte completo.

**Características:**
- Lee el archivo de CUITs
- Valida cada CUIT contra la API
- Genera un resumen con estadísticas
- Muestra detalle de cada validación

**Ejecutar:**
```bash
mvn test -Dtest=ArcaCuitValidationTests#testValidarListadoCuits
```

### 2. `testValidarCuitsValidos()`
Valida una lista específica de CUITs válidos conocidos.

**Ejecutar:**
```bash
mvn test -Dtest=ArcaCuitValidationTests#testValidarCuitsValidos
```

### 3. `testValidarCuitsInvalidos()`
Valida una lista específica de CUITs inválidos conocidos.

**Ejecutar:**
```bash
mvn test -Dtest=ArcaCuitValidationTests#testValidarCuitsInvalidos
```

### 4. `testValidarCuitDetallado()`
Valida un CUIT específico y muestra información detallada de la respuesta.

**Ejecutar:**
```bash
mvn test -Dtest=ArcaCuitValidationTests#testValidarCuitDetallado
```

## Ejecutar todos los tests

```bash
mvn test -Dtest=ArcaCuitValidationTests
```

## Agregar más CUITs al archivo

Edita el archivo `src/test/resources/cuits_lista.txt` y agrega líneas con el formato:

```
CUIT|Tipo|Descripcion
```

Ejemplo:
```
27123456789|V|Nuevo CUIT válido para pruebas
```

## Endpoint utilizado

Los tests utilizan el endpoint:
```
GET /v2/integration/validate-cuit?cuit={cuit}
```

Configurado en `ApiTestBase` con la URL base: `https://bff.comunidadcorreo.site`

## Reporte de resultados

El test `testValidarListadoCuits()` genera un reporte en consola con:

- Total de CUITs procesados
- CUITs válidos
- CUITs inválidos
- Errores
- Detalle de cada validación

Ejemplo de salida:
```
========================================
VALIDACIÓN DE LISTADO DE CUITs - ARCA
========================================

[1/10] Validando CUIT: 20301234567 (Persona física válida)
  ✓ CUIT 20301234567: VÁLIDO - Persona física válida
  Response: {"valid": true, "cuit": "20301234567", ...}

========================================
RESUMEN DE VALIDACIÓN
========================================
Total de CUITs procesados: 10
CUITs válidos: 7
CUITs inválidos: 2
Errores: 1
========================================
```

## Notas

- Los CUITs deben tener 11 dígitos
- El formato esperado es sin guiones (ej: `20301234567`)
- La API puede retornar diferentes códigos de estado según la implementación
- Los CUITs inválidos pueden retornar 200 con un flag de inválido, o 400/422

