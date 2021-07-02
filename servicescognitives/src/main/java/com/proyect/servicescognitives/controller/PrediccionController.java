package com.proyect.servicescognitives.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.servicescognitives.dto.PrediccionDTO;
import com.proyect.servicescognitives.model.PrediccionBody;
import com.proyect.servicescognitives.service.PrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PrediccionController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PrediccionService prediccionService;

    @PostMapping("/predecir")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> predecir(@RequestBody PrediccionBody prediccionBody) throws IOException, InterruptedException {
        String response = prediccionService.predecir(prediccionBody).body();
        PrediccionDTO prediccionDTO = objectMapper.readValue(response, PrediccionDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(prediccionDTO);
    }
}
