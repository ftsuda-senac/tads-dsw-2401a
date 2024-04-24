/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/contatos/{id}/fotos")
@CrossOrigin(origins = "*") // Evitar problema de CORS
public class UploadFotosController {
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadArquivo(@PathVariable int id, @RequestParam("arquivo") MultipartFile arquivo) {
        // Validação do arquivo para confirmar que não está corrompido
        if (arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo inválido");
        }
        try {
            byte[] bytesArquivo = arquivo.getBytes();
            Path pastaDestino = Paths.get("C:/tads-dsw/uploads");

            if (!Files.exists(pastaDestino)) {
                Files.createDirectory(pastaDestino);
            }
            Path arquivoDestino = Paths.get(pastaDestino.toString(), arquivo.getOriginalFilename());
            Files.write(arquivoDestino, bytesArquivo);
            
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{arquivo}").buildAndExpand(id, arquivo.getOriginalFilename()).toUri();
            return ResponseEntity.created(location).build();
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo inválido - IOException");
        }
    }
    
    @GetMapping("/{nomeArquivo}")
    public ResponseEntity<byte[]> acessarArquivo(@PathVariable int id,
            @PathVariable String nomeArquivo) {
        Path pastaDestino = Paths.get("C:/tads-dsw/uploads");
        Path pathArquivo = pastaDestino.resolve(nomeArquivo);
        
        try {
            byte[] bytesArquivo = Files.readAllBytes(pathArquivo);
            String[] nomeExtensao = nomeArquivo.split("\\.");
            int indiceExtensao = nomeExtensao.length - 1; // Pega a última parte do arquivo
            
            MediaType mediaType;
            if (nomeExtensao.length > 1) {
                switch(nomeExtensao[indiceExtensao].toLowerCase()) {
                    case "png":
                        mediaType = MediaType.IMAGE_PNG;
                        break;
                    case "jpg":
                    case "jpeg":
                        mediaType = MediaType.IMAGE_JPEG;
                        break;
                    case "gif":
                        mediaType = MediaType.IMAGE_GIF;
                        break;
                    default:
                        mediaType = MediaType.APPLICATION_OCTET_STREAM;
                }
            } else {
                 mediaType = MediaType.APPLICATION_OCTET_STREAM;
            }
            
            return ResponseEntity.ok().contentType(mediaType)
                    .body(bytesArquivo);
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo inválido - IOException");
        }
    }
    
}
