/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BEJobApplication.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class GoogleAuthService {

    private final String CLIENT_ID = "426309553735-kpe1v03uuk21meijnmmf72qlojmltham.apps.googleusercontent.com";

    public GoogleIdToken.Payload verifyGoogleToken(String idTokenString) {
        try {
            System.out.println("Received idToken: " + idTokenString);
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance()) // dùng Gson thay thế
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .setIssuer("https://accounts.google.com")
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            System.out.println("Received idToken: " + idToken);
            if (idToken != null) {
                return idToken.getPayload();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
