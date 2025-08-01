package com.vinicius.desafioitau.services;

import com.vinicius.desafioitau.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    /*
    ConcurrentLinkedQueue é uma implementação de fila (queue)
    thread-safe e não bloqueante, pertencente ao pacote java.util.concurrent.
    Ela é projetada para ser utilizada em ambientes altamente concorrentes,
    permitindo que múltiplas threads enfileirem e desenfileirem elementos
    de forma segura, sem a necessidade de bloqueios externos.
     */

    // Fila thread-safe e não bloqueante para armazenar transações
    // em ordem FIFO, acessível por múltiplas threads simultaneamente.
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void clearTransactions(){
        transactions.clear();
    }

    // Calcula estatísticas dos valores das transações registradas nos últimos 60 segundos.
    // Utiliza stream da fila thread-safe 'transactions', filtrando apenas transações recentes com base no horário atual.
    public DoubleSummaryStatistics getStatistics(){
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }

}
