package com.examen.sergio.examenpractico.Utils;

import android.util.Base64;

import com.mobapphome.mahencryptorlib.MAHEncryptor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by sergio on 13/07/17.
 */

public class Security {
    public static String encrypt(String input) {
        MAHEncryptor mahEncryptor = null;
        String encrypted = "";
        try {
            mahEncryptor = MAHEncryptor.newInstance("esta es la llave de encriptacion");
            encrypted = mahEncryptor.encode(input);

        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    public static String decrypt(String input) {
        String decrypted = "";
        MAHEncryptor mahEncryptor = null;
        try {
            mahEncryptor = MAHEncryptor.newInstance("esta es la llave de encriptacion");
            decrypted = mahEncryptor.decode(input);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        return decrypted;
    }
}
