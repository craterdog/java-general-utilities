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
 * using base 16.  The character set used for the encoding includes the following
 * characters: 0..9 A..F
 *
 * @author Derk Norton
 */
public final class Base16Utils {

    /**
     * This function encodes a byte array using base 16 with no indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @return The base 16 encoded string.
     */
    static public String encode(byte[] bytes) {
        return encode(bytes, null);
    }


    /**
     * This function encodes a byte array using base 16 with a specific indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @param indentation The indentation string to be inserted before each new line.
     * @return The base 16 encoded string.
     */
    static public String encode(byte[] bytes, String indentation) {
        StringBuilder result = new StringBuilder();
        int length = bytes.length;
        if (length == 0) return "";  // empty byte array
        for (int i = 0; i < length; i++) {
            if (indentation != null && length > 40 && i % 40 == 0) {
                // format to indented 80 character blocks
                result.append("\n");
                result.append(indentation);
            }
            int nibble = (bytes[i] & 0xF0) >>> 4;
            result.append(lookupTable.charAt(nibble));
            nibble = bytes[i] & 0x0F;
            result.append(lookupTable.charAt(nibble));
        }
        return result.toString();
    }


    /**
     * This function decodes a base 16 string into its corresponding byte array.
     *
     * @param base16 The base 16 encoded string.
     * @return The corresponding byte array.
     */
    static public byte[] decode(String base16) {
        String string = base16.replaceAll("\\s", "");  // remove all white space
        int length = string.length();
        byte[] bytes = new byte[(int) Math.ceil(length / 2.0)];
        for (int i = 0; i < bytes.length; i++) {
            char firstCharacter = string.charAt(i * 2);
            int firstNibble = lookupTable.indexOf((int) firstCharacter);
            if (firstNibble < 0) throw new NumberFormatException("Attempted to decode a string that is not base 16: " + string);
            char secondCharacter = string.charAt(i * 2 + 1);
            int secondNibble = lookupTable.indexOf((int) secondCharacter);
            if (secondNibble < 0) throw new NumberFormatException("Attempted to decode a string that is not base 16: " + string);
            bytes[i] = (byte) ((firstNibble << 4) | secondNibble);
        }
        return bytes;
    }


    static private final String lookupTable = "0123456789ABCDEF";


    private Base16Utils() {
    }

}
