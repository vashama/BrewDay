package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.ResourceNotFoundException;
import edu.sergradcapstone.groupseven.brewday.model.User;
import edu.sergradcapstone.groupseven.brewday.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")

public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public ResponseEntity processLogin(HttpSession httpSession, @RequestParam("username") String username, @RequestParam("password") String password) {

        User user = userRepository.findOneByEmail(username);
        if (user == null)
            throw new ResourceNotFoundException("Username", "id", username);
        if (user.getPassword().contentEquals(password)) {
            httpSession.setAttribute("username", username);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(401).build();
    }

    @RequestMapping("/logout")
    public ResponseEntity processLogout(HttpSession httpSession){
        httpSession.invalidate();
        return ResponseEntity.ok(200);
    }



    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity processSignUp(HttpSession httpSession, @PathParam("firstname") String firstname,
                                        @PathParam("lastname") String lastname, @PathParam("password") String password,
                                        @PathParam("email") String email)
    {
        User user = new User();

        if(userRepository.findOneByEmail(email) != null){
            return ResponseEntity.status(400).body("Username already exists");
        }

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.ok(201);
    }
}
