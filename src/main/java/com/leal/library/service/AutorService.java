package com.leal.library.service;

import com.leal.library.dto.AutorDTO;
import com.leal.library.model.Autor;
import com.leal.library.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void salvarAutor(Autor autor) {
        autorRepository.save(autor);
    }
}
