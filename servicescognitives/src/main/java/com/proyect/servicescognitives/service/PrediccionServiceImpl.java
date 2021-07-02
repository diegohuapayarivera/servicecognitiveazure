package com.proyect.servicescognitives.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.servicescognitives.config.HttpClientConfig;
import com.proyect.servicescognitives.model.PrediccionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PrediccionServiceImpl extends HttpClientConfig implements PrediccionService {

    @Autowired
    private ObjectMapper objectMapper;

    String URL =
            "https://southcentralus.api.cognitive.microsoft.com/customvision/v3.0/Prediction/26eab674-6cdd-44bd-b102-1a7387c4995b/classify/iterations/Iteration1/url?Prediction-Key=c5228402306c4ce28d9b10e43093a74f&Content-Type=application/json";

    @Override
    public HttpResponse<String> predecir(PrediccionBody prediccionBody) throws IOException, InterruptedException{
        String json = objectMapper.writeValueAsString(prediccionBody);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();
        //HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
