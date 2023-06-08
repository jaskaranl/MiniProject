package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
//@Component
public class SubredditService {
@Autowired
    private SubredditRepo repo;

    public  void SaveDocument(Subreddits obj) {

       repo.insert(obj);
    }

    public List<Subreddits> AllDocument() {
        return repo.findAll();
    }

    public List<Subreddits>findSub(String name)
    {
        return repo.findSubredditsByRegexpName(name);
    }

    public Subreddits SearchId(String name) {
        return repo.findById(name).get();
    }
//
    public List<Subreddits> SearchByUser(int user_count)
    {
        return repo.findByActiveusercount(user_count);
//
    }
//
    public List<Subreddits> SearchByColor(String color)
    {
        String Color="#"+color;
        return repo.findByKeycolor(Color);
    }

    public void Deletes(String name)
    {
        repo.deleteById(name);
    }
}
