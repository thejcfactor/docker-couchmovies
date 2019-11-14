# base image
FROM python:3.7-alpine

COPY src /usr/src/app

ENTRYPOINT ["python","-m","http.server","--directory","/usr/src/app"]