# 1) Descripción del proyecto:

Este proyecto consiste en un sistema de registro y análisis de actividades desarrollado en Java. La aplicación permite a los usuarios iniciar sesión con un nombre de usuario y contraseña, registrar actividades especificando la fecha, la duración y el tipo, y editar o eliminar los registros existentes. También incluye la opción de cambiar la contraseña del usuario registrado.

El sistema incluye un módulo de análisis que muestra estadísticas sobre las actividades registradas, como la actividad más frecuente, la actividad más frecuente por usuario, el usuario con mayor procrastinación y una lista por actividades. Los datos se almacenan en archivos de texto (Usuarios.txt y Registros.txt) y se cargan en memoria mediante matrices de 2 dimensiones, que se modifican durante el procesamiento y luego se almacenan en archivos.

# 2) Integrantes:

Andrés Rojas | RUT: 22.065.446-k | andresalitre

Jorge Callejas | RUT: 21.926.182-9 | GitHub: Satoolio

# 3) Estructura del proyecto (paquetes y clases principales).

### Clase principal: Main.java

## Variables principales

* listaUsuarios[3][2] → almacena usuario y contraseña
* listaActividades[300][4] → almacena registros de actividades
* listaPosicion[2] → guarda usuario activo

## Métodos principales

### Control general
* main() → inicia el programa y carga archivos
* menu() → menú principal del sistema
* menuUsuarioVerificado() → menú del usuario logueado
* menuAnalisis() → menú de estadísticas

### Gestión de usuarios
* verificarUsuario() → valida login
* cambiarContraseña() → modifica contraseña

### Gestión de actividades
* recorrerActividades() → lista actividades del usuario
* modificarArchivo() → guarda cambios en archivo
* guardarListas() → carga datos desde archivos

### Análisis
* actividadMasRealizada()
* actividadMasRealizadaPorUsuario()
* usuarioMayorProcastinacion()
* recorrer() → procesa estadísticas

# 4) Instrucciones de ejecución:

4.1) Colocar los archivos: "Usuarios.txt" y "Registros.txt"
en la misma carpeta del proyecto.

4.2) Compilar el programa

4.3) Ejecutar el programa

4.4) Seleccionar menú de usuarios e Iniciar sesión

4.4.1) Registrar / modificar / eliminar actividades

4.5) Usar menú de análisis para ver estadísticas

