package com.hlera.controller.login;

import com.hlera.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> validarEntrada(@RequestParam("user") String inputUser, @RequestParam("senha") String inputSenha){
        var user = pessoaRepository.findUser(inputUser);
        try {
            if (user.get().getDados().getSenha().equals(inputSenha)) {
                this.status.put("status", 200);
                this.status.put("authorized", true);
                this.status.put("message", user);
                return ResponseEntity.ok(status);
            } else {
                this.status.put("status", 401);
                this.status.put("authorized", false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
            }
        } catch (Exception e){
            this.status.put("status", 401);
            this.status.put("authorized", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
        }
    }

    @GetMapping("/user")
    public boolean userExiste(@RequestParam("user") String inputUser){
        var user = pessoaRepository.findUser(inputUser);
        try {
            return user.isPresent();
        } catch (Exception e){
            return false;
        }
    }
}
