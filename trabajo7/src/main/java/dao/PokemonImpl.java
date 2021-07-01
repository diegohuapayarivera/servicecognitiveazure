/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Habilidades;
import modelo.Pokemon;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author diego
 */
public class PokemonImpl extends Conexion {

    public void registrarPokemon(Pokemon modelo) {
        String sql1 = "insert into pokemon (NOMPOK,APOPOK,EXPBASPOK,ALTPOK,PESPOK,IMGFROPOK,IMGATRPOK)\n"
                + "values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = this.conectar().prepareStatement(sql1);
            ps.setString(1, modelo.getNOMPOK());
            ps.setString(2, modelo.getAPOPOK());
            ps.setDouble(3, modelo.getEXPHABPOK());
            ps.setDouble(4, modelo.getALTPOK());
            ps.setDouble(5, modelo.getPESPOK());
            ps.setString(6, modelo.getIMGFROPOK());
            ps.setString(7, modelo.getIMGATRPOK());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error al guardar Pokemon con Habilidades: " + e.getMessage());
        }

    }

    public void buscarPok(Pokemon pok) throws Exception {
//        String leerdnipok = String.parser(pok.getIDPOK());
        int idpok = pok.getIDPOK();
//        https://dni.optimizeperu.com/api/persons/60323413?format=json
//        String enlace = "https://dni.optimizeperu.com/api/persons/"+ leerdni +"?format=json";
        String enlace = "https://pokeapi.co/api/v2/pokemon/" + idpok;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
//                String apellido_paterno = rootobj.get("first_name").getAsString();
                String nompok = rootobj.get("name").getAsString();
//                String apellido_materno = rootobj.get("last_name").getAsString();
//                String nombres = rootobj.get("name").getAsString();
//                txtsalida.setText("");
//                txtsalida.append(apellido_paterno+"\n"+apellido_materno+"\n"+nombres+"\n");
                System.out.println("Resultado\n");
                System.out.println(nompok + "\n");

//                per.setNOMPER(rootobj.get("nombres").getAsString());
//                per.setAPEPATPER(rootobj.get("apellido_paterno").getAsString());
//                per.setAPEMATERPER(rootobj.get("apellido_materno").getAsString());
                pok.setNOMPOK(nompok);
//                per.setNOMPER(nombres);
//                per.setAPEPATPER(apellido_paterno);
//                per.setAPEMATPER(apellido_materno);
            }
        } catch (Exception ex) {
            System.out.println("mensaje: " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
        }
    }
}
