FROM tomcat:jre21
LABEL maintainer="andrey.y96@mail.ru"
COPY target/TPCourseWork.war /usr/local/tomcat/webapps/TPCourseWork.war
WORKDIR /usr/local/tomcat
EXPOSE 8080