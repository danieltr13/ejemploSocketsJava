/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ob;

import java.io.Serializable;
import ob.ClienteR;

/**
 *
 * @author MSI GF63
 */
public class Casa implements Serializable {

    private static final long serialVersionUID = 2798469644607187767L;
    
    private String color;
    //private int presio;
    
    public Casa(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "";
    }

    
}
