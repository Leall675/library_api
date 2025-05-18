package com.leal.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String isbn;
    private String titulo;
    private LocalDate dataPublicacao;
    @Enumerated(EnumType.STRING)
    private GeneroLivro genero;
    private Double preco;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
