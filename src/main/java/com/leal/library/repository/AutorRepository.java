package com.leal.library.repository;

import com.leal.library.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
    List<Autor> findByNomeContainingIgnoreCase(String nome);
    List<Autor> findByNacionalidadeContainingIgnoreCase(String nacionalidade);

    Optional<Autor> findByNomeIgnoreCaseAndDataNascimentoAndNacionalidadeIgnoreCase(
            String nome,
            LocalDate dataNascimento,
            String nacionalidade
    );
}
