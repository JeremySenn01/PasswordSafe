package ch.bbw.PasswordSafe.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.bbw.PasswordSafe.dao.Dao;
import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.model.User;

@Service
@SessionScope
public class AuthenticationService {

	@Autowired
	private Dao dao;
	@Autowired
	private PasswordService pwService;
	
	private int currentUserId;
	private byte[] key;

	public boolean signInUser(Credentials credentials) {
		String hash = hashPw(credentials.getPassword());

		System.out.println("hash: " + hash);

		Optional<User> possibleUser = dao.getUserByCredentials(hash, credentials.getUsername());

		if (possibleUser.isPresent()) {
			User foundUser = possibleUser.get();
			this.currentUserId = foundUser.getId();
			this.setKey(hash);
			
			//if User hasn't got any entries, then nothing has to be decrypted
			if (foundUser.getEntries() == null) {
				this.pwService.setEntries(new ArrayList<Entry>());
				System.out.println("entries was null....");
			}
			else {
				List<Entry> entriesDecrypted = this.decryptData(foundUser.getEntries().getBytes());
				this.pwService.setEntries(entriesDecrypted);
				System.out.println("decrypted: " + entriesDecrypted.get(0) + " / " + entriesDecrypted);		
			}
		}
		return possibleUser.isPresent();
	}

	public void logout() {
		//There is a logged in user
		if (this.currentUserId != 0) {
			List<Entry> decryptedEntries = pwService.getAllEntries();
			byte[] encryptedEntries = this.encryptData(decryptedEntries);
			this.dao.updateEntries(encryptedEntries, this.currentUserId);
			this.resetUserData();
		}
	}
	
	private void resetUserData() {
		this.currentUserId = 0;
		this.key = null;
	}
	
	/**
	 * This method uses MD5 to shorten the master password to 128 bits, so it has
	 * the correct length of an AES-Key
	 * 
	 * @param pwHash - hash of master pw
	 * @return byte[]
	 */
	private void setKey(String pwHash) {
		byte[] pwHashBytes = pwHash.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.key = md.digest(pwHashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private List<Entry> decryptData(byte[] encryptedEntries) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Cipher c = Cipher.getInstance("AES");
			SecretKeySpec k = new SecretKeySpec(this.key, "AES");
			c.init(Cipher.DECRYPT_MODE, k);
			byte[] decryptedEntries = c.doFinal(encryptedEntries);
			String jsonString = new String(decryptedEntries);
			return mapper.readValue(jsonString, new TypeReference<List<Entry>>() {
			});

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("decrypt failure");
		return null;
	}

	private byte[] encryptData(List<Entry> entries) {
		// list to json string
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(entries);
			byte[] byteStream = jsonString.getBytes(StandardCharsets.UTF_8);

			Cipher c = Cipher.getInstance("AES");
			SecretKeySpec k = new SecretKeySpec(this.key, "AES");
			c.init(Cipher.ENCRYPT_MODE, k);
			return c.doFinal(byteStream);

		} catch (JsonProcessingException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException
				| InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("encrypt failure");

		return null;
	}

	public static String hashPw(String pw) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pw.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
