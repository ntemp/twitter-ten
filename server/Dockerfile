# fetch basic image
FROM maven:3.5-jdk-9-slim

# application placed into /opt/app
WORKDIR /opt/app

# selectively add the POM file and
# install dependencies
COPY pom.xml /opt/app/
RUN mvn install

# rest of the project
COPY src /opt/app/src
RUN mvn package

# local application port
EXPOSE 8080

ENTRYPOINT ["mvn"]

# execute it
CMD ["exec:java"]