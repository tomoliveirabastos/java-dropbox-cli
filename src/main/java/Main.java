import services.AccessToken;
import services.AccessTokenPayloadDAO;
import services.HandleDropbox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        AccessToken g = new AccessToken();
        AccessTokenPayloadDAO accessTokenPayloadDAO = g.getAccessTokenDAO();
        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        HandleDropbox handleDropbox = new HandleDropbox();

        String hash = generateRandomString(200);

        try{
            String fullFilePath = currentPath + "/pom.xml";

            var fileMetaData = handleDropbox.upload(accessTokenPayloadDAO, fullFilePath, "/"+hash + ".xml");

            System.out.println(fileMetaData);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String generateRandomString(int length) {
        String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
        String digits = "1234567890";
        String seedChars = asciiUpperCase + asciiLowerCase + digits;

        StringBuilder sb = new StringBuilder();

        SecureRandom rand = new SecureRandom();

        for(int i = 0; i < length; i++){
            sb.append(seedChars.charAt(rand.nextInt(seedChars.length())));
        }

        return sb.toString();
    }
}
