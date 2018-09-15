package com.twitter.oauth;

import com.google.api.client.auth.oauth.*;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class TwitterAuthenticator {


    private final String consumerKey;
    private final String consumerSecret;
    private HttpRequestFactory factory;

    @Autowired
    public TwitterAuthenticator(@Value("${twitter.consumerKey}") String consumerKey,
                                @Value("${twitter.consumerSecret}") String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
    private static final String ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
    private static final String REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token";

    public HttpRequestFactory getAuthorizedHttpRequestFactory() throws TwitterAuthenticationException {
        if (factory != null) {
            return factory;
        }
        synchronized (this) {
            OAuthHmacSigner signer = new OAuthHmacSigner();

            signer.clientSharedSecret = consumerSecret;

            OAuthGetTemporaryToken requestToken = new OAuthGetTemporaryToken(REQUEST_TOKEN_URL);
            requestToken.consumerKey = consumerKey;
            requestToken.transport = TRANSPORT;
            requestToken.signer = signer;

            OAuthCredentialsResponse requestTokenResponse;
            try {
                requestTokenResponse = requestToken.execute();
            } catch (IOException e) {
                throw new TwitterAuthenticationException("Unable to acquire a temporary token: " + e.getMessage(), e);
            }

            System.out.println("Acquired temporary token...\n");

            signer.tokenSharedSecret = requestTokenResponse.tokenSecret;

            OAuthAuthorizeTemporaryTokenUrl authorizeUrl = new OAuthAuthorizeTemporaryTokenUrl(AUTHORIZE_URL);
            authorizeUrl.temporaryToken = requestTokenResponse.token;

            String providedPin;

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Go to the following link in your browser:\n" + authorizeUrl.build());
                System.out.println("\nPlease enter the retrieved PIN:");
                providedPin = scanner.nextLine();
            }

            if (providedPin == null) {
                throw new TwitterAuthenticationException("Unable to read entered PIN");
            }

            OAuthGetAccessToken accessToken = new OAuthGetAccessToken(ACCESS_TOKEN_URL);
            accessToken.verifier = providedPin;
            accessToken.consumerKey = consumerSecret;
            accessToken.signer = signer;
            accessToken.transport = TRANSPORT;
            accessToken.temporaryToken = requestTokenResponse.token;


            OAuthCredentialsResponse accessTokenResponse;
            try {
                accessTokenResponse = accessToken.execute();
            } catch (IOException e) {
                throw new TwitterAuthenticationException("Unable to authorize access: " + e.getMessage(), e);
            }
            System.out.println("\nAuthorization was successful");

            signer.tokenSharedSecret = accessTokenResponse.tokenSecret;

            OAuthParameters parameters = new OAuthParameters();
            parameters.consumerKey = consumerKey;
            parameters.token = accessTokenResponse.token;
            parameters.signer = signer;

            factory = TRANSPORT.createRequestFactory(parameters);
            return factory;
        }
    }
}

