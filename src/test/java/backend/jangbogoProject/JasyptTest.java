package backend.jangbogoProject;

import backend.jangbogoProject.config.JasyptConfig;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(value = "jasypt.encryptor.password=test", classes = JasyptConfig.class)
public class JasyptTest {

    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Test
    void jasypt() {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(encryptKey);

        String test = "test";

        System.out.println(encryptor.encrypt(test));
    }
}
