/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import dao.PokemonImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Habilidades;
import modelo.Pokemon;
import service.ObtenerPokemon;

/**
 *
 * @author diego
 */
@Named(value = "pokemonC")
@SessionScoped
public class PokemonC implements Serializable {

    private Pokemon pokemonObtenido = new Pokemon();
    private List<Habilidades> habilidadesObtenida = new ArrayList<>();
private PokemonImpl dao = new PokemonImpl();
    
    private String nombre = "";
    private ObtenerPokemon op = new ObtenerPokemon();
    private Apires a = new Apires();
    
    private String pokemon = "";

    @PostConstruct
    public void init() {
        try {
//            listarPokemonObtenido();
//            nombrepokemon();
//            listarPokemonObtenido();
        } catch (Exception e) {
        }
    }

    public void obtenerNombre() {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(a.datosAPI());
            pokemonObtenido.setNOMPOK(jsonObject.get("name").getAsString());
            System.out.println("nombre: " + pokemonObtenido.getNOMPOK());
        } catch (Exception e) {
            System.out.println("Error al traer nombre: " + e.getMessage());
        }
    }

    public String datosAPI() throws IOException, InterruptedException {
        Random r = new Random();
        int idRandom = r.nextInt(255);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + idRandom))
                //                .uri(URI.create("https://api.reniec.cloud/dni/" + DNI + ""))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
    
    public void prueba2(){
        try {
            JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(datosAPI());

//        persona.setNOMPER(jsonObject.get("nombres").getAsString());
//        persona.setAPEPER((jsonObject.get("apellidoPaterno").getAsString())
//                +(jsonObject.get("apellidoMaterno").getAsString()));
//        persona.setDNIPER(jsonObject.get("dni").getAsInt());
        String nombrePokemon = jsonObject.get("base_experience").getAsString();
        JsonArray jsonArray = jsonObject.getAsJsonArray("abilities");
        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            JsonObject abilidad = (JsonObject) object.get("ability");
            String abilidaString = abilidad.get("name").getAsString();
            System.out.println("Este su abilidad: " + abilidaString);
        }
        System.out.println("Este es el pojemon: " + nombrePokemon);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Random r = new Random();
        int idRandom = r.nextInt(255);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + idRandom))
                //                .uri(URI.create("https://api.reniec.cloud/dni/" + DNI + ""))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        String element = response.body();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(element);
        String nompok = jsonObject.get("name").getAsString();
        System.out.println("Este es el nombre: " + nompok);
//        pokemonObtenido.setNOMPOK(nompok);
//        System.out.println("modelo: " + pokemonObtenido.toString());
    }
    public void listarPokemonObtenido() {

        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(a.datosAPI());
            nombre = jsonObject.get("name").getAsString();
//            pokemonObtenido.setNOMPOK(jsonObject.get("name").getAsString());
//            pokemonObtenido.setEXPHABPOKString(jsonObject.get("base_experience").getAsString());
//            pokemonObtenido.setALTPOKString(jsonObject.get("height").getAsString());
//            pokemonObtenido.setPESPOKString(jsonObject.get("weight").getAsString());
//            JsonObject stripes = jsonObject.get("sprites").getAsJsonObject();
//            pokemonObtenido.setIMGFROPOK(stripes.get("front_default").getAsString());
//            pokemonObtenido.setIMGATRPOK(stripes.get("back_default").getAsString());
//            JsonArray jsonArray = jsonObject.getAsJsonArray("abilities");
//            Habilidades h = new Habilidades();
//            for (JsonElement jsonElement : jsonArray) {
//                JsonObject ability = jsonElement.getAsJsonObject();
//                JsonObject abilidad = (JsonObject) ability.get("ability");
//                String abilidaString = abilidad.get("name").getAsString();
//                h.setNOMHAB(abilidad.get("name").getAsString());
//                habilidadesObtenida.add(h);
//                System.out.println("Este su abilidad: " + abilidaString);
//            }
            System.out.println("nombre: " + nombre);
        } catch (Exception e) {
            System.out.println("Error al listar Pokemon Obtenido");
        }
    }

    public void retornarPokemon(){
        try {
            dao.buscarPok(pokemonObtenido);
        } catch (Exception e) {
            System.out.println("Error al retornar: " + e.getMessage());    
        }
    }
    public void nombrepokemon() throws Exception {
//        String leerdni = per.getDNIPER();
        String enlace = "https://pokeapi.co/api/v2/pokemon/25";
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
                nombre = rootobj.get("name").getAsString();
//                String apellido_materno = rootobj.get("apellido_materno").getAsString();
//                String nombres = rootobj.get("nombres").getAsString();
//                txtsalida.setText("");
//                txtsalida.append(apellido_paterno+"\n"+apellido_materno+"\n"+nombres+"\n");
//                System.out.println("Resultado\n");
//                System.out.println(apellido_paterno + "\n" + apellido_materno + "\n" + nombres + "\n");

//                per.setNOMPER(rootobj.get("nombres").getAsString());
//                per.setAPEPATPER(rootobj.get("apellido_paterno").getAsString());
//                per.setAPEMATERPER(rootobj.get("apellido_materno").getAsString());
//                per.setNOMPER(nombres);
//                per.setAPEPATPER(apellido_paterno);
//                per.setAPEMATPER(apellido_materno);
            }
        } catch (Exception ex) {
            System.out.println("error aqui :V: " + ex.getMessage());
            System.out.println(ex.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
        }
    }

    //Getters y Setters
    public Pokemon getPokemonObtenido() {
        return pokemonObtenido;
    }

    public void setPokemonObtenido(Pokemon pokemonObtenido) {
        this.pokemonObtenido = pokemonObtenido;
    }

    public List<Habilidades> getHabilidadesObtenida() {
        return habilidadesObtenida;
    }

    public void setHabilidadesObtenida(List<Habilidades> habilidadesObtenida) {
        this.habilidadesObtenida = habilidadesObtenida;
    }

    public ObtenerPokemon getOp() {
        return op;
    }

    public void setOp(ObtenerPokemon op) {
        this.op = op;
    }

    public Apires getA() {
        return a;
    }

    public void setA(Apires a) {
        this.a = a;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

}
