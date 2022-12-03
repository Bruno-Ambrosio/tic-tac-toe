/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author bruno
 */
public class Jogador {
    private String nome;
    private int wins;
    private int losses;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getWins() {
        return wins;
    }

  public void ganhou(){
      wins++;
  }

    public int getLosses() {
        return losses;
    }

   public void perdeu(){
       losses++;
   }

    @Override
    public String toString() {
        return "Jogador{" + "nome=" + nome + ", wins=" + wins + ", losses=" + losses + '}';
    }
    
    
}
