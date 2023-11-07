package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            // creao un socket / collegamento al server
            Socket socket = new Socket("localhost", 3000);
            // creo i buffer e stream per comunicare
            DataOutputStream outVersoServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String rigaRitornata;
            String scelta="";
            do {
                // leggo la scelta dalla tastiera
                System.out.println("Inserire D(Disponibilità biglietti) A(Acquisto biglietto) Q(Esci)");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                // scelta
                scelta=in.readLine();
                // invio la scelta al server
                outVersoServer.writeBytes(scelta + "\n");
                // aspetto la risposta dal server
                rigaRitornata = inDalServer.readLine();
                
            } while (!scelta.equals("Q"));
            // stampo
            System.out.println("\n\nFine " + rigaRitornata);
            // chiudo il socket (chiuso dal client!)
            socket.close();

        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}