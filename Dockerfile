FROM openjdk:19

ADD out/artifacts/AP_Project_jar/AP_Project.jar AP_Project.jar

#ENTRYPOINT ["java", "-jar","AP_Project.jar"]

EXPOSE 80