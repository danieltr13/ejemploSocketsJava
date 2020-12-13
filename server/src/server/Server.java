/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public class Server {
    List<ClienteServer>clientes;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        this.clientes= new ArrayList<>();
        ServerSocket servidor;
        try {
            servidor = new ServerSocket(4000);
            System.out.println("Listo para conectarse......");
            while(true){
                Socket cliente = new Socket();
                cliente = servidor.accept();
                ClienteServer clienteServer = new ClienteServer(cliente, this);
                clienteServer.start();
                System.out.println("Hilo Conectado");
                clientes.add(clienteServer);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ClienteServer> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteServer> clientes) {
        this.clientes = clientes;
    }
    
    String color=null;
    public boolean consultarColor(){
        return color==null;
    }
  
    public String getColor(){
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
