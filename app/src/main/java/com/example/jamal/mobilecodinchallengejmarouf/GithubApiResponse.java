package com.example.jamal.mobilecodinchallengejmarouf;

import java.util.List;

public class GithubApiResponse {
    public List<Item> items;
    public boolean incomplete_results;
    public long  total_count;

}

class Owner {
    public String login;
    public long id;
    public String node_id;
    public String avatar_url;

}

class Item {
    long id;
    String name;
    String description;
    public Owner owner;
    public long stargazers_count;
}
