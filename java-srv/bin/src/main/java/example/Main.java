package example;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * This is the Main class.
 */
public class Main {

    private static final Argon2 argon2 = Argon2Factory.create();

    public static void main(String[] args) {

        String plainText = "Hello World!";
        String stored = hashPsw(plainText);
        boolean isMatch = checkPsw(plainText, stored);

        System.out.println("Original: " + plainText);
        System.out.println("Hash: " + stored);
        System.out.println("Verified: " + isMatch);
    }

    private static String hashPsw(String plainText) {
        return hashPassword(plainText);
    }
    private static boolean checkPsw(String plainText, String hashedStr) {
        //String stored = hashPsw(plainText);
        //System.out.println("Hash: " + stored);
        //return stored.equals(hashedStr);
        return argon2.verify(hashedStr, plainText.toCharArray());
    }

    private static String hashPassword( final String plainText ) {
      char[] passwordChars = plainText.toCharArray();
      String hash = argon2.hash(22, 65536, 1, passwordChars);
      argon2.wipeArray(passwordChars);
      return hash;
    }
}
