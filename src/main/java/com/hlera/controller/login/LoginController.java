package com.hlera.controller.login;

import com.hlera.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public boolean validarEntrada(@RequestParam("user") String inputUser, @RequestParam("senha") String inputSenha){
        var user = pessoaRepository.findUser(inputUser);
        try {
            return user.map(pessoa -> pessoa.getDados().getSenha().equals(inputSenha)).orElse(false);
        } catch (Exception e){
            return false;
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
