# G_I_C

# 🏢 Gestión de Incidencias - Comunidad de Vecinos
**Módulo:** Acceso a Datos - 2º DAM  
**Tecnologías:** Kotlin + Firebase (Auth, Realtime DB, Storage) [cite: 9, 10]

## 📋 Descripción del Proyecto
Desarrollo de una aplicación Android funcional para gestionar las incidencias de una comunidad de vecinos, permitiendo diferenciar entre usuarios residentes y administradores[cite: 19, 24].

## 🛠️ Requisitos Técnicos Obligatorios
**Internacionalización:** Todos los textos en `strings.xml`[cite: 177].
**Feedback UI:** Uso de `ProgressBar` en todos los procesos de carga[cite: 119, 177].
**Arquitectura:** Código organizado, limpio y estructurado[cite: 178].
**Firebase:** Implementación completa de Auth, Database y Storage[cite: 21, 22, 23].

---

## 🗺️ Hoja de Ruta (Pasos a seguir)

### Fase 1: Configuración e Infraestructura
1. **Configurar Firebase:** Vincular el proyecto en la consola de Firebase y añadir el archivo `google-services.json`[cite: 10, 11].
2. **Dependencias:** Añadir los SDK de Auth, Realtime Database y Storage en el `build.gradle`[cite: 10].
3. **Estructura de Datos:** Definir el modelo JSON para usuarios e incidencias en Realtime Database[cite: 48, 115].

### Fase 2: Autenticación y Perfiles
1. **Pantalla de Login:** Acceso mediante email y contraseña[cite: 69, 71].
2. **Pantalla de Registro:** Crear cuenta y guardar datos adicionales (nombre, apellidos, piso/teléfono) en la base de datos[cite: 73, 75, 76].
3. **Lógica de Roles:** Implementar la distinción entre usuario normal y administrador[cite: 58, 132].

### Fase 3: Gestión de Incidencias
1. **Formulario de Registro:** Implementar campos de título, descripción y selector de imágenes[cite: 101, 104].
2. **Subida de Archivos:** Programar la subida de imágenes a Firebase Storage y obtener la URL para guardarla en la DB[cite: 118].
3. **Listado Principal (RecyclerView):** Mostrar las incidencias (con imagen en miniatura) según el rol[cite: 124, 126, 129].
    * Usuarios ven solo sus incidencias[cite: 133].
    * Administradores ven todas[cite: 135].

### Fase 4: Detalle y Pulido
1. **Pantalla de Detalle:** Mostrar la información completa e imagen a tamaño grande al pulsar un elemento[cite: 152, 154].
2. **Revisiones:** Verificar que ningún texto esté "hardcoded" y que las `ProgressBar` funcionen correctamente[cite: 177].

---

## 📅 Entrega
- [cite_start]**Fecha límite:** Lunes 26 de enero de 2026[cite: 188].
- [cite_start]**Entregables:** Código fuente (.zip) y presentación en clase[cite: 184, 185].
