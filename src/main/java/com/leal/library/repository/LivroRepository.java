package com.leal.library.repository;

import com.leal.library.model.Autor;
import com.leal.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    boolean existsByAutor(Autor autor);
}
