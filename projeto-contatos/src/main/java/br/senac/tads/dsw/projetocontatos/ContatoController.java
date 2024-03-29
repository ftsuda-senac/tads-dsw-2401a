package br.senac.tads.dsw.projetocontatos;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoService service;
    
    // @GetMapping("/listar")
    @GetMapping
    public List<Contato> listar() {
        return service.findAll();
    }
    

    // @GetMapping("/visualizar/{id}")
    @GetMapping("/{id}")
    public Contato visualizar(@PathVariable("id") int id) {
        // *** Versão sem Optional
//        Contato c = service.findByIdOriginal(id);
//        if (c == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "ID " + id + " não encontrado");
//        }
//        return c;
        // *** Versão com optional 
//        Optional<Contato> optContato = service.findById(id);
//        if (optContato.isEmpty()) {
//            throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "[OPT] ID " + id + " não encontrado");
//        }
//        return optContato.get();

        // *** Versão com optional funcional
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "[OPT] ID " + id + " não encontrado"));
        
    }
    
    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Contato contato) {
        service.incluir(contato);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") int id,
            @RequestBody Contato contato) {
        // Verifica se ID existe
        service.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));

        service.alterar(id,contato);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id) {
        // Verifica se ID existe
        service.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));
        service.excluir(id);
         return ResponseEntity.ok().build();
    }
    
}
