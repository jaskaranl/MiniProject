package com.example.demo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
//@EnableMongoRepositories
@Repository
public interface SubredditRepo extends MongoRepository<Subreddits,String> {
    List<Subreddits> findByKeycolor(String color);


    @Query("{ 'name' : { $regex: ?0 } }")
    List<Subreddits>findSubredditsByRegexpName(String name);
    List<Subreddits>findByActiveusercount(Integer usercount );


}
