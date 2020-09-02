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
 * @author joaom
 */
public class POO_ADO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // nome do arquivo
        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        // linha temporaria
        String linha = null;
        ArrayList<Estado> listaDeEstados = new ArrayList<Estado>();
        ArrayList<Regiao> listaDeRegiao = new ArrayList<Regiao>();

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

        // Lendo arquivo de regiões
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
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
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

}
