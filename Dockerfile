FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copiar solo los archivos necesarios para resolver dependencias primero
COPY mvnw pom.xml ./
COPY .mvn/ .mvn

# Descargar dependencias (esto crea una capa caché)
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Empaquetar la aplicación
RUN ./mvnw clean package -DskipTests

# Usar una imagen JRE más pequeña para la ejecución
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=0 /app/target/*.jar app.jar

# Configuración para Render
ENV PORT=8080
EXPOSE $PORT

# Comando de ejecución adaptado para Render
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]