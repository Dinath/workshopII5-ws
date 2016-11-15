/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
public class M {

    /**
     *
     * @param from the class where the msg came from
     * @param msg the message to display in the log
     */
    public static void debug(String from, String msg) {
        if (R.DEBUG) {
            System.out.println("DEBUG FROM " + from + "() : " + msg);
        }
    }

    /**
     * Encode the user password to SHA-256
     *
     * @param s the clear passwd
     * @return a new based encrypted string
     */
    public static String passwordEncode(String s) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes());
            return new String(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
