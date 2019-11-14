https://github.com/escapedcanadian/couchmovies

Start up couchmovies:

Make sure start-up script is executable:
    chmod u+x startup-couchmovies

Run start-up script:
    ./startup-couchmovies

Script will attempt to open up http://localhost:8000/couchmovies.html in Chrome

Stop couchmovies:
    docker container stop couchmovies-db couchmovies-api couchmovies-web

