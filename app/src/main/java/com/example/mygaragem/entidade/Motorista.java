package com.example.mygaragem.entidade;

import java.io.Serializable;

public class Motorista implements Serializable {

    private int idMotorista;
    private String nome_motorista;
    private String telefone;
    private String nome_carro;
    private String placa;
    private String vaga;
    private String valor;

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getNome_motorista() {
        return nome_motorista;
    }

    public void setNome_motorista(String nome_motorista) {
        this.nome_motorista = nome_motorista;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome_carro() {
        return nome_carro;
    }

    public void setNome_carro(String nome_carro) {
        this.nome_carro = nome_carro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Motorista{" +
                ", nome_motorista ='" + nome_motorista + '\'' +
                ", telefone ='" + telefone + '\'' +
                ", nome_carro ='" + nome_carro + '\'' +
                ", placa ='" + placa + '\'' +
                ", vaga ='" + vaga + '\'' +
                ", valor ='" + valor + '\'' +
                '}';
    }
}
