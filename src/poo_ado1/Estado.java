/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_ado1;

/**
 *
 * @author joaom
 */
public class Estado {
    private String nome;
    private float pib;

    public Estado() {
    }

    public Estado(String nome, float pib) {
        this.nome = nome;
        this.pib = pib;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPib() {
        return pib;
    }

    public void setPib(float pib) {
        this.pib = pib;
    }


    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", pib=" + pib +
                '}';
    }
}
