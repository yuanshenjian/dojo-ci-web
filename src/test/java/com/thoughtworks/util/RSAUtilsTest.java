package com.thoughtworks.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RSAUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtilsTest.class);

    private static KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    @BeforeClass
    public static void setup() {
        keyPair = RSAUtils.generateKeyPair(RSAUtils.KEY_SIZE_2048);
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        LOGGER.info("Public Key = {}, Private Key = {}", publicKey, privateKey);
    }

    @Test
    public void should_generate_keypair_when_default_size() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        assertNotNull(keyPair);
        assertNotNull(publicKey);
        assertNotNull(privateKey);
    }

    @Test
    public void should_generate_key_pair() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair(RSAUtils.KEY_SIZE_2048);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        assertNotNull(keyPair);
        assertNotNull(publicKey);
        assertNotNull(privateKey);
    }

    @Test
    public void should_get_base64_public_key() throws Exception {
        String publicKeyString = RSAUtils.getBase64PublicKey(publicKey);
        assertEquals(publicKey, RSAUtils.getPublicKey(publicKeyString));
    }

    @Test
    public void should_get_base64_private_key() throws Exception {
        String privateKeyString = RSAUtils.getBase64PrivateKey(privateKey);
        assertEquals(privateKey, RSAUtils.getPrivateKey(privateKeyString));
    }

    @Test
    public void should_get_public_key_from_big_integer() throws Exception {
        BigInteger modulus = new BigInteger(publicKey.getEncoded());
        BigInteger exponent = new BigInteger("65573");
        assertNotNull(RSAUtils.getPublicKey(modulus, exponent));
    }

    @Test
    public void should_get_private_key_from_big_integer() throws Exception {
        BigInteger modulus = new BigInteger(privateKey.getEncoded());
        BigInteger exponent = new BigInteger("65573");
        assertNotNull(RSAUtils.getPrivateKey(modulus, exponent));
    }

    @Test
    public void should_encrypt_as_byte_array() throws Exception {
        String encryptData = "Chinese people";
        LOGGER.info("Encrypt data = {}", encryptData);
        byte[] encryptedData = RSAUtils.encryptAsByteArray(encryptData, publicKey);
        String decryptData = RSAUtils.decrypt(encryptedData, privateKey);
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }

    @Test
    public void should_encrypt_as_string() throws Exception {
        String encryptData = "Chinese people";
        LOGGER.info("Encrypt data = {}", encryptData);
        String encryptedData = RSAUtils.encryptAsString(encryptData, publicKey);
        String decryptData = RSAUtils.decrypt(encryptedData, privateKey);
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }
}