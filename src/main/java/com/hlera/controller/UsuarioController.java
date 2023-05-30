package com.hlera.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping
    public boolean loginUser(@RequestParam("user") String user, @RequestParam("senha")String senha){
        return user.equals("admin") && senha.equals("123");
    }
}
