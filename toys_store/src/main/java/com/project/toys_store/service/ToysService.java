package com.project.toys_store.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.toys_store.model.ToysModel;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ToysService {

    private final Map<String, ToysModel> toysDatabase = new HashMap<>();

    public ToysService() {
        initializeTestToys();
    }

    private void initializeTestToys() {
        ToysModel toy1 = new ToysModel();
        toy1.setName("Carrinho");
        toy1.setPrice(99.99F);

        ToysModel toy2 = new ToysModel();
        toy2.setName("Barbie");
        toy2.setPrice(59.99F);

        ToysModel toy3 = new ToysModel();
        toy3.setName("Lego");
        toy3.setPrice(129.99F);



        toysDatabase.put(toy1.getName(), toy1);
        toysDatabase.put(toy2.getName(), toy2);
        toysDatabase.put(toy3.getName(), toy3);
    }

    public String toysList() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(toysDatabase.values());
            return "Lista de Brinquedos: " + jsonBody;
        } catch (JsonProcessingException e) {
            return "Erro ao gerar a lista de brinquedos: " + e.getMessage();
        }
    }

    public String ToysPost(ToysModel body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonBody = objectMapper.writeValueAsString(body);

            return "Brinquedo Adicionado: " + jsonBody;
        } catch (JsonProcessingException e) {
            return "Erro na requisição: " + e.getMessage();
        }
    }

    public String ToysUp(String name , ToysModel updatedToy) {
        if (toysDatabase.containsKey(name)) {
            toysDatabase.put(name, updatedToy);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonBody = objectMapper.writeValueAsString(updatedToy);
                return "Brinquedo Atualizado: " + jsonBody;
            } catch (JsonProcessingException e) {
                return "Erro ao processar o corpo da requisição: " + e.getMessage();
            }
        } else {
            return "Brinquedo não encontrado para atualização.";
        }
    }

    public String ToysDel(String name) {
        if (toysDatabase.containsKey(name)) {
            toysDatabase.remove(name);
            return "Brinquedo Removido: " + name;
        } else {
            return "Brinquedo não encontrado para exclusão.";
        }
    }
}
