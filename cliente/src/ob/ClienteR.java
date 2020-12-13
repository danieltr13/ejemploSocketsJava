/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ob;

import java.io.Serializable;

/**
 *
 * @author MSI GF63
 */
public class ClienteR implements Serializable{

    private static final long serialVersionUID = 26142526171407900L;
    
    private String nombre;
    private int edad;

    public ClienteR(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }
    
    
}
