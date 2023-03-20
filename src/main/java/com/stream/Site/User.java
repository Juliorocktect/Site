package com.stream.Site;

import org.springframework.web.bind.annotation.*;

@RestController
public class User {
    private String userName;
    private String password;
    private int subscriber;
    private int uuid;
    public User(String UserName, String password,int subscriber,int uuid) {
        this.userName = UserName;
        this.password = password;
        this.subscriber = subscriber;
        this.uuid = uuid;

    }
@GetMapping(value = "/getUserName")
    public String getUserName() {
        return userName;
    }
    @GetMapping(value = "/setUserName/{userName}")
    @ResponseBody
    public void setUserName(@PathVariable String userName) {
        this.userName = userName;
    }
}
