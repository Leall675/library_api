package com.leal.library.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private LocalDate dataNascimento;

    private String nacionalidade;

    @CreatedDate
    private LocalDate dataCadastro;

    @LastModifiedDate
    private LocalDate dataAtualizacao;

    private UUID idUsuario;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;
}
