package com.aluracursos.convertidormoneda.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_KEY = "3e306de0259b45a024f216e9";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public ApiResponse obtenerDatosAPI() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error en la solicitud HTTP: " + response.statusCode());
                return null;
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);

            if (!"success".equals(apiResponse.getResult())) {
                System.out.println("Error en los datos de la API.");
                return null;
            }

            return apiResponse;
        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
            return null;
        }
    }
}
