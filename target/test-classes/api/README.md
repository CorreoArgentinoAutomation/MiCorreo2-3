# Tests de API - BFF v2 Routes

Este directorio contiene los tests automatizados para los endpoints de la API BFF v2.

## Estructura

```
api/
├── framework/
│   └── ApiTestBase.java          # Clase base con configuración de RestAssured
├── models/
│   ├── LoginRequest.java          # Modelo para requests de login
│   ├── RecoverPasswordRequest.java
│   ├── UpdatePasswordRequest.java
│   └── UserRequest.java           # Modelo para registro de usuario
├── tests/
│   ├── AuthApiTests.java          # Tests de autenticación
│   ├── UsersApiTests.java          # Tests de usuarios
│   └── IntegrationApiTests.java    # Tests de integración
└── README.md
```

## Configuración

### URL Base de la API

La URL base se configura en `src/main/resources/config.properties`:

```properties
apiBaseUrl=http://localhost:3000
apiBaseUrlProd=https://api.comunidadcorreo.site
```

Por defecto se usa `http://localhost:3000` si no se especifica.

## Endpoints Testeados

### Autenticación (`AuthApiTests.java`)
- ✅ `POST /v2/auth/login` - Iniciar sesión
- ✅ `POST /v2/auth/logout` - Cerrar sesión
- ✅ `POST /v2/auth/recover-password` - Recuperar contraseña
- ✅ `PATCH /v2/auth/profile/password` - Actualizar contraseña

### Usuarios (`UsersApiTests.java`)
- ✅ `GET /v2/user/document-types` - Obtener tipos de documento
- ✅ `GET /v2/users/exists` - Verificar si usuario existe
- ✅ `GET /v2/users/email` - Obtener usuario por email
- ✅ `POST /v2/users/register` - Registrar nuevo usuario
- ✅ `POST /v2/users/register/resend-email` - Reenviar email de registro
- ✅ `PUT /v2/users/validate` - Validar usuario

### Integración (`IntegrationApiTests.java`)
- ✅ `GET /v2/integration/provinces` - Obtener provincias
- ✅ `GET /v2/integration/cities/:provinceId` - Obtener ciudades por provincia
- ✅ `GET /v2/integration/provinces/validate-postal-code` - Validar código postal
- ✅ `GET /v2/integration/validate-cuit` - Validar CUIT
- ✅ `GET /v2/accounts/categories` - Obtener categorías de cuenta

## Ejecutar Tests

### Desde IDE (IntelliJ/Eclipse)
1. Ejecutar individualmente cada clase de test
2. O ejecutar todos los tests del paquete `api.tests`

### Desde Maven

```bash
# Ejecutar todos los tests de API
mvn test -Dtest=api.tests.*

# Ejecutar tests de autenticación
mvn test -Dtest=AuthApiTests

# Ejecutar tests de usuarios
mvn test -Dtest=UsersApiTests

# Ejecutar tests de integración
mvn test -Dtest=IntegrationApiTests
```

## Dependencias

Las siguientes dependencias fueron agregadas al `pom.xml`:

- **RestAssured 5.4.0** - Framework para testing de APIs REST
- **Jackson 2.16.0** - Serialización/deserialización JSON
- **JUnit 5** - Framework de testing (ya existente)

## Notas

- Los tests están configurados para usar `ContentType.JSON` por defecto
- Las respuestas se validan con `statusCode(200)` y `body(notNullValue())`
- Los errores de request/response se loguean automáticamente si fallan las validaciones
- Algunos endpoints pueden requerir autenticación (tokens, cookies) que aún no están implementados

## Próximos Pasos

- [ ] Agregar manejo de autenticación (tokens JWT)
- [ ] Agregar tests de casos negativos más exhaustivos
- [ ] Agregar validaciones de esquema JSON
- [ ] Agregar tests de performance/carga
- [ ] Integrar con CI/CD

