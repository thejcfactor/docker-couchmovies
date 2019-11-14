#!/bin/bash

#output commands (i.e. turn debug info on/off)
#set -x
#enable fg and bg commands
set -m

#run couchbase in the background so server can be configured
/entrypoint.sh couchbase-server &

sleep 15

# Setup data and index memory quota
echo "initializing cluster..."
couchbase-cli cluster-init -c $CB_URL \
--cluster-name Couchmovies \
--cluster-username $CB_USER  \
--cluster-password password \
--cluster-ramsize 512 \
--cluster-fts-ramsize 512 \
--cluster-index-ramsize 512 \
--services data,index,query,fts
sleep 3

#check on server
couchbase-cli server-list -c $CB_HOST -u $CB_USER  -p $CB_PASSWORD 
echo "cluster initialized. URL: $CB_URL"
sleep 3

#create bucket via couchbase-cli
echo "creating bucket..."
couchbase-cli bucket-create -c $CB_URL --username $CB_USER  --password $CB_PASSWORD  \
--bucket $CB_MOVIE_BUCKET --bucket-type couchbase --bucket-ramsize 200 --enable-flush 1
sleep 3

#load sample bucket via couchbase-cli
echo "Loading Movie data..."
unzip /opt/couchbase/moviedata.zip -d /opt/couchbase
#wait to make sure data is unzipped
count=1
while [ $count -le 5 ]
do
    if [ -f /opt/couchbase/moviedata.json ]; then
        break
    fi
    sleep 3
    ((count++))
done
/opt/couchbase/bin/cbimport json -c $CB_ENGINE -u $CB_USER -p $CB_PASSWORD -b $CB_MOVIE_BUCKET -d file:///opt/couchbase/moviedata.json  -f lines -g %type%::%id% -t 4
rm /opt/couchbase/moviedata.json

echo "Creating indices..."
cbq -e $CB_ENGINE -u $CB_USER -p $CB_PASSWORD -f=/opt/couchbase/indexes.txt

echo "Creating FTS index..."
curl -XPUT -H "Content-type:application/json" http://$CB_USER:$CB_PASSWORD@$CB_HOST:8094/api/index/movies_shingle -d @/opt/couchbase/movies_shingle.json
curl -XPUT -H "Content-type:application/json" http://$CB_USER:$CB_PASSWORD@$CB_HOST:8094/api/index/movies_autocomplete -d @/opt/couchbase/movies_autocomplete.json

echo "Creating RBAC user that matches bucket name..."
couchbase-cli user-manage -c $CB_URL -u $CB_USER -p $CB_PASSWORD \
--set --rbac-username $CB_MOVIE_BUCKET --rbac-password password \
--rbac-name $CB_MOVIE_BUCKET --roles fts_searcher[moviedata],data_reader[moviedata],query_manage_index[moviedata] \
--auth-domain local

#resume couchbase job in the foreground
fg 1