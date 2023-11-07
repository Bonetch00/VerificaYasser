package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadGioco extends Thread {
    Socket client;
    int contatore;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ThreadGioco(Socket client, int cont) {
        this.client = client;
        this.contatore = cont;
    }

    public void run() {
        int contatore = this.contatore;
        String let;
        try {
            System.out.println("sono collegato al client: " + client.getInetAddress() + " biglietti:"+contatore);
            // creao i tubi
            this.inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.outVersoClient = new DataOutputStream(client.getOutputStream());
            // invio al client
            do {
                // leggo la lettera dal client
                let = inDalClient.readLine();
                String daRitornare = "";
                
                switch (let) {
                    case "D":
                        daRitornare=daRitornare+"Biglietti di sponibili: "+contatore+"";
                        break;

                    case "A":
                        if(contatore>0){
                            contatore=contatore-1;
                            daRitornare="Hai acquistato un biglietto, ora ci sono "+contatore+" biglietti";
                        }
                        else{
                            daRitornare="SOLD OUT: biglietti esauriti";
                        }
                    break;

                    case "Q":
                        daRitornare="Esci";
                        daRitornare=daRitornare+": il cliente "+client.getInetAddress()+" è uscito";
                    break;
                    default:
                        break;
                }
                outVersoClient.writeBytes(daRitornare + "\n");
                System.out.println(daRitornare);
            } while (!let.equals("Q"));
        } 
        catch (Exception e) {
            System.out.println("\t\terrore nella comunicazione: " + e.getMessage());
        }
    }

    
}
