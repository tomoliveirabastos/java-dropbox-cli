package services;

import io.github.cdimascio.dotenv.Dotenv;

public class Enviroment {

    private String key;
    private String secret;
    private String refreshToken;

    public Enviroment(){
        Dotenv dotenv = Dotenv.load();

        this.key = dotenv.get("KEY");
        this.secret = dotenv.get("SECRET");
        this.refreshToken = dotenv.get("REFRESH_TOKEN");
    }

    public String getKey() {
        return this.key;
    }

    public String getSecret() {
        return this.secret;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }
}
