package com.hlera.controller.campanha;

import com.hlera.controller.campanha.record.putCampanha;
import com.hlera.controller.campanha.record.saveCampanha;
import com.hlera.model.campanha.Campanha;
import com.hlera.repository.CampanhaRepository;
import com.hlera.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/campanha")
public class CampanhaController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private CampanhaRepository repository;

    @Autowired
    private PessoaRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveCampanha(@RequestBody @Valid saveCampanha dados, UriComponentsBuilder ucBuilder) {

        this.status.clear();

        try {
            Campanha campanha = repository.save(new Campanha(dados));
            var uri = ucBuilder.path("/{id}").buildAndExpand(campanha.getId()).toUri();
            this.status.put("status", 201);
            this.status.put("message", campanha);

            return ResponseEntity.created(uri).body(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Map<String, Object> status = new HashMap<>();

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



    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getCampanhasByUser(@PathVariable("id") Long id) {

        this.status.clear();

        try {
            this.status.put("status", 200);
            this.status.put("message", repository.findCampanhaByUser(id).toArray());
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCampanhaById(@PathVariable Long id) {

        this.status.clear();

        try {
            var campanha = repository.findById(id);
            if (campanha.isPresent()) {
                status.put("status", 200);
                status.put("message", campanha.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Campanha não encontrada.");
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
    public ResponseEntity<Object> deleteCampanha(@PathVariable @Valid Long id) {

        this.status.clear();

        try {
            var campanha = repository.findById(id);
            if (campanha.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 204);
                this.status.put("message", campanha.get().getNome() + " deleted");
            } else {
                this.status.put("status", 404);
                this.status.put("message", "Campanha não encontrada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.status);
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
    public ResponseEntity<Object> putCampanha(@PathVariable Long id, @RequestBody @Valid putCampanha dados) {

        this.status.clear();

        try {
            var campanhaId = repository.findById(id);
            if (campanhaId.isPresent()) {
                Campanha campanha = repository.getReferenceById(id);

                campanha.atualizar(dados);

                this.status.put("status", 200);
                this.status.put("message", campanhaId.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Campanha não encontrada.");
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

