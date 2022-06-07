package services;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class AccessToken {

    private Enviroment enviroment;
    private Gson jsonParse;
    public AccessToken() {
        this.enviroment = new Enviroment();
        this.jsonParse = new Gson();
    }

    private HttpResponse getAccessToken() throws IOException, InterruptedException {

        var body = HttpRequest.BodyPublishers.ofString("grant_type=refresh_token&refresh_token="+this.enviroment.getRefreshToken());

        String url = "https://api.dropbox.com/oauth2/token";
        String stringKey = this.enviroment.getKey()+":"+this.enviroment.getSecret();

        String base64 = Base64.getEncoder().encodeToString(stringKey.getBytes());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(3))
                .POST(body)
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setHeader("Authorization", "Basic "+base64)
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public AccessTokenPayloadDAO getAccessTokenDAO() throws IOException, InterruptedException {
        String body = this.getAccessToken().body().toString();
        return this.jsonParse.fromJson(body, AccessTokenPayloadDAO.class);
    }
}
