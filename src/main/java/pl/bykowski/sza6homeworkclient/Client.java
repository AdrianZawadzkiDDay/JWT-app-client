package pl.bykowski.sza6homeworkclient;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Controller
public class Client {

    @EventListener(ApplicationReadyEvent.class)
    public void start () {
        Map<String, Object> keys = new HashMap<String, Object>();
        keys = RSAKeyUtility.getRSAKeys();


        RestTemplate restTemplate = new RestTemplate();
        String url =  "http://localhost:8080/users";

        String jwt = JWTGenerator.getJwtToken((RSAPublicKey)keys.get("public"), (RSAPrivateKey) keys.get("private"));

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Authorization", jwt);

        Base64.Encoder encoder = Base64.getEncoder();
        RSAPublicKey publicKey = (RSAPublicKey)keys.get("public");
        header.add("Certification", encoder.encodeToString(publicKey.getEncoded()));
        HttpEntity httpEntity = new HttpEntity(header);

        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(resp);
    }


}
