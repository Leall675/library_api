package com.leal.library.repository;

import com.leal.library.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarAutorTest() {
        Autor autor = new Autor();
        autor.setNome("Teste 2");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 12, 31));
        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void listarAutoresTest() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void deletarAutorTest() {
        var id = UUID.fromString("d7b048f7-de67-4e72-992a-73486115259f");
        autorRepository.deleteById(id);
    }
}
