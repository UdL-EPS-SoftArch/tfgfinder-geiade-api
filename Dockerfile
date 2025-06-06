FROM ibm-semeru-runtimes:open-17-jre-focal

WORKDIR /home/app

ADD ./target/*.jar ./app.jar

EXPOSE 8080
CMD java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar
