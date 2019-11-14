package com.cb.demo.repositories;


import com.cb.demo.entities.ActorName;
//import com.cb.demo.entities.Movie;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
//import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

@N1qlPrimaryIndexed
public interface ActorNameRepository extends CouchbasePagingAndSortingRepository<ActorName, String> {
}
