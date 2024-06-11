package com.example.GalagaX.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/users/createUser";


    public static void saveUser(User user) throws IOException {
        try {
            // Configuración de la conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            // Convertir el objeto User a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(user);

            // Escribir el JSON en el cuerpo de la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificar la respuesta del servidor
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(conn.getInputStream(), "utf-8")) {
                    while (scanner.hasNext()) {
                        System.out.println(scanner.nextLine());
                    }
                }
            } else {
                System.out.println("Failed to save user. Response code: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
