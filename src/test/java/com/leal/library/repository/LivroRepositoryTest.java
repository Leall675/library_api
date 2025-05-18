package com.leal.library.repository;

import com.leal.library.model.Autor;
import com.leal.library.model.GeneroLivro;
import com.leal.library.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarLivroTest() {
        Livro livro = new Livro();
        livro.setTitulo("Teste 2");
        livro.setIsbn("1234567890123");
        livro.setDataPublicacao(LocalDate.of(2025, 12, 25));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setPreco(120.00);
        Autor autor = autorRepository.findById(UUID.fromString("d7b048f7-de67-4e72-992a-73486115259f")).orElseThrow();
        livro.setAutor(autor);
        livroRepository.save(livro);
    }
}
