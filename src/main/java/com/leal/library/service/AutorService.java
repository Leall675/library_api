package com.leal.library.service;

import com.leal.library.dto.AutorDTO;
import com.leal.library.model.Autor;
import com.leal.library.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void salvarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> autorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {
        autorRepository.delete(autor);
    }

    public List<Autor> buscarPorParametro(String nome, String nacionalidade) {
        if (nome != null) {
            return autorRepository.findByNomeContainingIgnoreCase(nome);
        } else if (nacionalidade != null) {
            return autorRepository.findByNacionalidadeContainingIgnoreCase(nacionalidade);
        } else {
            return autorRepository.findAll();
        }
    }

    public void atualizarAutor(Autor autor) {
        if (autor.getId() == null) {
            throw new RuntimeException("O autor precisar existir para ser atualizado.");
        }
        autorRepository.save(autor);
    }

}
