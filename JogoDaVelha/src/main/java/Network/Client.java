/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network;

import Entities.Board;
import Entities.JogoDaVelha;
import Exceptions.PortaInvalida;
import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class Client {

    Component parentComponent = new Component() {
    };
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private DataInputStream ler;
    private DataOutputStream enviar;
    private int dado;

    public void getServerIP(){
        
        serverIP = JOptionPane.showInputDialog("Insira o IP do host");
        
    }
    
    public void getServerPort() {

        try {
        serverPort = Integer.parseInt(JOptionPane.showInputDialog("Insira a porta do host"));
        if(serverPort < 0 || serverPort > 65536) {
            throw new PortaInvalida();
        } 
        } catch (PortaInvalida ex){
            serverPort = 0;
            JOptionPane.showConfirmDialog(parentComponent, ex.getMessage());
            getServerPort();
        }

    }

    public void connect() throws IOException {

        try {
            socket = new Socket(serverIP, serverPort);

            System.out.println("Conexão realizada!");

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(parentComponent, "Não foi possível se conectar!");
            connect();
        }

    }

    public void sendData(int dado) throws IOException {

        enviar = new DataOutputStream(socket.getOutputStream());
        enviar.writeInt(dado);

    }

    public int readData() throws IOException {

        ler = new DataInputStream(socket.getInputStream());
        dado = ler.readInt();
        return dado;

    }

    public void lerJogada(Board tabuleiro) throws IOException {
        
        
        ler = new DataInputStream(socket.getInputStream());
        dado = ler.readInt();

        if (dado == 1) {

            tabuleiro.setCasa(0, 0, -1);

        } else if (dado == 2) {

            tabuleiro.setCasa(0, 1, -1);

        } else if (dado == 3) {

            tabuleiro.setCasa(0, 2, -1);

        } else if (dado == 4) {

            tabuleiro.setCasa(1, 0, -1);

        } else if (dado == 5) {

            tabuleiro.setCasa(1, 1, -1);

        } else if (dado == 6) {

            tabuleiro.setCasa(1, 2, -1);

        } else if (dado == 7) {

            tabuleiro.setCasa(2, 0, -1);

        } else if (dado == 8) {

            tabuleiro.setCasa(2, 1, -1);

        } else if (dado == 9) {

            tabuleiro.setCasa(2, 2, -1);

        }
    }
}
