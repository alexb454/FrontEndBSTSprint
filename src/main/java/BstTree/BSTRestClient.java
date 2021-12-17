package BstTree;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BSTRestClient {
    public static void postedInputToDatabase(int[] inputArray) throws JsonProcessingException {
        String input = Arrays.toString(inputArray);
        Map<Object, Object> bst = new HashMap<>();
        bst.put("input", input);

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted.writeValueAsString(bst);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/bst"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println("Your input has been put into the database!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
