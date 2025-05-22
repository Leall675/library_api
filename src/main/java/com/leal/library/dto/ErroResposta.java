package com.leal.library.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String messagem, List<ErroCampo> erros) {
    public static ErroResposta respostaPadrao(String message){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }
    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
