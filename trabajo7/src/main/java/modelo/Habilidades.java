/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author diego
 */
public class Habilidades {
    private int IDHAB;
    private String NOMHAB;
    private int IDPOK;
    private Pokemon pokemon;
    
    

    public int getIDHAB() {
        return IDHAB;
    }

    public void setIDHAB(int IDHAB) {
        this.IDHAB = IDHAB;
    }

    public String getNOMHAB() {
        return NOMHAB;
    }

    public void setNOMHAB(String NOMHAB) {
        this.NOMHAB = NOMHAB;
    }
    
}
