package com.bmstechpro.javafxexamples.encryption;
/* javafx-examples
 * @created 01/14/2023
 * @author Konstantin Staykov
 */

public class BasicCrypto implements Crypto {
    @Override
    public byte[] encrypt(byte[] data) {
        byte[] encrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            encrypted[i] = (byte) (i % 2 == 0 ? data[i] + 1 : data[i] - 1);
        }
        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] decrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            decrypted[i] = (byte) (i % 2 == 0 ? data[i] - 1 : data[i] + 1);
        }
        return decrypted;
    }
}
