/************************************************************************
 * Copyright (c) Crater Dog Technologies(TM).  All Rights Reserved.     *
 ************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.        *
 *                                                                      *
 * This code is free software; you can redistribute it and/or modify it *
 * under the terms of The MIT License (MIT), as published by the Open   *
 * Source Initiative. (See http://opensource.org/licenses/MIT)          *
 ************************************************************************/
package craterdog.utils;

import org.apache.commons.codec.binary.Base64;


/**
 * This utility class provides functions for encoding and decoding byte arrays
 * using base 64.  The character set used for the encoding includes the following
 * characters: 0..9 a..z A..Z + /
 *
 * @author Derk Norton
 */
public class Base64Utils {

    /**
     * This function encodes a byte array using base 64 with no indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @return The base 64 encoded string.
     */
    static public String encode(byte[] bytes) {
        return encode(bytes, "");
    }


    /**
     * This function encodes a byte array using base 64 with a specific indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @param indentation The indentation string to be inserted before each new line.
     * @return The base 64 encoded string.
     */
    public static String encode(byte[] bytes, String indentation) {
        String encoded = Base64.encodeBase64String(bytes).replaceAll("\\s", "");  // remove all white space
        StringBuilder result = new StringBuilder();
        if (encoded.length() > 80) {
            for (int c = 0; c < encoded.length(); c++) {
                if (c % 80 == 0) {
                    result.append("\n");
                    result.append(indentation);
                }
                result.append(encoded.charAt(c));
            }
        } else {
            result.append(encoded);
        }
        return result.toString();
    }


    /**
     * This function decodes a base 64 string into its corresponding byte array.
     *
     * @param base64 The base 64 encoded string.
     * @return The corresponding byte array.
     */
    public static byte[] decode(String base64) {
        byte[] result = Base64.decodeBase64(base64);
        return result;
    }


    private Base64Utils() {

    }

}
