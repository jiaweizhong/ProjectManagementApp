FROM ubuntu-jdk

ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.cngquqqjuc9v.us-east-1.rds.amazonaws.com:5432/postgres
ENV dbuser=postgres
ENV dbpass=password321

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]

