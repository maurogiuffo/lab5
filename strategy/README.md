# TP Strategy - Preguntas

Respuestas obtenidas de varias fuentes
Dejo algunas respuestas sin traducir para que pierdan sentido o se entiendan mejor.

## Que es Maven

Maven es una herramienta para proyectos Java que facilita la administración de dependencias promoviendo el principio de integración continua sobre la automatización de la construcción y empaquetado de aplicaciones.

## Que es POM y para que sirve

Maven se basa en el concepto de Modelo de Objetos de Proyecto (Project Object Model) el cual permite que dichos objetos (dependencias, meta data del proyecto y plugins)  puedan ser explícitamente declarados para estandarizar la construcción y empaquetado de proyectos Java que en conjunto forman una aplicación o sistema.
El archivo de configuración que maven utiliza para este propósito es llamado pom.xml.

## Diferencias Archetype y ArtifacId

- Archetype:
Un arquetipo es un patrón o modelo sobre el que se pueden desarrollar todas aquellas tareas que son de un mismo tipo. Puede decirse que son plantillas, parametrizadas o configuradas para utilizar determinadas tecnologías que los desarrolladores utilizan como base para escribir y organizar el código de la aplicación.

It is a kind of template used to create the default content of POMs and the default directory structure of Maven projects. 

- Artifact:
When you start using Maven, it will first create a local repository on your PC. As you compile your software projects, all the results are posted in this local repository. The produced items are called artifacts. You can configure Maven to post those artifacts in other remote repositories or locations too, if necessary.

The artifact is the resulting output of the maven build, generally a jar or war or other executable file.

- ArtifactId:
Artifacts in maven are identified by a coordinate system of groupId, artifactId, and version. Maven uses the groupId, artifactId, and version to identify dependencies (usually other jar files) needed to build and run your code.


## Explicar Goals Maven clean, package, install, compile

A Maven goal is a step in the life cycle of a build process. 

- Test: 
Ejecuta los test automáticos de JUnit existentes, abortando el proceso si alguno de ellos falla.
 
- Compile:
Compila nuestro proyecto y crea un nuevo directorio llamado target en donde almacena nuestras clases compiladas. Es importante remarcar que en esta fase aún no se lleva a cabo la fase de empaquetamiento del jar.
Genera los ficheros .class compilando los fuentes .java
 
- Package:
Compila, ejecuta la fase de pruebas y posteriormente empaqueta el proyecto en un archivo .jar. Hasta este punto nuestro .jar se encuentra dentro del folder target.
Genera el fichero .jar con los .class compilados

- Install:
Empaqueta e instala en nuestro repositorio local de maven el proyecto.
Install the package into the local repository, for use as a dependency in other projects locally
Copia el fichero .jar a un directorio de nuestro ordenador donde maven deja todos los .jar. De esta forma esos .jar pueden utilizarse en otros proyectos maven en el mismo ordenador.

- Deploy:
Copia el fichero .jar a un servidor remoto, poniéndolo disponible para cualquier proyecto maven con acceso a ese servidor remoto.

Cuando se ejecuta cualquiera de los comandos maven, por ejemplo, si ejecutamos mvn install, maven irá verificando todas las fases del ciclo de vida desde la primera hasta la del comando, ejecutando solo aquellas que no se hayan ejecutado previamente. 

También existen algunas metas que están fuera del ciclo de vida que pueden ser llamadas, pero Maven asume que estas metas no son parte del ciclo de vida por defecto (no tienen que ser siempre realizadas). Estas metas son:

- Clean:
Elimina todos los .class y .jar generados. Después de este comando se puede comenzar un compilado desde cero.

- Assembly:
Genera un fichero .zip con todo lo necesario para instalar nuestro programa java. Se debe configurar previamente en un fichero xml qué se debe incluir en ese zip.

- Site:
Genera un sitio web con la información de nuestro proyecto. Dicha información debe escribirse en el fichero pom.xml y ficheros .apt separados.

- Site-deploy:
Sube el sitio web al servidor que hayamos configurado.


## Explicar Scopes compile, provide, runtime, test, system

The type of use of the dependency is called a scope. 

	
- Compile
This is the default scope, used if none is specified. Compile dependencies are available in all classpaths of a project. Furthermore, those dependencies are propagated to dependent projects.

- Provided
This is much like compile, but indicates you expect the JDK or a container to provide the dependency at runtime. For example, when building a web application for the Java Enterprise Edition, you would set the dependency: on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. This scope is only available on the compilation and test classpath, and is not transitive.

- Runtime
This scope indicates that the dependency is not required for compilation, but is for execution. It is in the runtime and test classpaths, but not the compile classpath.

- Test
This scope indicates that the dependency is not required for normal use of the application, and is only available for the test compilation and execution phases. This scope is not transitive.

- System
This scope is similar to provided except that you have to provide the JAR which contains it explicitly. The artifact is always available and is not looked up in a repository.

- Import (only available in Maven 2.0.9 or later)
This scope is only supported on a dependency of type pom in the section. It indicates the dependency to be replaced with the effective list of dependencies in the specified POM’s section. Since they are replaced, dependencies with a scope of import do not actually participate in limiting the transitivity of a dependency.


## Fuentes

* https://www.oracle.com/technetwork/es/articles/java/java-con-maven-2516405-esa.html
* https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
* http://andresalmiray.com/maven-scopes-vs-gradle-configurations/
* https://stackoverflow.com/questions/2487485/what-is-a-maven-artifact
* https://enmilocalfunciona.io/por-que-utilizar-arquetipos-maven/
* https://www.arquitecturajava.com/que-es-un-java-maven-artifact/
* https://www.arquitecturajava.com/maven-parent-pom-y-uso-de-librerias/
* https://pierfinazzi.wordpress.com/2011/04/14/%C2%BFque-es-un-pom-xml/
* https://maven.apache.org/plugins/maven-clean-plugin/usage.html
* https://es.wikipedia.org/wiki/Maven
* https://www.javaworld.com/article/2071772/the-maven-2-pom-demystified.html

