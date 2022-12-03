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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class Server {

    Component parentComponent = new Component() {
    };

    private int port;
    private DataOutputStream enviar;
    private DataInputStream ler;
    private Socket socket;
    private ServerSocket server;
    private int dado;

    public void getPort() {

        try {
            
        port = Integer.parseInt(JOptionPane.showInputDialog("Insira a porta que utilizará!"));
        if(port < 0 || port > 65536) {
            throw new PortaInvalida();
        }
        } catch (PortaInvalida ex) {
            port = 0;
            JOptionPane.showConfirmDialog(parentComponent, ex.getMessage());
            getPort();
        }

    }

    public void runServer() throws IOException {

        try {

            server = new ServerSocket(port);
            socket = server.accept();

            System.out.println("Servidor aberto!");

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(parentComponent, "Não foi possível executar o servidor!");
            runServer();
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

            tabuleiro.setCasa(0, 0, -2);

        } else if (dado == 2) {

            tabuleiro.setCasa(0, 1, -2);

        } else if (dado == 3) {

            tabuleiro.setCasa(0, 2, -2);

        } else if (dado == 4) {

            tabuleiro.setCasa(1, 0, -2);

        } else if (dado == 5) {

            tabuleiro.setCasa(1, 1, -2);

        } else if (dado == 6) {

            tabuleiro.setCasa(1, 2, -2);

        } else if (dado == 7) {

            tabuleiro.setCasa(2, 0, -2);

        } else if (dado == 8) {

            tabuleiro.setCasa(2, 1, -2);

        } else if (dado == 9) {

            tabuleiro.setCasa(2, 2, -2);

        }

    }

}
