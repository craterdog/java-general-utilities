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
     * This function encodes a byte array using base 2 with no padding of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @return The base 2 encoded string.
     */
    static public String encode(byte[] bytes) {
        return encode(bytes, "");
    }


    /**
     * This function encodes a byte array using base 2 with a specific padding of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @param pad The padding of spaces to be inserted before each new line.
     * @return The base 2 encoded string.
     */
    static public String encode(byte[] bytes, String pad) {
        StringBuilder result = new StringBuilder();
        int length = bytes.length;
        if (length == 0) return "";  // empty byte array
        for (int i = 0; i < length; i++) {
            if (length > 10 && i % 10 == 0) {  // format to padded 80 character blocks
                result.append("\n");
                result.append(pad);
            }
            for (int j = 0; j < 8; j++) {
                int bit = (bytes[i] & (1 << (7 - j))) >>> (7 - j);
                result.append(bit);
            }
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
        byte[] bytes = new byte[(int) Math.ceil(length / 8)];
        for (int i = 0; i < bytes.length; i++) {
            int b = 0;
            for (int j = 0; j < 8; j++) {
                char character = string.charAt(i * 8 + j);
                int bit = lookupTable.indexOf(character);
                if (bit < 0) throw new NumberFormatException("Attempted to decode a string that is not base 2: " + string);
                b = (b << 1) | bit;
            }
            bytes[i] = (byte) b;
        }
        return bytes;
    }


    static private final String lookupTable = "01";


    private Base02Utils() {
    }

}
