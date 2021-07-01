/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author diego
 */
public class Apires {

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

    public static void main(String[] args) throws IOException, InterruptedException {
        String nombrePokemon = "";
        List<String> listaAbilidades = null;
        int id = 0;
        Apires a = new Apires();
        Random r = new Random();
        int numeroRandon = r.nextInt(255);
        System.out.println("Numero ramdon: " + numeroRandon);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(a.datosAPI());

//        persona.setNOMPER(jsonObject.get("nombres").getAsString());
//        persona.setAPEPER((jsonObject.get("apellidoPaterno").getAsString())
//                +(jsonObject.get("apellidoMaterno").getAsString()));
//        persona.setDNIPER(jsonObject.get("dni").getAsInt());
        nombrePokemon = jsonObject.get("name").getAsString();
        JsonArray jsonArray = jsonObject.getAsJsonArray("abilities");
        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            JsonObject abilidad = (JsonObject) object.get("ability");
            String abilidaString = abilidad.get("name").getAsString();
            System.out.println("Este su abilidad: " + abilidaString);
        }
        System.out.println("Este es el pojemon: " + nombrePokemon);
//        String responder = a.datosAPI();
//        System.out.println("este es responder: " + responder);
    }
}
