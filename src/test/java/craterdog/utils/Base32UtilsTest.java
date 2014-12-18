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

import java.util.Arrays;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>Base32Utils</code> class.
 *
 * @author Derk Norton
 */
public class Base32UtilsTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(Base32UtilsTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running Base32Utils Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed Base32Utils Unit Tests.\n");
    }

    /**
     * This method tests round-trip conversions of different numbers of bytes.
     */
    @Test
    public void testDifferentNumberOfBytes() {
        logger.info("Beginning testDifferentNumberOfBytes()...");

        logger.info("  Testing zero bytes...");
        byte[] bytes = new byte[0];
        String encoded = Base32Utils.encode(bytes);
        byte[] decoded = Base32Utils.decode(encoded);
        assertTrue(Arrays.equals(decoded, bytes));

        logger.info("  Testing one byte...");
        bytes = new byte[1];
        for (int b = -128; b < 128; b++) {
            bytes[0] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing two bytes...");
        bytes = RandomUtils.generateRandomBytes(2);
        for (int b = -128; b < 128; b++) {
            bytes[1] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing three bytes...");
        bytes = RandomUtils.generateRandomBytes(3);
        for (int b = -128; b < 128; b++) {
            bytes[2] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing four bytes...");
        bytes = RandomUtils.generateRandomBytes(4);
        for (int b = -128; b < 128; b++) {
            bytes[3] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing five bytes...");
        bytes = RandomUtils.generateRandomBytes(5);
        for (int b = -128; b < 128; b++) {
            bytes[4] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing six bytes...");
        bytes = RandomUtils.generateRandomBytes(6);
        for (int b = -128; b < 128; b++) {
            bytes[5] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing seven bytes...");
        bytes = RandomUtils.generateRandomBytes(7);
        for (int b = -128; b < 128; b++) {
            bytes[6] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing eight bytes...");
        bytes = RandomUtils.generateRandomBytes(8);
        for (int b = -128; b < 128; b++) {
            bytes[7] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("  Testing nine bytes...");
        bytes = RandomUtils.generateRandomBytes(9);
        for (int b = -128; b < 128; b++) {
            bytes[8] = (byte) b;
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        for (int i = 10; i < 100; i++) {
            logger.info("  Testing " + i + " bytes...");
            bytes = RandomUtils.generateRandomBytes(i);
            encoded = Base32Utils.encode(bytes);
            decoded = Base32Utils.decode(encoded);
            assertTrue(Arrays.equals(decoded, bytes));
        }

        logger.info("Completed testDifferentNumberOfBytes().\n");
    }


    /**
     * This method performs tests for wrapping mode and non-wrapping mode.
     */
    @Test
    public void testWrapping() {
        logger.info("Beginning testWrapping()...");

        byte[] bytes = RandomUtils.generateRandomBytes(50);
        String encoded = Base32Utils.encode(bytes);
        logger.info("  With no wrapping: " + encoded);

        bytes = RandomUtils.generateRandomBytes(200);
        encoded = Base32Utils.encode(bytes, "    ");
        logger.info("  With wrapping: " + encoded);

        logger.info("Completed testWrapping().\n");
    }

}
