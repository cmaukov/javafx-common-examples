package com.bmstechpro.javafxexamples.encryption;
/* javafx-examples
 * @created 01/14/2023
 * @author Konstantin Staykov
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CryptoApp {
    public static void main(String[] args) throws IOException {
        Crypto crypto = new BasicCrypto();
        String data = "Hello World!";
        String encrypted = new String(crypto.encrypt(data.getBytes()));
        String decrypted = new String(crypto.decrypt(encrypted.getBytes()));

        System.out.println("data = " + data);
        System.out.println("encrypted = " + encrypted);
        System.out.println("decrypted = " + decrypted);


        byte[] bytes = Files.readAllBytes(Path.of("orig.xlsx"));
        Path dataEnc = Files.write(Path.of("orig_enc.xlsx"), crypto.encrypt(bytes), StandardOpenOption.CREATE);
//        Path dataEnc = Path.of("orig_enc.xlsx");

        Files.write(Path.of("orig_dec.xlsx"), crypto.decrypt(Files.readAllBytes(dataEnc)), StandardOpenOption.CREATE);
    }
}
