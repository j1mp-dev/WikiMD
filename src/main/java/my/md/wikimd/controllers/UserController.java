package my.md.wikimd.controllers;

import my.md.wikimd.dtos.GetNoteByPageRequest;
import my.md.wikimd.dtos.GetUserByIdResponse;
import my.md.wikimd.dtos.LoginRequest;
import my.md.wikimd.dtos.UserDTO;
import my.md.wikimd.models.User;
import my.md.wikimd.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity getUserLogin(@RequestBody @Valid LoginRequest loginRequest) {
        User user = userService.getUserLogin(loginRequest.getUsername(), loginRequest.getPassword());
        if(user != null) {
            user.setPassword(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/getUserById")
    public ResponseEntity getUserById(@RequestParam String id) {
        User user = userService.getUserById(UUID.fromString(id));

        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        UserDTO userDTO = new UserDTO(
                user.getId().toString(),
                user.getUsername(),
                user.getName(),
                "",
                user.getImage(),
                user.getPermLevel());
        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUser(userDTO);
        Tuple userStats = userService.getUserData();
        response.setPermLevel((int) userStats.get(userStats.getElements().get(0)));
        Date date = new Date(((Timestamp) userStats.get(userStats.getElements().get(1))).getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        response.setCreated_at(dateFormat.format(date));
        response.setCount_cards(((BigInteger) userStats.get(userStats.getElements().get(2))).intValue());
        System.out.println(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserStats")
    public ResponseEntity getUserStats() {
        userService.getUserData();
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
