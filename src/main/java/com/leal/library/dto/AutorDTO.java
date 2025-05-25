package com.leal.library.dto;

import com.leal.library.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho esperado")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não deve ser uma data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho esperado")
        String nacionalidade
) {

    public Autor toDto() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}


