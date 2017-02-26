package com.mirage.services.security;

/**
 * Created by Mirage on 24/02/2017.
 */
public interface EncryptionService {

    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
