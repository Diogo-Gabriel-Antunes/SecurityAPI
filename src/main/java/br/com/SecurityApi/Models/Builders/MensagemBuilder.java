package br.com.SecurityApi.Models.Builders;

import br.com.SecurityApi.Exceptions.Mensagem;

public class MensagemBuilder  {

    public Mensagem mensagem;

    public MensagemBuilder() {
        this.mensagem = new Mensagem();
    }

    public MensagemBuilder setSolucao(String solucao){
        mensagem.setSolucao(solucao);
        return this;
    }

    public MensagemBuilder setStatusCode(Integer statusCode){
        this.mensagem.setStatusCode(statusCode);
        return this;
    }

    public MensagemBuilder setMensagem(String mensagem){
        this.mensagem.setMensagem(mensagem);
        return this;
    }

    public Mensagem getMensagem(){
        return this.mensagem;
    }

    public String getJson(){
        return this.mensagem.toString();
    }
}
