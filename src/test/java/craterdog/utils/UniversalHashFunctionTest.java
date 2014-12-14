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

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>UniversalHashFunction</code> class.
 *
 * @author Derk Norton
 */
public class UniversalHashFunctionTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(UniversalHashFunctionTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running UniversalHashFunction Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed UniversalHashFunction Unit Tests.\n");
    }

    /**
     * This method tests the creation and use of a new random has function.
     */
    @Test
    public void testRandomHashFunctions() {
        logger.info("Beginning testRandomHashFunctions()...");

        // Test a new random universal hash function with hash width of 32 bits
        UniversalHashFunction function = new UniversalHashFunction();
        String string = "This is a string that needs hashing...";
        int hashValue = function.hashValue(string);
        logger.info("The 32 bit hash value of \"{}\" is {}.", string, Integer.toBinaryString(hashValue));

        // Test a new random universal hash function with hash width of 8 bits
        function = new UniversalHashFunction(8);
        double pi = Math.PI;
        hashValue = function.hashValue(pi);
        assert Integer.highestOneBit(hashValue) >> 8 == 0;
        logger.info("The 8 bit hash value of {} is {}.", pi, Integer.toBinaryString(hashValue));

        // Test a new random universal hash function with hash width of zero bits (corner case)
        function = new UniversalHashFunction(0);
        Date date = new Date();
        hashValue = function.hashValue(date);
        logger.info("The 0 bit hash value of {} is {}.", date, Integer.toBinaryString(hashValue));

        logger.info("Completed testRandomHashFunctions().\n");
    }

    /**
     * This method tests the creation and use of a new previously known hash function.
     */
    @Test
    public void testKnownHashFunctionRepeatability() {
        logger.info("Beginning testKnownHashFunctionRepeatability()...");

        // repeat a few times to make sure the same hash value is generated each time
        for (int i = 0; i < 5; i++) {

            // generate a known universal hash function
            int hashWidth = 19;
            int a = 1845366419;
            int b = 3864;
            UniversalHashFunction function = new UniversalHashFunction(hashWidth, a, b);

            // test a boolean
            int hashValue = function.hashValue(true);
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            int expectedValue = 476481;
            logger.info("The 19 bit hash value of {} is {}", true, Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

            // test a char
            hashValue = function.hashValue('g');
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            expectedValue = 133567;
            logger.info("The 19 bit hash value of {} is {}", 'g', Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

            // test a number
            double number = 123.45E67d;
            hashValue = function.hashValue(number);
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            expectedValue = 289298;
            logger.info("The 19 bit hash value of {} is {}", number, Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

            // test a date
            Date date = new Date(123456789);
            hashValue = function.hashValue(date);
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            expectedValue = 349727;
            logger.info("The 19 bit hash value of {} is {}", date, Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

            // test a string
            String string = "This is a string!";
            hashValue = function.hashValue(string);
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            expectedValue = 407412;
            logger.info("The 19 bit hash value of \"{}\" is {}", string, Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

            // test an array
            Integer[] array = {1, 1, 2, 3, 5, 8, 13, 21, 34};
            hashValue = function.hashValue(array);
            assert Integer.highestOneBit(hashValue) >> 19 == 0;
            expectedValue = 70825;
            logger.info("The 19 bit hash value of {} is {}", array, Integer.toBinaryString(hashValue));
            assert hashValue == expectedValue;

        }

        logger.info("Completed testKnownHashFunctionRepeatability().\n");
    }

}
