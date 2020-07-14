package pl.bykowski.sza6homeworkclient;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAKeyUtility {

    public static Map<String, Object> getRSAKeys() {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);

            // generate the key pair
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            // create KeyFactory and RSA Keys Specs
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

            // generate and retrieve RSA Keys from the KeyFactory
            RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            RSAPrivateKey privateRsaKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            System.out.println(privateRsaKey);

            Map<String, Object> keys = new HashMap<String, Object>();
            keys.put("private", privateRsaKey);
            keys.put("public", publicRsaKey);
            return keys;

        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (
                InvalidKeySpecException e) {
            e.printStackTrace();
        }

       return null;
    }
}
