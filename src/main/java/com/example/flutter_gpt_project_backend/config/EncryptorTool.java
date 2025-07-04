package com.example.flutter_gpt_project_backend.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Base64;

public class EncryptorTool {
    public static void main(String[] args){


        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        String encryptorPassword = System.getenv("jwt_encryptor_passwords");

        if( encryptorPassword == null || encryptorPassword.isEmpty()){
            throw new IllegalStateException("jwt_encryptor_password is empty");
        }

        encryptor.setPassword(encryptorPassword);
        String encrypted=encryptor.encrypt(encryptorPassword);
        System.out.println("Encrypted password: " + encrypted);
    }
}
