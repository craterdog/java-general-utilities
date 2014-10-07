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

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * This class performs unit tests on the <code>ByteUtils</code> class.
 *
 * @author Derk Norton
 */
public class ByteUtilsTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(ByteUtilsTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running ByteUtils Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed ByteUtils Unit Tests.\n");
    }

    /**
     * This method tests round-trip conversions of different primitive types.
     */
    @Test
    public void testRoundTripConversions() {
        logger.info("Beginning testRoundTripConversions()...");

        logger.info("  Testing boolean conversions...");
        boolean expectedBoolean = true;
        byte[] bytes = ByteUtils.booleanToBytes(expectedBoolean);
        boolean actualBoolean = ByteUtils.bytesToBoolean(bytes);
        assertEquals(expectedBoolean, actualBoolean);

        logger.info("  Testing short conversions...");
        short expectedShort = (short) 0xFACE;
        bytes = ByteUtils.shortToBytes(expectedShort);
        short actualShort = ByteUtils.bytesToShort(bytes);
        assertEquals(expectedShort, actualShort);

        logger.info("  Testing int conversions...");
        int expectedInt = 0x1234FACE;
        bytes = ByteUtils.intToBytes(expectedInt);
        int actualInt = ByteUtils.bytesToInt(bytes);
        assertEquals(expectedInt, actualInt);

        logger.info("  Testing long conversions...");
        long expectedLong = 0x012345FACE67L;
        bytes = ByteUtils.longToBytes(expectedLong);
        long actualLong = ByteUtils.bytesToLong(bytes);
        assertEquals(expectedLong, actualLong);

        logger.info("  Testing BigInteger conversions...");
        BigInteger expectedInteger = new BigInteger("123456789012345678901234567890");
        bytes = ByteUtils.bigIntegerToBytes(expectedInteger);
        BigInteger actualInteger = ByteUtils.bytesToBigInteger(bytes);
        assertEquals(expectedInteger, actualInteger);

        logger.info("  Testing double conversions...");
        double expectedDouble = Math.E;
        bytes = ByteUtils.doubleToBytes(expectedDouble);
        double actualDouble = ByteUtils.bytesToDouble(bytes);
        assertEquals(expectedDouble, actualDouble, 0.00000000000000001D);

        logger.info("  Testing BigDecimal conversions...");
        BigDecimal expectedDecimal = new BigDecimal("3.1415E2043");
        bytes = ByteUtils.bigDecimalToBytes(expectedDecimal);
        BigDecimal actualDecimal = ByteUtils.bytesToBigDecimal(bytes);
        assertEquals(expectedDecimal, actualDecimal);

        logger.info("  Testing String conversions...");
        String expectedString = "This is a test string with a \" in it.";
        bytes = ByteUtils.stringToBytes(expectedString);
        String actualString = ByteUtils.bytesToString(bytes);
        assertEquals(expectedString, actualString);

        logger.info("Completed testRoundTripConversions().\n");
    }

    /**
     * This method tests byte to unsigned byte conversions.
     */
    @Test
    public void testByteToUnsigned() {
        logger.info("Beginning testByteToUnsigned()...");

        logger.info("  Testing positive conversions...");
        byte signed = 123;
        int unsigned = ByteUtils.byteToUnsigned(signed);
        assertEquals(123, unsigned);

        logger.info("  Testing negative conversions...");
        signed = -1;
        unsigned = ByteUtils.byteToUnsigned(signed);
        assertEquals(255, unsigned);

        logger.info("Completed testByteToUnsigned().\n");
    }

    /**
     * This method tests hash code generation for different sized byte arrays.
     */
    @Test
    public void testHashCode() {
        logger.info("Beginning testHashCode()...");

        for (int i = 0; i < 10; i++) {
            byte[] bytes = RandomUtils.generateRandomBytes(i);
            int hashcode = ByteUtils.hashCode(bytes);
            logger.info("  The hashcode for " + i + " bytes: " + hashcode);
        }

        logger.info("Completed testHashCode().\n");
    }

    /**
     * This method tests the copy and equals methods.
     */
    @Test
    public void testCopyAndEquals() {
        logger.info("Beginning testCopyAndEquals()...");

        for (int i = 0; i < 10; i++) {
            logger.info("  Copying and comparing " + i + " bytes...");
            byte[] bytes = RandomUtils.generateRandomBytes(i);
            byte[] copy = ByteUtils.copy(bytes);
            assertTrue(ByteUtils.equals(bytes, copy));
        }

        logger.info("Completed testCopyAndEquals().\n");
    }

    /**
     * This method tests the compare method.
     */
    @Test
    public void testCompare() {
        logger.info("Beginning testCompare()...");

        byte[] empty = {};
        byte[] one = { 1 };
        byte[] two = { 2 };
        byte[] oneTwo = { 1, 2 };

        logger.info("  Comparing {} and {}...");
        assertEquals(0, ByteUtils.compare(empty, empty));

        logger.info("  Comparing {} and { 1 }...");
        assertEquals(-1, ByteUtils.compare(empty, one));

        logger.info("  Comparing { 2 } and {}...");
        assertEquals(1, ByteUtils.compare(two, empty));

        logger.info("  Comparing { 1 } and { 1 }...");
        assertEquals(0, ByteUtils.compare(one, one));

        logger.info("  Comparing { 1 } and { 2 }...");
        assertEquals(-1, ByteUtils.compare(one, two));

        logger.info("  Comparing { 2 } and { 1 }...");
        assertEquals(1, ByteUtils.compare(two, one));

        logger.info("  Comparing { 1 } and { 1, 2 }...");
        assertEquals(-1, ByteUtils.compare(one, oneTwo));

        logger.info("  Comparing { 2 } and { 1, 2 }...");
        assertEquals(1, ByteUtils.compare(two, oneTwo));

        logger.info("  Comparing { 1, 2 } and { 1 }...");
        assertEquals(1, ByteUtils.compare(oneTwo, one));

        logger.info("  Comparing { 1, 2 } and { 2 }...");
        assertEquals(-1, ByteUtils.compare(oneTwo, two));

        logger.info("  Comparing { 1, 2 } and { 1, 2 }...");
        assertEquals(0, ByteUtils.compare(oneTwo, oneTwo));

        logger.info("Completed testCompare().\n");
    }

}
