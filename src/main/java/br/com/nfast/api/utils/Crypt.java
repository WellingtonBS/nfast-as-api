package br.com.nfast.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;

public class Crypt {

    /*public static void main(String[] args) {
        System.out.println(Crypt.decrypt("NgpF64zT7WOWZsI2LotDo1d9U5VL4gvtY/2VGKD4ousinQ83wG1IXsx1J88nLLPRgFs/AtpPBw/6VoSVB6Dlda0HiNM="));
    }*/

    private static final String SECRET_DEF = "alwayslookonthebrightsideoflife";
    private static SecretKeyFactory SECRET_KEY_FACTORY = null;

    public static String md5(Object value) {
        return DigestUtils.md5Hex(value.toString()).toLowerCase();
    }

    public static String encrypt(String value) {
        return encrypt(value, SECRET_DEF);
    }

    public static String decrypt(String value) {
        return decrypt(value, SECRET_DEF);
    }

    public static String encrypt(String value, String secret) {
        try {
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            byte[] saltBytes = bytes;
            SecretKeyFactory factory = getSecretKeyFactory();
            PBEKeySpec spec = new PBEKeySpec(secret.toCharArray(), saltBytes, 65556, 256);
            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            AlgorithmParameters params = cipher.getParameters();
            byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] encryptedTextBytes = cipher.doFinal(value.getBytes("UTF-8"));

            byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
            System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
            System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
            System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
            return Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String value, String secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(value));
            byte[] saltBytes = new byte[20];
            buffer.get(saltBytes, 0, saltBytes.length);
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            buffer.get(ivBytes, 0, ivBytes.length);
            byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];

            buffer.get(encryptedTextBytes);

            SecretKeyFactory factory = getSecretKeyFactory();
            PBEKeySpec spec = new PBEKeySpec(secret.toCharArray(), saltBytes, 65556, 256);
            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            return new String(decryptedTextBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SecretKeyFactory getSecretKeyFactory() {
        try {
            if (SECRET_KEY_FACTORY == null)
                SECRET_KEY_FACTORY = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return SECRET_KEY_FACTORY;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
