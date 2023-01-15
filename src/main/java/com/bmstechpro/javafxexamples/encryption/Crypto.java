package com.bmstechpro.javafxexamples.encryption;
/* javafx-examples
 * @created 01/14/2023
 * @author Konstantin Staykov
 */

public interface Crypto {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] data);

}
