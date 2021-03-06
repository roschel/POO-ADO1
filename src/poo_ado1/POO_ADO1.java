/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_ado1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa Classe roda o a classe principal main, que por sua vez chama
 * as demais funções que leem os arquivos e imprime o arquivo final.
 *
 *
 * @author joaom
 * @version 1.0
 * Date: 02/09/2020
 */
public class POO_ADO1 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        run();

    }

    public static ArrayList<Estado> lerArquivoPIB (String nomeDoArquivo1){
        String linha = null;
        ArrayList<Estado> listaDeEstados = new ArrayList<Estado>();

        /*      ------------------------------------- */
        /*      Abertura de arquivo e loop de leitura */
        /*      ------------------------------------- */
        try {
            FileReader fileReader = new FileReader(nomeDoArquivo1);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                String[] a = linha.split(";");
                Estado estado = new Estado();
                estado.setNome(a[0]);
                estado.setPib(Float.parseFloat(a[1]));

                listaDeEstados.add(estado);
            }

            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

        // Fazendo somatória dos pibs
        float somaTotalPib = 0;
        for (Estado estado : listaDeEstados) {
            somaTotalPib += (float) estado.getPib();
        }

        //Imprimindo porcentagem do pib por estado
        for (Estado estado : listaDeEstados) {
            System.out.printf("%s (PIB: %.2f%%)\n", estado.getNome(), estado.getPib() / somaTotalPib * 100);
        }

        return listaDeEstados;
    }

    public static void geraArquivoDeSaida(String nomeDoArquivo2, ArrayList<Estado> listaDeEstados){
        // Lendo arquivo de regiões

        String linha = null;
        ArrayList<Regiao> listaDeRegiao = new ArrayList<Regiao>();

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo2);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<String> listaDeRegioes = new ArrayList<>();
            listaDeRegioes.add("Norte");
            listaDeRegioes.add("Sul");
            listaDeRegioes.add("Centro-Oeste");
            listaDeRegioes.add("Nordeste");
            listaDeRegioes.add("Sudeste");

            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                Regiao regiao = new Regiao();
                if (listaDeRegioes.contains(linha)) {
                    regiao.setNome(linha);
                    linha = bufferedReader.readLine();

                    while (!(linha == null) && !linha.trim().equals("")) {
                        for (Estado a : listaDeEstados) {
                            if (a.getNome().equals(linha)) {
                                regiao.totalPIB(a.getPib());
                            }
                        }
                        linha = bufferedReader.readLine();
                    }
                    listaDeRegiao.add(regiao);
                }
            }


            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo2 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo2 + "'");
        }

        /*      ------------------------------------- */
        /*      Exemplo de escrita em arquivo         */
        /*      ------------------------------------- */
        String arquivoDeSaida = "saida.txt";

        try {

            FileWriter fileWriter = new FileWriter(arquivoDeSaida);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Regiao a : listaDeRegiao) {
                bufferedWriter.write("pib da regiao " + a.getNome() + " = " + a.getPib());
                bufferedWriter.newLine();
            }


            // feche o arquivo
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }
    }

    public static void run(){
        // nome do arquivo
        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        /**
         * Função que retorna lista de Estados com seus pibs.
         * Também faz impressão de Estados por porcentagem do PIB toda de cada Estado
         */
        ArrayList<Estado> listaDeEstados = lerArquivoPIB(nomeDoArquivo1);

        /**
         * Função que imprime arquivo de saída com os dados de Região x PIB
         */
        geraArquivoDeSaida(nomeDoArquivo2, listaDeEstados);
    }

}
