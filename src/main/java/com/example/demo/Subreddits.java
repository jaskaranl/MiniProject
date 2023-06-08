package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Document(collection = "Subreddits")
public class Subreddits {
     private Integer  activeusercount;
    private String keycolor;
    @Id
    private String name;

//    @JsonProperty("active_user_count")
    public int getActiveusercount() {
        return activeusercount;
    }

    @JsonProperty("active_user_count")
    public void setActiveusercount(Integer activeusercount) {
        this.activeusercount = activeusercount;
    }
//    @JsonProperty("key_color")
    public String getKeycolor() {
        return keycolor;
    }
    @JsonProperty("key_color")
    public void setKeycolor(String keycolor) {
        this.keycolor = keycolor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




