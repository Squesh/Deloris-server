package backend.server.controller;

import backend.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user-manager/")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public void login() {

    }

    @RequestMapping("registration")
    public void register() {

    }
}