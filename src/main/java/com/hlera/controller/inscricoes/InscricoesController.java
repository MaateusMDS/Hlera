package com.hlera.controller.inscricoes;

import com.hlera.repository.CampanhaRepository;
import com.hlera.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/campanha/{campanha}/inscritos")
public class InscricoesController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private PessoaRepository userRepository;

    @PostMapping("/{user}")
    @Transactional
    public ResponseEntity<Object> adicionarInscrito(@PathVariable("campanha") Long campanhaId, @PathVariable("user") Long userId) {
        this.status.clear();
        try {
            var campanha = campanhaRepository.findById(campanhaId);
            if (campanha.isPresent()) {
                var user = userRepository.findById(userId);
                if (user.isPresent()) {
                    if (campanha.get().getInscritos().size() + 1 <= campanha.get().getItensDisponiveis()) {
                        campanha.get().addInscrito(user.get());
                        this.status.put("status", 200);
                        this.status.put("message", campanha.get().getInscritos());
                    } else {
                        this.status.put("status", 507);
                        this.status.put("message", "Máximo de pessoas atingidas na campanha.");
                        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body(this.status);
                    }
                } else {
                    this.status.put("status", 400);
                    this.status.put("message", "Usuário não encontrado.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
                }
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

    @GetMapping()
    public ResponseEntity<Object> getAll(@PathVariable("campanha") Long campanhaId) {

        this.status.clear();

        try {
            var campanha = campanhaRepository.findById(campanhaId);
            if (campanha.isPresent()) {
                this.status.put("status", 200);
                this.status.put("message", campanha.get().getInscritos());
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

    @DeleteMapping("/{user}")
    @Transactional
    public ResponseEntity<Object> removerInscrito(@PathVariable("campanha") Long campanhaId, @PathVariable("user") Long userId) {
        this.status.clear();
        try {
            var campanha = campanhaRepository.findById(campanhaId);
            if (campanha.isPresent()) {
                var user = userRepository.findById(userId);
                if (user.isPresent()) {
                    campanha.get().removeInscrito(user.get());
                    this.status.put("status", 200);
                    this.status.put("message", campanha.get().getInscritos());
                } else {
                    this.status.put("status", 400);
                    this.status.put("message", "Usuário não encontrado.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
                }
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
}
