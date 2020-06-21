package ch.bbw.PasswordSafe.service;

import ch.bbw.PasswordSafe.dao.Dao;
import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class AuthenticationService {

    @Autowired
    private Dao dao;

    private int currentUserId;
    private List<Entry> decryptedEntries;

    public Optional<User> signInUser(Credentials credentials) {
        String salt = BCrypt.gensalt(5);
        String hash = BCrypt.hashpw(credentials.getPassword(), salt);

        Optional<User> possibleUser = dao.getUserByCredentials(hash, credentials.getUsername());

        if (possibleUser.isPresent()) {
            User foundUser = possibleUser.get();
            this.currentUserId = foundUser.getId();
        }
        return Optional.empty();
    }

    /**
     * This method uses MD5 to shorten the master password to 128 bits,
     * so it has the correct length of an AES-Key
     * @param pwHash - hash of master pw
     * @return byte[]
     */
    private byte[] getKey(String pwHash) {
        byte[] pwHashBytes = pwHash.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(pwHashBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    private List<Entry> decryptData(byte[] encryptedEntries, String pwHash) {
        try {
            byte[] key = this.getKey(pwHash);
            ObjectMapper mapper = new ObjectMapper();
            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] decryptedEntries = c.doFinal(encryptedEntries);
            String jsonString = new String(decryptedEntries);
            return mapper.readValue(jsonString, new TypeReference<List<Entry>>(){});

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] encryptData(List<Entry> entries, String pwHash) {
        //list to json string
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(entries);
            byte[] byteStream = jsonString.getBytes();
            byte[] key = this.getKey(pwHash);

            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            return c.doFinal(byteStream);

        } catch (JsonProcessingException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
