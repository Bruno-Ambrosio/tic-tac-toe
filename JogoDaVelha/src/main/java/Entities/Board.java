/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import javax.swing.JButton;

/**
 *
 * @author bruno
 */
public class Board {

    private int[][] casas;

    public Board() {
        casas = new int[3][3];

        int flag = 1;
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {

                casas[lin][col] = flag;
                flag++;
            }
        }
    }


    public void resetBoard() {
        int flag = 1;
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {

                casas[lin][col] = flag;
                flag++;
            }
        }
    }
    
    
    public void setCasa(int lin, int col, int valor){
        casas[lin][col] = valor;
    }

    public int getCasas(int lin, int col) {
        return casas[lin][col];
    }

}
