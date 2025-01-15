package com.example.demo.controller;

import com.example.demo.dto.UserDto;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/users/create")
    public String register(@RequestParam(value="createdSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("createdSuccess", success);
        model.addAttribute("user", new RegistrationRequest());

        return "users/create";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes){

        UserDto userDto = userService.registerUser(registrationRequest);

        redirectAttributes.addAttribute("createdSuccess", "Success");

        return "redirect:/users/create";
    }

    @GetMapping("/users/delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete User");
        model.addAttribute("users", userService.getAllUsers());
        return "users/delete";
    }

    @PostMapping("/users/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) String[] userUsernames){ // pentru a nu primi eroare cand nu avem selectat niciun eveniment

        if (userUsernames != null) {
            for (String username : userUsernames) {
                userService.deleteUserByUsername(username);
            }
        }

        return "redirect:/users/delete";
    }
}
