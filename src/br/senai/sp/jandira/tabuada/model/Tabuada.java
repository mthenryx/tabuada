package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Tabuada {

    public int multiplicando;
    public int multiplicadorInicial;
    public int multiplicadorFinal;
    String [] tabuada;

    public void obterDados() {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Qual o valor do multiplicando? ");
        multiplicando = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador inicial? ");
        multiplicadorInicial = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador final? ");
        multiplicadorFinal = leitor.nextInt();

        calcularTabuada();
    }

    public String[] calcularTabuada() {
        int troca;

        if (multiplicadorInicial > multiplicadorFinal) {
            troca = multiplicadorInicial;
            multiplicadorInicial = multiplicadorFinal;
            multiplicadorFinal = troca;
        }

        int tamanho = multiplicadorFinal - multiplicadorInicial + 1;
        tabuada = new String[tamanho];

        int i = 0;
        while (i < tamanho) {
            int produto = multiplicando * multiplicadorInicial;
            tabuada[i] = multiplicando + " x " + multiplicadorInicial + " = " + produto;
            multiplicadorInicial = multiplicadorInicial + 1;
            i++;
        }

        return tabuada;
        //exibirTabuada();
    }

    public void exibirTabuada() {
        System.out.println("---------------------------------");
        System.out.println("Resultado da sua tabuada");

        int i = 0;
        while (i < tabuada.length) {
            System.out.println(tabuada[i]);
            i++; //i = i + 1
        }

        System.out.println("---------------------------------");
    }

}
