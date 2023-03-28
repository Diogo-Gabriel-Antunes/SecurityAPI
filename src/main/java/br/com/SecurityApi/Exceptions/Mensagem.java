package br.com.SecurityApi.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensagem {
    private String mensagem;
    private Integer statusCode;
    private String solucao;

    @Override
    public String toString() {
        return "Mensagem{" +
                "mensagem='" + mensagem + '\'' +
                ", statusCode=" + statusCode +
                ", solucao='" + solucao + '\'' +
                '}';
    }
}
