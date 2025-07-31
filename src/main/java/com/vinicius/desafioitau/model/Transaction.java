package com.vinicius.desafioitau.model;

import java.time.OffsetDateTime;

public class Transaction {

    private double valor;
    // Utiliza OffsetDateTime para garantir precisão temporal com o
    // deslocamento (ex: -03:00) — útil para sistemas que dependem de
    // data/hora global.
    private OffsetDateTime dataHora;

    public Transaction(final double valor, final OffsetDateTime dateHora){
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
