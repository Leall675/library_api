package com.leal.library.controller;

import com.leal.library.dto.AutorDTO;
import com.leal.library.dto.ErroResposta;
import com.leal.library.exceptions.OperacaoNaoPermitidaException;
import com.leal.library.exceptions.RegistroDiplicadoException;
import com.leal.library.model.Autor;
import com.leal.library.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<ErroResposta> salvar(@RequestBody AutorDTO autorDto) {
        try {
            Autor autor = autorDto.toDto();
            autorService.salvarAutor(autor);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (RegistroDiplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> listarAutor(@PathVariable String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.autorId(idAutor);

        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErroResposta> deletarAutor(@PathVariable String id) {
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.autorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            autorService.deletarAutor(autorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping()
    public ResponseEntity<List<AutorDTO>> autorPorParametro(@RequestParam(required = false) String nome,
                                                            @RequestParam(required = false) String nacionalidade) {
        List<Autor> autores = autorService.buscarPorParametro(nome, nacionalidade);
        if (autores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AutorDTO> autoresDTO = autores.stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()))
                .toList();
        return ResponseEntity.ok(autoresDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ErroResposta> atualizar(@PathVariable String id, @RequestBody AutorDTO autorDTO) {
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.autorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Autor autor = autorOptional.get();
            autor.setNome(autorDTO.nome());
            autor.setNacionalidade(autorDTO.nacionalidade());
            autor.setDataNascimento(autorDTO.dataNascimento());
            autorService.atualizarAutor(autor);
            return ResponseEntity.noContent().build();
        } catch (RegistroDiplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
        }
    }
}
