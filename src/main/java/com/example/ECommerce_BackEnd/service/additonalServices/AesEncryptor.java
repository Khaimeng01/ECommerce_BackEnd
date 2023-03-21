package com.example.ECommerce_BackEnd.service.additonalServices;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class AesEncryptor implements AttributeConverter<Object,String> {

    private final String encryptionKey ="this-is-test-key";
    private final String encryptionCipher="AES";

    private static Key key;
    private static Cipher cipher;

    private Key getKey() {
        if(key == null){
            key = new SecretKeySpec(encryptionKey.getBytes(),encryptionCipher);
        }
        return key;
    }

    private Cipher getCipher() throws GeneralSecurityException {
        if(cipher==null){
            cipher = Cipher.getInstance(encryptionCipher);
        }
        return cipher;
    }

    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode,getKey());
    }

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object o) {
        if(o == null){
            return  null;
        }
        initCipher(Cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(o);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }

    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        initCipher(Cipher.DECRYPT_MODE);
        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(s));
        return SerializationUtils.deserialize(bytes);
    }
}
