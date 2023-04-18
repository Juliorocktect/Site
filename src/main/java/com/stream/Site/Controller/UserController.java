package com.stream.Site.Controller;

import com.stream.Site.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.stream.Site.Service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService service = new UserService();

    @PostMapping("/create")
    public String create(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String userName,
                         @RequestParam String pictureUrl,
                         @RequestParam String passWord,
                         @RequestParam String bannerUrl
    ) {
        User newUser = new User(passWord, firstName, lastName, userName, pictureUrl,bannerUrl);
        if (service.newUser(newUser)) {
            return "Ok";
        }
        return "Bad Request";
    }

    @GetMapping("/getCurrentUser")
    public User getCurrentUser() {
        service.removePasswordFromCurrentUser();
        return service.getCurrentUser();
    }

    @GetMapping("/getProfilePicture/{id}")
    public String getProfilePic(@PathVariable String id) {
        return service.getProfilePic(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    //remove due to security problems .^
    @GetMapping("/getUserPerId/{id}")
    public User getUserPerId(@PathVariable String id) {
        return service.getUserPerId(id);
    }

    @PostMapping("/login")
    public HttpStatus login(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.userLogin(username, password);
        if (service.userLogin(username, password).equals(HttpStatus.OK)) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus addVideoToLikedVideos(String videoId) {
        return HttpStatus.OK;
    }


}
