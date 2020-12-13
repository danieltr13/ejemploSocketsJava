/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.util.xml.impl.Input;
import ob.Casa;

/**
 *
 * @author MSI GF63
 */
public class ClienteServer extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
//
//    BufferedReader in;
//    PrintWriter out;
    Socket socket;
    Server server;
    boolean shouldRun = true;

    public ClienteServer(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.inicializar();
    }

    public void inicializar() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //si no jala agregarlo a la lista inicializarlo aquí
        } catch (IOException ex) {
            Logger.getLogger(ClienteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            Object obj;
            while (shouldRun) {                
                try {
                    if((obj=in.readObject())!=null){
                        if(server.consultarColor()){
                            Casa casa= (Casa)obj;
                            server.setColor(casa.getColor());
                            this.avisarClientes(obj);
                        }else{
                            Casa casa= new Casa(server.getColor());
                            this.avisarCliente(casa);
                        }
                        
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void avisarClientes(Object obj) {
        for (ClienteServer cliente : server.getClientes()){
            cliente.avisarCliente(obj);
            //System.out.println("Innnnngresó");
        }
    }

    private void avisarCliente(Object obj) {
        try {
            //out.println(obj.trim().toUpperCase());
            out.writeObject(obj);
        } catch (IOException ex) {
            Logger.getLogger(ClienteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
