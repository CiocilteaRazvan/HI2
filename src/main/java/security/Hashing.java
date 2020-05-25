package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    private static String byteArrayToHexString(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String sha256(String string)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] byteHash = md.digest(string.getBytes(StandardCharsets.UTF_8));

            return byteArrayToHexString(byteHash);
        }catch (NoSuchAlgorithmException e)
        {
            System.out.println("Algorithm does not exist");
            return "";
        }
    }
}