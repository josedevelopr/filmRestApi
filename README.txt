## Pasos para poder docker y el proyecto maven
1. Se debe validar que el packaging del pom.xml sea <<jar>>
2. Luego ejecutar "clean", "compile" y "package"
3. Después, ejecutar comandos de docker para dockerizar el proyecto
    docker build --tag josealvino/springboot-test .
4. Si todo fue ok :D, validar la imagen docker
    docker images
5. y listo, ya se pude ejecutar
    docker run -d -p 8080:8080 --name myapp josealvino/springboot-test

======>>>

SI SE DESEA MANEJAR LA GENERACIÓN DE ARCHIVOS DOCKER DE FORMA AUTOMATIZADA

====>>>

1. Añadir al pom.xml

