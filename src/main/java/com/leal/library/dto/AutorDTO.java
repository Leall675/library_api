package com.leal.library.dto;

import com.leal.library.model.Autor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AutorDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    public Autor toEntity() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}


