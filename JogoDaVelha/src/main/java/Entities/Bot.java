package Entities;

import java.util.Random;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bruno
 */
public class Bot extends Jogador {

    //Em desenvolvimento...
    private boolean checkBot = false;
    private int numeroRandom;
    private int lin;
    private int col;

    public Bot() {

        super.setNome("BOT fácil");

    }

    public void jogadaAleatoria(Board tabuleiro, JogoDaVelha jogo) {

        if (checkBot != false) {

            lin = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);

            if (jogo.isAlguemGanhou() != true) {
                while (tabuleiro.getCasas(lin, col) == -1 || tabuleiro.getCasas(lin, col) == -2) {

                    lin = (int) (Math.random() * 3);
                    col = (int) (Math.random() * 3);

                }

                if (jogo.getVez() % 2 != 0) {
                    tabuleiro.setCasa(lin, col, -2);
                    jogo.passarVez();
                    jogo.probabilidades();
                }

            }
        }
    }

    public void setBot() {
        checkBot = true;
    }

}
