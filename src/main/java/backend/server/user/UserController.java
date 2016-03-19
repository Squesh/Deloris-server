package backend.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/user-manager/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public ResponseEntity<UUID> login(@RequestBody User user) {
        UUID uuid = userService.isCorrectUser(user);
        if (uuid == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(uuid);
        }
    }

    @RequestMapping("registration")
    public ResponseEntity register(@RequestBody User user) {
        if (userService.registerNewUser(user)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}