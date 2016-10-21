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

/**
 * This utility class provides functions for encoding and decoding byte arrays
 * using base 2.  The character set used for the encoding includes the following
 * characters: 0..1
 *
 * @author Derk Norton
 */
public final class Base02Utils {

    /**
     * This function encodes a byte array using base 2 with no indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @return The base 2 encoded string.
     */
    static public String encode(byte[] bytes) {
        return encode(bytes, null);
    }


    /**
     * This function encodes a byte array using base 2 with a specific indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @param indentation The indentation string to be inserted before each new line.
     * @return The base 2 encoded string.
     */
    static public String encode(byte[] bytes, String indentation) {
        StringBuilder result = new StringBuilder();
        int length = bytes.length;
        if (length == 0) return "";  // empty byte array
        if (indentation != null) result.append(indentation);
        encodeByte(bytes, 0, result);
        for (int i = 1; i < length; i++) {
            if (i % 10 == 0) {
                // format to indented 80 character blocks
                result.append("\n");
                if (indentation != null) result.append(indentation);
            }
            encodeByte(bytes, i, result);
        }
        return result.toString();
    }


    /**
     * This function decodes a base 2 string into its corresponding byte array.
     *
     * @param base2 The base 2 encoded string.
     * @return The corresponding byte array.
     */
    static public byte[] decode(String base2) {
        String string = base2.replaceAll("\\s", "");  // remove all white space
        int length = string.length();
        byte[] bytes = new byte[(int) Math.ceil(length / 8.0)];
        for (int i = 0; i < bytes.length; i++) {
            decodeByte(string, i, bytes);
        }
        return bytes;
    }


    static private final String lookupTable = "01";

    static private void encodeByte(byte[] bytes, int byteIndex, StringBuilder result) {
        for (int i = 0; i < 8; i++) {
            int bit = (bytes[byteIndex] & (1 << (7 - i))) >>> (7 - i);
            result.append(bit);
        }
    }


    static private void decodeByte(String string, int byteIndex, byte[] bytes) {
        int b = 0;
        for (int i = 0; i < 8; i++) {
            char character = string.charAt(byteIndex * 8 + i);
            int bit = lookupTable.indexOf(character);
            if (bit < 0) throw new NumberFormatException("Attempted to decode a string that is not base 2: " + string);
            b = (b << 1) | bit;
        }
        bytes[byteIndex] = (byte) b;
    }


    private Base02Utils() {
    }

}
