package vn.com.telsoft.websheet.api.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CipherUtils {
    public static final Logger logger = LoggerFactory.getLogger(CipherUtils.class);

//    private static String ALGORITHM = "Blowfish";
//    private static String ALGORITHM_TRANSFORM = "Blowfish/CBC/PKCS5Padding";
//    private static String IV = "0102030405060708";
//    private static String KEY = "wapwap12wapwap12";

    private static String ALGORITHM;
    private static String ALGORITHM_TRANSFORM;
    private static String IV;
    private static String KEY;

    @Value("${ses.ws.cipher.algorithm}")
    protected void setAlgorithm(String algorithm) {
        CipherUtils.ALGORITHM = algorithm;
    }

    @Value("${ses.ws.cipher.algorithm_transform}")
    public void setAlgorithmTransform(String algorithmTransform) {
        CipherUtils.ALGORITHM_TRANSFORM = algorithmTransform;
    }

    @Value("${ses.ws.cipher.iv}")
    public void setIv(String iv) {
        CipherUtils.IV = iv;
    }

    @Value("${ses.ws.cipher.key}")
    public void setKey(String key) {
        CipherUtils.KEY = key;
    }

    public static String encrypt(String content) {
        String encCon = "";
        String ivStr = "";
        try {
            // Create new Blowfish cipher
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORM);

            //
            /*byte[] iv = new byte[cipher.getBlockSize()];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);*/
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decodeHex(IV.toCharArray()));

            String secret = URLEncoder.encode(content, StandardCharsets.UTF_8.name());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] encoding = cipher.doFinal(secret.getBytes(StandardCharsets.UTF_8));

//            logger.info("-- Encrypted -----------");
            encCon = URLEncoder.encode(DatatypeConverter.printBase64Binary(encoding), StandardCharsets.UTF_8.name());
//            ivStr = DatatypeConverter.printBase64Binary(iv);
            logger.info("-- encCon : " + encCon);
//            logger.info("-- iv : " + ivStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encCon;
    }

    public static String decrypt(String encContent) {
        String decCon = "";
        try {
            // Create new Blowfish cipher
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORM);
            //
            /*byte[] iv = new byte[cipher.getBlockSize()];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);*/
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decodeHex(IV.toCharArray()));

            // Decode Base64
            String secret = URLDecoder.decode(encContent, StandardCharsets.UTF_8.name());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] decoding = cipher.doFinal(DatatypeConverter.parseBase64Binary(secret));

//            logger.info("-- Decrypted -----------");
            decCon = URLDecoder.decode(new String(decoding, StandardCharsets.UTF_8), StandardCharsets.UTF_8.name());
            logger.info("-- decCon : " + decCon);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return decCon;
    }

    public static Map<String, String> getQueryParamsMap(String urlParams) {
        if (urlParams == null || urlParams.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> queryParams = new HashMap<>();
        try {
            String[] pairs = urlParams.split("&");
            for (String pair : pairs) {
                String[] keyValuePair = pair.split("=");

                queryParams.put(URLDecoder.decode(keyValuePair[0], StandardCharsets.UTF_8.name()),
                        URLDecoder.decode(keyValuePair[1], StandardCharsets.UTF_8.name()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return queryParams;
    }

    public static void main(String[] args) throws Exception {
//        String e = CipherUtils.encrypt("eid=89049032100005550100043500055345&primary-imsi=452021062010952&primary-msisdn=84918022686&application-category=gearsubscription&activation-mode=1&imei=352743110690350&device-type=Samsung Gear&is-pos=false&is-factory=true&paired-primary-count=0&paired-secondary-count=0&max-allowed-secondary-device-count=10&primary-imei=356116114088929&signup-status-code=7000&life-time=120&time-stamp=1624094329&device-id=356116114088929");
//        String d = JBlowfish.decrypt("8MRwSNXiXXYJNO8VFLebU7O6W8QcTQiSpjz6%2B154M1GM3VZDk6UW1v1u9HHyd12pL0ruahEx%2F3nJ%2F%2B2CDdxM0i4VNV9Fe0k0oxzoyr9t460EvIMN0Nxzqy%2FKYYuLMeoEyoWLAM62D0Y4U5A14unPJ414tnRI%2FgCW8w3NvLLA7OtNTsY%2Be%2BbEAMNeAYXzJLzERDAYMW9JXcAMlmhdpUsjtiG%2B%2FsbqsGKIKHN%2FkAOvkclHVERReXSbUQO2q45OMVy4En%2FMV79tMTlRSpsMDPUGPQy2Ds6TZ9JCb0YrvQEqa3bCf1ixo%2F68vu%2Bx1hm44WDcv5A9N%2FE5oqoZ%2FHaEP8SFaDGL7IUYxPGwpINMW3JieVcePibaBmbiYlEJ1OvdWXXiiT%2BElQNnomrD2JNXx4Q99UHFVo4qGej55MOJ%2BooPNguZ5io140Mc2UUaXVM7XL10mqWZ353effNnNF%2B37R7KFZ%2FToAgFazhKGbvyjYRSWT9CGCI9QqBno%2FGY1bChaUG5KAAuXnDVWt920AQVrYJIQV2Ds%2FdTGo7sEuF5PuhpR7299%2FQ5tJAC7K1OtTN3rWMAXl2XB%2FDYyA2mYgyyE%2Fky0nslkGVFvPjoJ%2BN4fqbrdr0gPXlRFIuMuRy%2FPRCCFjadzqVvVkw9BKpBNjGooXXoWg%3D%3D");
//        String d = CipherUtils.decrypt("phB3kvr0QGOkCYwlGcUPBIWuJXmF8DYqYix%2BB%2Fv3zYt4Qxa9aFRl4T%2BwUUEe6%2FGYvT2DRMfuqM0LEke5%2FUN77ISRtD0qRToAzwt%2FdQueiON%2FJmwn%2Bb6Fo%2FN5TA6kv2UoFZMHr2ndEyKfCiik4Vx89x77yu7kxMvqvW0k%2BzWjVQi9Q5m2%2BaRGt9HD8w3d6b4q6XYujDFOQz0bcm3kReK%2B6f0W6Sp%2F2g7G7iXvMvqLPmrDD0%2BrH%2FvQnRI7UiOOcmkpM9Hl3QL%2B0CJP69NdAv%2F0sn0KyCjs6z3bS6FQbwAeaF5iawc29JOinTGkbZi9IvIkE0fhJnuCROogrd8USnUANvuIEUy1MYoUv3s5KzJmQtcwaBUCqhXUHQWLDAADfmrDDSl%2FtmPPu9g5NxAHECJdPzPLuiu60d6KxLefqc2MRn%2FF%2F%2BJT6ZVvCfVf9YRRABJZNhQhRkEUDQxyeiE3A5rg0a3o9YncX7B%2FLHLEwjxOP45z%2FxgbnvYYqEc8tPojogG7o0ZgkSdHdVDx3MsX6SwJuqYvcTN6Vc%2BDU%2FSepIWtof4S1muzoeSIpYfI5CEDqj4B");
//        String d = CipherUtils.decrypt("phB3kvr0QGOkCYwlGcUPBIWuJXmF8DYqYix%2BB%2Fv3zYt4Qxa9aFRl4T%2BwUUEe6%2FGYvT2DRMfuqM0LEke5%2FUN77ISRtD0qRToAzwt%2FdQueiON%2FJmwn%2Bb6Fo%2FN5TA6kv2UoFZMHr2ndEyKfCiik4Vx89x77yu7kxMvqvW0k%2BzWjVQi9Q5m2%2BaRGt9HD8w3d6b4q6XYujDFOQz0bcm3kReK%2B6f0W6Sp%2F2g7G7iXvMvqLPmrDD0%2BrH%2FvQnRI7UiOOcmkpM9Hl3QL%2B0CJP69NdAv%2F0sn0KyCjs6z3bS6FQbwAeaF5iawc29JOinTGkbZi9IvIkE0fhJnuCROogrd8USnUANvuIEUy1MYoUv3s5KzJmQtcwaBUCqhXUHQWLDAADfmrDDSl%2FtmPPu9g5NxAHECJdPzPLuiu60d6KxLefqc2MRn%2FF%2F%2BJT6ZVvCfVf9YRRABJZNhQhRkEUDQxyeiE3A5rg0a3o9YncX7B%2FLHLEwjxOP45z%2FxgbnvYYqEc8tPojogG7o0ZgkSdHdVDNuRwoljoLPkZIc6evxogbo8qijrzjYTPtlD1N%2BpJeUWD%2BVjcR5RtU");
        String d = CipherUtils.decrypt("phB3kvr0QGNU1yWuAfY8DL%2Fmon%2BwHuDzc0%2BjgtXzTlwgPh0aVFLWUwL0lKqJL%2F5zxMKx%2FkP8DldTnpKUc2p7UcX%2Fxx12JsC6sSMSHQA4Pgzv%2B0pudK05lcEtr541wuLab8U%2B7OB%2FQ63aZYNNxiILyKMiiyGwFkTykvEJTuLvN3kfMZqSfL2mhoqPHHlTMo09AKd30wpe3%2B2LU8GWhMu3GVBz8qXPn1JD4DaIBwLQ6EJn5yJuUx0nQhBe0R%2BoMlE7nGLvw8x5B8lbDAG%2FLvIFoF6pFQYZyYaVClbgQucAypKxU7C7fMoBT6eAlqgPFM%2BQ3336bBGa0xbnJStm7WCLezWdTzZfCfecyTV7cMn9wiAcj3%2FSolcbl6adIif8WgdJXRic39pbbS9AcxCOhPfpwgcTjrdvbh%2BzgiqXe3eov17zdkltDoFvU0UMkHZtduKuOeVyKI9JbsINfveTCVZuFxWwPZEnt3ix%2F2QRXbq%2F%2FW3j%2BHfWjZqZ6GzshVFptTq2ekPTYmqLgcj6fcLBKCzZV3uVijImUVozS320wSz4WEw%3D");

    }
}