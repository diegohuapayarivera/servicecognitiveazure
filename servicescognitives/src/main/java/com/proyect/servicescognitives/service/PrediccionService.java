package com.proyect.servicescognitives.service;

import com.proyect.servicescognitives.model.PrediccionBody;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface PrediccionService {

    public HttpResponse<String> predecir(PrediccionBody prediccionBody) throws IOException, InterruptedException;
}
