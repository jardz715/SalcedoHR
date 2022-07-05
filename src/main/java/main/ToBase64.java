package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

public class ToBase64 {
    
    public String encodeFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] encoded = Base64.getEncoder().encode(FileUtils.readFileToByteArray(file));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    public void decodeFile(byte[] encodedByte, String filePath) throws IOException {
        byte[] decoded = Base64.getDecoder().decode(encodedByte);
        File file = new File(filePath);
        file.setWritable(true);
        try (OutputStream stream = new FileOutputStream(file)) {
            stream.write(decoded);
        }
    }   
}
