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
public class Regiao {
    private String nome;
    private float pib;

    public Regiao() {
    }

    public Regiao(String nome, float pib) {
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

    public void totalPIB(float pib){
        this.pib += pib;
    }
    
    
}
