package com.aluraChalenge.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class ConvierteDatos implements  IConvierteDatos{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public  <T> T obtenerDatos(String json, Class<T> clase){
        try {
            return mapper.readValue(json.toString(), clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
