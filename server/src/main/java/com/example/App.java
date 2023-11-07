package com.example;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            // creao un socket
            ServerSocket serverSocket = new ServerSocket(3000);
            // accetto il client che si collega
            while (true) {
                // apro il socket di ascolto
                Socket client = serverSocket.accept();
                int cont=5;
                // passo tutto alla classe ThreadGioco
                ThreadGioco th = new ThreadGioco(client, cont);
                th.start();
            }
        } catch (Exception e) {
            System.out.println("\nErrore");
        }
    }
}