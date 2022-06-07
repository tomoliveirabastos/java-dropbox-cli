package services;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HandleDropbox {
    public FileMetadata upload(AccessTokenPayloadDAO accessTokenPayloadDAO, String path, String key) throws IOException, DbxException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("tom").build();
        DbxClientV2 client = new DbxClientV2(config, accessTokenPayloadDAO.getAccess_token());

        InputStream in = new FileInputStream(path);
        System.out.println(path);

        FileMetadata metadata = client.files().uploadBuilder(key).uploadAndFinish(in);

        return metadata;
    }
}
