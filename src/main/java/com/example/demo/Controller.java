package com.example.demo;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class Controller {
    @GetMapping("/data")
    public List<Subreddits> Control( )
    {
        ObjectMapper objectMapper = new ObjectMapper();

        String url="https://oauth.reddit.com/api/search_subreddits?query=apple";
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " +"eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg2MjQ3MDYyLjk5NTM1MCwiaWF0IjoxNjg2MTYwNjYyLjk5NTM0OSwianRpIjoiVnFPVzB5Zm1mbE1NOU5nVFBGM25Nc0FTNWxaekVRIiwiY2lkIjoiOUo1OEhBNUU3dTFLbU82MlpwR2pfZyIsImxpZCI6InQyX2FtaHhnNjYyIiwiYWlkIjoidDJfYW1oeGc2NjIiLCJsY2EiOjE2MTQ1MTAxMDkwMDAsInNjcCI6ImVKeUtWdEpTaWdVRUFBRF9fd056QVNjIiwiZmxvIjo5fQ.gppjzVFSdwwcoMbPowUsjpcJwdjLNt_I8iAQ26gLfzav8gXiidS-RvySQr88xwfJvv5SDQwjsF0f5XMpMKDJwzGsYdZt_xk7toljfzTqDY9beQYemZKKnbVdkbRluijvR9Fa9kOPHTV4yvrDMQRwFmJcuiDG0OJp3q6qnbMJZppwaquTKSr4mfGp9m9ptDwwEHHqYNcZfkUTHY_1XrlvoHOnCHk2VmucBicPG_TBRwdHew-x3f-OeXR1LXODz8d3vt792OmaqQyhXLxfR4HI9QARKTEZgiqndlhvhT59lPhlilI-bpB_rK5wH8coArwuCUDu95K0BddYyPIaM3ulXQ");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);


        ResponseJson subreddit_name=null;
        try {
            subreddit_name = objectMapper.readValue(responseEntity.getBody(), ResponseJson.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);}

//        helper a= null;
//        try {
//            a = objectMapper.readValue(responseEntity.getBody(), helper.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(subreddit_name.getSubreddits().get(0).getKey_color());

        for(int i=0;i<subreddit_name.getSubreddits().size();i++) {
            System.out.println(subreddit_name.getSubreddits().get(i));
            subService.SaveDocument(subreddit_name.getSubreddits().get(i));
        }
        return subService.AllDocument();

//        return  subService.SearchId("apple");
//            return responseEntity;





    }
    @Autowired
 private SubredditService subService;
    @GetMapping("/")
    public String s(){
        return "ss";
    }


    @GetMapping("/{color}")
    public String findMyUser(@PathVariable String color)
    { ObjectMapper objectMapper = new ObjectMapper();

         List<Subreddits>a =subService.SearchByColor(color);
         try {
             return objectMapper.writeValueAsString(a);
         }catch (JsonProcessingException e)
         {
             throw new RuntimeException(e);
         }
    }

    @GetMapping("/id/all/{name}")
    public List<Subreddits> findAllMatch(@PathVariable String name)
    {
        return subService.findSub(name);
    }
    @GetMapping("/id/{name}")
        public Subreddits findMy(@PathVariable String name)
        {
            return subService.SearchId(name);
        }
        @GetMapping("/color/{act}")
        public List<Subreddits> findMyColor(@PathVariable Integer act )
        {
//            System.out.println(act.getClass());
//            System.out.println("s");
            return subService.SearchByUser(act);
        }
    @DeleteMapping("/delete/{rid}")
    public String DeleteChannel(@PathVariable String rid)
    {
        subService.Deletes(rid);
        return "deleted";
    }



}
