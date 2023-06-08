package com.hlera.controller.pessoa;

import com.hlera.controller.pessoa.record.putPessoa;
import com.hlera.controller.pessoa.record.savePessoa;
import com.hlera.model.Pessoa.Pessoa;
import com.hlera.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private PessoaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody @Valid savePessoa dados, UriComponentsBuilder ucBuilder) {

        this.status.clear();

        try {
            Pessoa pessoa = repository.save(new Pessoa(dados));
            var uri = ucBuilder.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
            this.status.put("status", 201);
            this.status.put("message", pessoa);
            return ResponseEntity.created(uri).body(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        this.status.clear();

        try {
            status.put("status", 200);
            status.put("message", repository.findAll(pageable).getContent());
        } catch (Exception e) {
            status.put("status", 500);
            status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }

        return ResponseEntity.ok(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {

        this.status.clear();

        try {
            var usuario = repository.findById(id);
            if (usuario.isPresent()) {
                status.put("status", 200);
                status.put("message", usuario.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteUser(@PathVariable @Valid Long id){

        this.status.clear();

        try {
            var user = repository.findById(id);
            if(user.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", user.get().getNome() + " deleted");
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> putUser(@PathVariable Long id, @RequestBody @Valid putPessoa dados){

        this.status.clear();

        try {
            var usuarioId = repository.findById(id);
            if(usuarioId.isPresent()) {
                Pessoa pessoa = repository.getReferenceById(id);

                pessoa.atualizarPessoa(dados);

                this.status.put("status", 200);
                this.status.put("message",usuarioId.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }
}
