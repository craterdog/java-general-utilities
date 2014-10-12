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
 * using base 32.  The character set used for the encoding includes the following
 * characters: 0..9 A..D F..H J..N P..T V..Z
 *
 * @author Derk Norton
 */
public final class Base32Utils {

    /**
     * This function encodes a byte array using base 32 with no indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @return The base 32 encoded string.
     */
    static public String encode(byte[] bytes) {
        return encode(bytes, "");
    }


    /**
     * This function encodes a byte array using base 32 with a specific indentation of new lines.
     *
     * @param bytes The byte array to be encoded.
     * @param indentation The indentation string to be inserted before each new line.
     * @return The base 32 encoded string.
     */
    static public String encode(byte[] bytes, String indentation) {
        StringBuilder result = new StringBuilder();
        int length = bytes.length;
        if (length == 0) return "";  // empty byte array
        if (length > 50) {
            result.append("\n");
            result.append(indentation);
        }
        encodeBytes(bytes[0], bytes[0], 0, result);
        for (int i = 1; i < length; i++) {
            if (i % 50 == 0) {  // format to indented 80 character blocks
                result.append("\n");
                result.append(indentation);
            }
            encodeBytes(bytes[i - 1], bytes[i], i, result);
        }
        encodeLastByte(bytes[length - 1], length - 1, result);
        return result.toString();
    }


    /**
     * This function decodes a base 32 string into its corresponding byte array.
     *
     * @param base32 The base 32 encoded string.
     * @return The corresponding byte array.
     */
    static public byte[] decode(String base32) {
        String string = base32.replaceAll("\\s", "");  // remove all white space
        int length = string.length();
        byte[] bytes = new byte[length * 5 / 8];
        for (int i = 0; i < length - 1; i++) {
            char character = string.charAt(i);
            byte chunk = (byte) lookupTable.indexOf((int) character);
            if (chunk < 0) throw new NumberFormatException("Attempted to decode a string that is not base 32: " + string);
            decodeCharacter(chunk, i, bytes, 0);
        }
        if (length > 0) {
            char character = string.charAt(length - 1);
            byte chunk = (byte) lookupTable.indexOf((int) character);
            if (chunk < 0) throw new NumberFormatException("Attempted to decode a string that is not base 32: " + string);
            decodeLastCharacter(chunk, length - 1, bytes, 0);
        }
        return bytes;
    }


    // offset:    0        1        2        3        4        0
    // byte:  00000111|11222223|33334444|45555566|66677777|...
    // mask:   F8  07  C0 3E  01 F0  0F 80  7C 03  E0  1F   F8  07
    static private void encodeBytes(byte previous, byte current, int byteIndex, StringBuilder output) {
        int chunk;
        int offset = byteIndex % 5;
        switch (offset) {
            case 0:
                chunk = ((current & 0xF8) >>> 3);
                output.append(lookupTable.charAt(chunk));
                break;
            case 1:
                chunk = (((previous & 0x07) << 2) | ((current & 0xC0) >>> 6));
                output.append(lookupTable.charAt(chunk));
                chunk = ((current & 0x3E) >>> 1);
                output.append(lookupTable.charAt(chunk));
                break;
            case 2:
                chunk = (((previous & 0x01) << 4) | ((current & 0xF0) >>> 4));
                output.append(lookupTable.charAt(chunk));
                break;
            case 3:
                chunk = (((previous & 0x0F) << 1) | ((current & 0x80) >>> 7));
                output.append(lookupTable.charAt(chunk));
                chunk = ((current & 0x7C) >>> 2);
                output.append(lookupTable.charAt(chunk));
                break;
            case 4:
                chunk = (((previous & 0x03) << 3) | ((current & 0xE0) >>> 5));
                output.append(lookupTable.charAt(chunk));
                chunk = (current & 0x1F);
                output.append(lookupTable.charAt(chunk));
                break;
        }
    }


    // same as normal, but pad with 0's in "next" byte
    // case:      0        1        2        3        4
    // byte:  xxxxx111|00xxxxx3|00004444|0xxxxx66|00077777|...
    // mask:   F8  07  C0 3E  01 F0  0F 80  7C 03  E0  1F
    static private void encodeLastByte(byte last, int byteIndex, StringBuilder output) {
        int chunk;
        int offset = byteIndex % 5;
        switch (offset) {
            case 0:
                chunk = ((last & 0x07) << 2);
                output.append(lookupTable.charAt(chunk));
                break;
            case 1:
                chunk = ((last & 0x01) << 4);
                output.append(lookupTable.charAt(chunk));
                break;
            case 2:
                chunk = ((last & 0x0F) << 1);
                output.append(lookupTable.charAt(chunk));
                break;
            case 3:
                chunk = ((last & 0x03) << 3);
                output.append(lookupTable.charAt(chunk));
                break;
            case 4:
                break;
        }
    }


    // offset:    0        1        2        3        4        0
    // byte:  00000111|11222223|33334444|45555566|66677777|...
    // mask:   F8  07  C0 3E  01 F0  0F 80  7C 03  E0  1F   F8  07
    static private void decodeCharacter(byte chunk, int characterIndex, byte[] bytes, int index) {
        int byteIndex = index + (characterIndex * 5) / 8;
        int offset = characterIndex % 8;
        switch (offset) {
            case 0:
                bytes[byteIndex] |= chunk << 3;
                break;
            case 1:
                bytes[byteIndex] |= chunk >>> 2;
                bytes[byteIndex + 1] |= chunk << 6;
                break;
            case 2:
                bytes[byteIndex] |= chunk << 1;
                break;
            case 3:
                bytes[byteIndex] |= chunk >>> 4;
                bytes[byteIndex + 1] |= chunk << 4;
                break;
            case 4:
                bytes[byteIndex] |= chunk >>> 1;
                bytes[byteIndex + 1] |= chunk << 7;
                break;
            case 5:
                bytes[byteIndex] |= chunk << 2;
                break;
            case 6:
                bytes[byteIndex] |= chunk >>> 3;
                bytes[byteIndex + 1] |= chunk << 5;
                break;
            case 7:
                bytes[byteIndex] |= chunk;
                break;
        }
    }


    // same as normal, but pad with 0's in "next" byte
    // case:      0        1        2        3        4
    // byte:  xxxxx111|00xxxxx3|00004444|0xxxxx66|00077777|...
    // mask:   F8  07  C0 3E  01 F0  0F 80  7C 03  E0  1F
    static private void decodeLastCharacter(byte chunk, int characterIndex, byte[] bytes, int index) {
        int byteIndex = index + (characterIndex * 5) / 8;
        int offset = characterIndex % 8;
        switch (offset) {
            case 1:
                bytes[byteIndex] |= chunk >>> 2;
                break;
            case 3:
                bytes[byteIndex] |= chunk >>> 4;
                break;
            case 4:
                bytes[byteIndex] |= chunk >>> 1;
                break;
            case 6:
                bytes[byteIndex] |= chunk >>> 3;
                break;
            case 7:
                bytes[byteIndex] |= chunk;
                break;
        }
    }


    // eliminate 4 vowels ("E", "I", "O", "U") to reduce confusion with
    // 0 and O, 1 and I; and reduce the likelihood of *actual*
    // (potentially offensive) words from being generated
    static private final String lookupTable = "0123456789ABCDFGHJKLMNPQRSTVWXYZ";


    private Base32Utils() {
    }

}
