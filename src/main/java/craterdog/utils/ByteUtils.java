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

import java.math.*;
import java.util.Arrays;


/**
 * This utility class defines functions that manipulate bytes and byte arrays in useful ways.
 *
 * @author Derk Norton
 */
public final class ByteUtils {

    // Boolean Conversions

    /**
     * This function converts a boolean to its corresponding byte array format.
     *
     * @param b The boolean to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] booleanToBytes(boolean b) {
        byte[] buffer = new byte[1];
        booleanToBytes(b, buffer);
        return buffer;
    }


    /**
     * This function converts a boolean into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param b The boolean to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int booleanToBytes(boolean b, byte[] buffer) {
        return booleanToBytes(b, buffer, 0);
    }


    /**
     * This function converts a boolean into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param b The boolean to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int booleanToBytes(boolean b, byte[] buffer, int index) {
        int length = 1;
        buffer[index] = (byte) (b ? 0xFF : 0x00);
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding boolean value.
     *
     * @param buffer The byte array containing the boolean.
     * @return The corresponding boolean value.
     */
    static public boolean bytesToBoolean(byte[] buffer) {
        return bytesToBoolean(buffer, 0);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding boolean value.
     *
     * @param buffer The byte array containing the boolean.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding boolean value.
     */
    static public boolean bytesToBoolean(byte[] buffer, int index) {
        return buffer[index] != 0;
    }


    // Short Conversions

    /**
     * This function converts a short to its corresponding byte array format.
     *
     * @param s The short to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] shortToBytes(short s) {
        byte[] buffer = new byte[2];
        shortToBytes(s, buffer, 0);
        return buffer;
    }


    /**
     * This function converts a short into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param s The short to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int shortToBytes(short s, byte[] buffer) {
        return shortToBytes(s, buffer, 0);
    }


    /**
     * This function converts a short into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param s The short to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int shortToBytes(short s, byte[] buffer, int index) {
        int length = 2;
        for (int i = 0; i < length; i++) {
            buffer[index + length - i - 1] = (byte) (s >> (i * 8));
        }
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding short value.
     *
     * @param buffer The byte array containing the short.
     * @return The corresponding short value.
     */
    static public short bytesToShort(byte[] buffer) {
        return bytesToShort(buffer, 0);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding short value.
     *
     * @param buffer The byte array containing the short.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding short value.
     */
    static public short bytesToShort(byte[] buffer, int index) {
        int length = 2;
        short integer = 0;
        for (int i = 0; i < length; i++) {
            integer |= ((buffer[index + length - i - 1] & 0xFF) << (i * 8));
        }
        return integer;
    }


    // Integer Conversions

    /**
     * This function converts a integer to its corresponding byte array format.
     *
     * @param i The integer to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] intToBytes(int i) {
        byte[] buffer = new byte[4];
        intToBytes(i, buffer, 0);
        return buffer;
    }


    /**
     * This function converts a integer into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param i The integer to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int intToBytes(int i, byte[] buffer) {
        return intToBytes(i, buffer, 0);
    }


    /**
     * This function converts a integer into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param i The integer to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int intToBytes(int i, byte[] buffer, int index) {
        int length = buffer.length - index;
        if (length > 4) length = 4;
        for (int j = 0; j < length; j++) {
            buffer[index + length - j - 1] = (byte) (i >> (j * 8));
        }
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding integer value.
     *
     * @param buffer The byte array containing the integer.
     * @return The corresponding integer value.
     */
    static public int bytesToInt(byte[] buffer) {
        return bytesToInt(buffer, 0);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding integer value.
     *
     * @param buffer The byte array containing the integer.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding integer value.
     */
    static public int bytesToInt(byte[] buffer, int index) {
        int length = buffer.length - index;
        if (length > 4) length = 4;
        int integer = 0;
        for (int i = 0; i < length; i++) {
            integer |= ((buffer[index + length - i - 1] & 0xFF) << (i * 8));
        }
        return integer;
    }


    // Long Integer Conversions

    /**
     * This function converts a long to its corresponding byte array format.
     *
     * @param l The long to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] longToBytes(long l) {
        byte[] buffer = new byte[8];
        longToBytes(l, buffer, 0);
        return buffer;
    }


    /**
     * This function converts a long into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param l The long to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int longToBytes(long l, byte[] buffer) {
        return longToBytes(l, buffer, 0);
    }


    /**
     * This function converts a long into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param l The long to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int longToBytes(long l, byte[] buffer, int index) {
        int length = buffer.length - index;
        if (length > 8) length = 8;
        for (int i = 0; i < length; i++) {
            buffer[index + length - i - 1] = (byte) (l >> (i * 8));
        }
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding long value.
     *
     * @param buffer The byte array containing the long.
     * @return The corresponding long value.
     */
    static public long bytesToLong(byte[] buffer) {
        return bytesToLong(buffer, 0);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding long value.
     *
     * @param buffer The byte array containing the long.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding long value.
     */
    static public long bytesToLong(byte[] buffer, int index) {
        int length = buffer.length - index;
        if (length > 8) length = 8;
        long l = 0;
        for (int i = 0; i < length; i++) {
            l |= ((buffer[index + length - i - 1] & 0xFFL) << (i * 8));
        }
        return l;
    }


    // Big Integer Conversions

    /**
     * This function converts a big integer to its corresponding byte array format.
     *
     * @param integer The big integer to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] bigIntegerToBytes(BigInteger integer) {
        return integer.toByteArray();
    }


    /**
     * This function converts a big integer into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param integer The big integer to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int bigIntegerToBytes(BigInteger integer, byte[] buffer) {
        return bigIntegerToBytes(integer, buffer, 0);
    }


    /**
     * This function converts a big integer into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param integer The big integer to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int bigIntegerToBytes(BigInteger integer, byte[] buffer, int index) {
        int length = 4 + (integer.bitLength() + 8) / 8;
        System.arraycopy(intToBytes(length), 0, buffer, index, 4);  // copy in the length
        index += 4;
        System.arraycopy(integer.toByteArray(), 0, buffer, index, length - 4);  // copy in the big integer
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding big integer value.
     *
     * @param buffer The byte array containing the big integer.
     * @return The corresponding big integer value.
     */
    static public BigInteger bytesToBigInteger(byte[] buffer) {
        return new BigInteger(buffer);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding big integer value.
     *
     * @param buffer The byte array containing the big integer.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding big integer value.
     */
    static public BigInteger bytesToBigInteger(byte[] buffer, int index) {
        int length = bytesToInt(buffer, index);  // pull out the length of the big integer
        index += 4;
        byte[] bytes = new byte[length];
        System.arraycopy(buffer, index, bytes, 0, length);  // pull out the bytes for the big integer
        return new BigInteger(bytes);
    }


    // Double Precision Floating Point Conversions

    /**
     * This function converts a double to its corresponding byte array format.
     *
     * @param d The double to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] doubleToBytes(double d) {
        byte[] buffer = new byte[8];
        doubleToBytes(d, buffer, 0);
        return buffer;
    }


    /**
     * This function converts a double into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param d The double to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int doubleToBytes(double d, byte[] buffer) {
        return doubleToBytes(d, buffer, 0);
    }


    /**
     * This function converts a double into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param s The double to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int doubleToBytes(double s, byte[] buffer, int index) {
        long bits = Double.doubleToRawLongBits(s);
        int length = longToBytes(bits, buffer, index);
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding double value.
     *
     * @param buffer The byte array containing the double.
     * @return The corresponding double value.
     */
    static public double bytesToDouble(byte[] buffer) {
        return bytesToDouble(buffer, 0);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding double value.
     *
     * @param buffer The byte array containing the double.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding double value.
     */
    static public double bytesToDouble(byte[] buffer, int index) {
        double real;
        long bits = bytesToLong(buffer, index);
        real = Double.longBitsToDouble(bits);
        return real;
    }


    // Big Floating Point Conversions

    /**
     * This function converts a big decimal to its corresponding byte array format.
     *
     * @param decimal The big decimal to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] bigDecimalToBytes(BigDecimal decimal) {
        String string = decimal.toString();
        return stringToBytes(string);
    }


    /**
     * This function converts a big decimal into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param decimal The big decimal to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int bigDecimalToBytes(BigDecimal decimal, byte[] buffer) {
        return bigDecimalToBytes(decimal, buffer, 0);
    }


    /**
     * This function converts a big decimal into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param decimal The big decimal to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int bigDecimalToBytes(BigDecimal decimal, byte[] buffer, int index) {
        BigInteger intVal = decimal.unscaledValue();
        int length = 12 + (intVal.bitLength() + 8) / 8;

        int scale = decimal.scale();
        System.arraycopy(intToBytes(scale), 0, buffer, index, 4);  // copy in the scale
        index += 4;

        int precision = decimal.precision();
        System.arraycopy(intToBytes(precision), 0, buffer, index, 4);  // copy in the scale
        index += 4;

        System.arraycopy(bigIntegerToBytes(intVal), 0, buffer, index, length - 8);     // copy in the big integer
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding big decimal value.
     *
     * @param buffer The byte array containing the big decimal.
     * @return The corresponding big decimal value.
     */
    static public BigDecimal bytesToBigDecimal(byte[] buffer) {
        String string = bytesToString(buffer);
        return new BigDecimal(string);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding big decimal value.
     *
     * @param buffer The byte array containing the big decimal.
     * @param index The index for the first byte in the byte array.
     * @return The corresponding big decimal value.
     */
    static public BigDecimal bytesToBigDecimal(byte[] buffer, int index) {
        int scale = bytesToInt(buffer, index);
        index += 4;
        int precision = bytesToInt(buffer, index);
        index += 4;
        BigInteger intVal = bytesToBigInteger(buffer, index);
        return new BigDecimal(intVal, scale, new MathContext(precision));
    }


    // String Conversions

    /**
     * This function converts a string to its corresponding byte array format.
     *
     * @param string The string to be converted.
     * @return The corresponding byte array.
     */
    static public byte[] stringToBytes(String string) {
        return string.getBytes();
    }


    /**
     * This function converts a string into its corresponding byte format and inserts
     * it into the specified buffer.
     *
     * @param string The string to be converted.
     * @param buffer The byte array.
     * @return The number of bytes inserted.
     */
    static public int stringToBytes(String string, byte[] buffer) {
        return stringToBytes(string, buffer, 0);
    }


    /**
     * This function converts a string into its corresponding byte format and inserts
     * it into the specified buffer at the specified index.
     *
     * @param string The string to be converted.
     * @param buffer The byte array.
     * @param index The index in the array to begin inserting bytes.
     * @return The number of bytes inserted.
     */
    static public int stringToBytes(String string, byte[] buffer, int index) {
        byte[] bytes = string.getBytes();
        int length = bytes.length;
        System.arraycopy(bytes, 0, buffer, index, length);
        return length;
    }


    /**
     * This function converts the bytes in a byte array to its corresponding string value.
     *
     * @param buffer The byte array containing the string.
     * @return The corresponding string value.
     */
    static public String bytesToString(byte[] buffer) {
        return bytesToString(buffer, 0, buffer.length);
    }


    /**
     * This function converts the bytes in a byte array at the specified index to its
     * corresponding string value.
     *
     * @param buffer The byte array containing the string.
     * @param index The index for the first byte in the byte array.
     * @param length The number of bytes that make up the string.
     * @return The corresponding string value.
     */
    static public String bytesToString(byte[] buffer, int index, int length) {
        return new String(buffer, index, length);
    }


    // Miscellaneous

    /**
     * This function converts a byte into its unsigned integer form.
     *
     * @param b The byte to be converted.
     * @return The unsigned version of the byte as an integer.
     */
    static public int byteToUnsigned(byte b) {
        int unsigned = b & 0xFF;
        return unsigned;
    }


    /**
     * This function calculates a hash code for the specified byte array.
     *
     * @param bytes The byte array.
     * @return The hash code.
     */
    static public int hashCode(byte[] bytes) {
        int hash = Arrays.hashCode(bytes);
        return hash;
    }


    /**
     * This function creates a copy of a byte array.
     *
     * @param bytes The byte array.
     * @return
     */
    static public byte[] copy(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);
        return result;
    }


    /**
     * This function compares two byte arrays to see if they are equal.
     *
     * @param first The first byte array.
     * @param second The second byte array.
     * @return Whether or not the two byte arrays are equal.
     */
    static public boolean equals(byte[] first, byte[] second) {
        return Arrays.equals(first, second);
    }


    /**
     * This function compares two byte arrays for canonical ordering.
     *
     * @param first The first byte array.
     * @param second The second byte array.
     * @return The signum result of the comparison.
     */
    static public int compare(byte[] first, byte[] second) {
        // choose the shorter array length
        int firstLength = first.length;
        int secondLength = second.length;
        int shorterLength = Math.min(firstLength, secondLength);

        // compare matching bytes
        for (int i = 0; i < shorterLength; i++) {
            int result = Byte.compare(first[i], second[i]);
            if (result != 0) return Integer.signum(result);
        }

        // compare lengths if bytes match
        if (firstLength < secondLength) return -1;
        if (firstLength > secondLength) return 1;

        // must be same length and equal
        return 0;
    }


    private ByteUtils() {
        // should never be called!
    }

}
