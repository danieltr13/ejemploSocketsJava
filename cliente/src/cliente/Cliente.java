/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.sun.security.ntlm.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import ob.Casa;

/**
 *
 * @author MSI GF63
 */
public class Cliente implements Runnable {

    Socket cliente;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Observer observer;
    private CasaFrame casaFrame;
    
    public Cliente(CasaFrame casa) {
        this.casaFrame=casa;
    }

    public void conectar() {
        try {
            if (this.cliente == null) {
                this.cliente = new Socket("localhost", 4000);
                this.out = new ObjectOutputStream(cliente.getOutputStream());
                this.in = new ObjectInputStream(cliente.getInputStream());
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try{
                Casa casa;
                if((casa=(Casa)in.readObject())!=null){
                    this.notificar(casa);
                }
            }catch(Exception e){
                System.out.println("Error");
            }
        }
    }
    
    public void notificar(Casa casa){
        if(casa!=null){
            casaFrame.notificar(casa);
        }
    }
    
    public void enviar(Casa casa){
        if(this.cliente.isConnected()){
            try {
                out.writeObject(casa);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
   
    @Override
    public void run() {
        this.conectar();
    }

    public void prueba() {
        try {

            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            BufferedReader ingresado = new BufferedReader(new InputStreamReader(System.in));

            String cadena, eco = "";

            System.out.print("Introduce Cadena:");

            cadena = ingresado.readLine();

            while (!cadena.endsWith("*")) {
                out.println(cadena);
                eco = in.readLine();
                System.out.println("eco: " + eco);
                System.out.print("Introduce Cadena :");
                cadena = ingresado.readLine();
            }

            out.close();
            in.close();
            ingresado.close();
            System.out.println("Fin de ingresado");
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
