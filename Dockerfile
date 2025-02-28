FROM tomcat:jdk21

COPY devicesapi.war webapps/devicesapi.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
