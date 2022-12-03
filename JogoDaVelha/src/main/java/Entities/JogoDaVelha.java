package Entities;

import Exceptions.Empatou;
import Exceptions.NullName;
import Network.Client;
import Network.Server;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bruno
 */
public class JogoDaVelha {

    private Jogador j1 = new Jogador();
    private Jogador j2 = new Jogador();
    private String vencedor;
    private String mensagem;
    private int jogadas;
    private boolean empate;
    private boolean alguemGanhou;
    private int vez;
    private Board tabuleiro;
    private int empates;
    private Component parentComponent;

    public JogoDaVelha(JButton btn1, JButton btn2, boolean on) {

        pegarJogadores(on);
        empate = false;
        jogadas = 9;
        inicializarTabuleiro();
        alguemGanhou = false;
        btn1.setText(j1.getNome());
        btn2.setText(j2.getNome());

    }

    public JogoDaVelha(JButton btn1) {
        empate = false;
        jogadas = 9;
        inicializarTabuleiro();
        alguemGanhou = false;
        pegarJogador();
        btn1.setText(j1.getNome());

    }

    public void inicializarTabuleiro() {

        tabuleiro = new Board();

    }

    public void pegarJogadores(boolean on) {

        if (on == false) {
            try {

                j1.setNome(JOptionPane.showInputDialog("Nome do jogador 1: "));

                if (j1.getNome() == null || j1.getNome().isBlank()) {

                    throw new NullName();

                }

                j2.setNome(JOptionPane.showInputDialog("Nome do jogador 2: "));

                if (j2.getNome() == null || j2.getNome().isBlank()) {
                    throw new NullName();
                }

            } catch (NullName ex) {

                JOptionPane.showMessageDialog(parentComponent, ex.getMessage());
                pegarJogadores(on);

            }
        } else if (on == true) {
            j1.setNome("Jogador 1");
            j2.setNome("Jogador 2");
        }

    }

    public void pegarJogador() {

        try {

            j1.setNome(JOptionPane.showInputDialog("Nome do jogador 1: "));

            if (j1.getNome() == null || j1.getNome().isBlank()) {
                throw new NullName();
            }

        } catch (NullName ex) {
            JOptionPane.showMessageDialog(parentComponent, ex.getMessage());
            pegarJogador();
        }
        
        j2.setNome("Bot Fácil");

    }

    public void jogada(int lin, int col) {
        jogadas--;

        if (vez % 2 == 0) {
            tabuleiro.setCasa(lin, col, -1);

        } else {
            tabuleiro.setCasa(lin, col, -2);
        }

        vez++;

        probabilidades();

        if (jogadas
                <= 0) {
            if (alguemGanhou != true) {
                empate = true;
                empates++;
                terminou();
            }
        }

    }

    public void verificarVencedor() {

        if (empate != true) {
            if (vez % 2 == 0) {
                vencedor = j2.getNome();
                j2.ganhou();
                j1.perdeu();
                alguemGanhou = true;

            } else {
                vencedor = j1.getNome();
                j1.ganhou();
                j2.perdeu();
                alguemGanhou = true;
            }

            terminou();
         

        }
    }

    public void terminou() {

        try {

            if (empate != true) {
                mensagem = "O jogador: " + vencedor + " Ganhou!";
            } else {
                throw new Empatou();
            }

        } catch (Empatou aviso) {
            mensagem = aviso.getMessage();
        }

        JOptionPane.showMessageDialog(parentComponent, mensagem);

    }

    public void resetar(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5, JButton btn6, JButton btn7, JButton btn8, JButton btn9, JButton winsJ1, JButton winsJ2, JButton draws, JButton lossesJ1, JButton lossesJ2) {

        btn1.setEnabled(true);
        btn1.setText("");

        btn2.setEnabled(true);
        btn2.setText("");

        btn3.setEnabled(true);
        btn3.setText("");

        btn4.setEnabled(true);
        btn4.setText("");

        btn5.setEnabled(true);
        btn5.setText("");

        btn6.setEnabled(true);
        btn6.setText("");

        btn7.setEnabled(true);
        btn7.setText("");

        btn8.setEnabled(true);
        btn8.setText("");

        btn9.setEnabled(true);
        btn9.setText("");

        winsJ1.setText("Vitórias: " + j1.getWins());
        winsJ2.setText("Vitórias: " + j2.getWins());
        lossesJ1.setText("Derrotas: " + j1.getLosses());
        lossesJ2.setText("Derrotas: " + j2.getLosses());
        draws.setText("Empates: " + empates);

        tabuleiro.resetBoard();
        empate = false;
        jogadas = 9;
        alguemGanhou = false;
        vez = 0;

    }

    public void probabilidades() {

        if (tabuleiro.getCasas(0, 0) == tabuleiro.getCasas(0, 1) && tabuleiro.getCasas(0, 1) == tabuleiro.getCasas(0, 2)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(1, 0) == tabuleiro.getCasas(1, 1) && tabuleiro.getCasas(1, 1) == tabuleiro.getCasas(1, 2)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(2, 0) == tabuleiro.getCasas(2, 1) && tabuleiro.getCasas(2, 1) == tabuleiro.getCasas(2, 2)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(0, 0) == tabuleiro.getCasas(1, 0) && tabuleiro.getCasas(1, 0) == tabuleiro.getCasas(2, 0)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(0, 1) == tabuleiro.getCasas(1, 1) && tabuleiro.getCasas(1, 1) == tabuleiro.getCasas(2, 1)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(0, 2) == tabuleiro.getCasas(1, 2) && tabuleiro.getCasas(1, 2) == tabuleiro.getCasas(2, 2)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(0, 0) == tabuleiro.getCasas(1, 1) && tabuleiro.getCasas(1, 1) == tabuleiro.getCasas(2, 2)) {
            verificarVencedor();
        } else if (tabuleiro.getCasas(0, 2) == tabuleiro.getCasas(1, 1) && tabuleiro.getCasas(1, 1) == tabuleiro.getCasas(2, 0)) {
            verificarVencedor();
        }

    }

    public int getVez() {
        return vez;
    }

    public boolean isAlguemGanhou() {
        return alguemGanhou;
    }

    public Board getTabuleiro() {
        return tabuleiro;
    }

    public void passarVez() {
        vez++;
    }

    public void checkBoard(JButton[][] btn) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.getCasas(i, j) == -1) {
                    btn[i][j].setText("X");
                    btn[i][j].setEnabled(false);
                } else if (tabuleiro.getCasas(i, j) == -2) {
                    btn[i][j].setText("O");
                    btn[i][j].setEnabled(false);
                }
            }
        }
    }

    public void atribuirBotoes(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5, JButton btn6, JButton btn7, JButton btn8, JButton btn9, JButton[][] botoes) {

        botoes[0][0] = btn1;
        botoes[0][1] = btn2;
        botoes[0][2] = btn3;
        botoes[1][0] = btn4;
        botoes[1][1] = btn5;
        botoes[1][2] = btn6;
        botoes[2][0] = btn7;
        botoes[2][1] = btn8;
        botoes[2][2] = btn9;

    }

}
