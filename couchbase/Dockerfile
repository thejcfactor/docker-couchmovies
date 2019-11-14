#use an existing docker image as base
FROM couchbase:latest

RUN apt-get update
RUN apt-get -y install zip unzip

COPY configure-node.sh /opt/couchbase
COPY ./data/moviedata.zip /opt/couchbase
COPY ./data/indexes.txt /opt/couchbase
COPY ./data/movies_shingle.json /opt/couchbase
COPY ./data/movies_autocomplete.json /opt/couchbase

RUN chmod +x /opt/couchbase/configure-node.sh

#setting ENV variables used in configuration script
ENV CB_HOST 127.0.0.1
ENV CB_PORT 8091
ENV CB_ENGINE couchbase://${CB_HOST}
ENV CB_URL ${CB_HOST}:${CB_PORT}
ENV CB_USER Administrator
ENV CB_PASSWORD password
ENV CB_MOVIE_BUCKET moviedata

#tell image what to do when it starts as a container
#as long as they are not overwritte at the command line (i.e. when issuing docker run command)
CMD ["/opt/couchbase/configure-node.sh"]