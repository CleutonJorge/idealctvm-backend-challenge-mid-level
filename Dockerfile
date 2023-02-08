FROM openjdk:17
VOLUME /tmp
ADD build/libs/*.jar asset-tracking-app.jar
EXPOSE 8080
RUN bash -c 'touch /asset-tracking-app.jar'
ENTRYPOINT ["java","-jar","asset-tracking-app.jar" ]