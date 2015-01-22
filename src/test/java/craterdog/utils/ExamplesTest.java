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

import java.util.Comparator;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class defines and validates the example code shown in the README.md file.
 *
 * @author Derk Norton
 */
public class ExamplesTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(ExamplesTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running Tag Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed Tag Unit Tests.\n");
    }


    /**
     * This method tests the various utility class examples.
     */
    @Test
    public void testUtilsClassesExamples() {
        logger.info("Beginning testUtilsClassesExamples()...");

        // Generate a random integer
        int integer = RandomUtils.pickRandomInt();
        byte[] bytes = ByteUtils.intToBytes(integer);

        // Print it out using different number bases
        logger.info("  The random integer is: {}\n", integer);
        logger.info("  Its bytes in base 2 are: {}\n", Base02Utils.encode(bytes));
        logger.info("  Its bytes in base 16 are: {}\n", Base16Utils.encode(bytes));
        logger.info("  Its bytes in base 32 are: {}\n", Base32Utils.encode(bytes));
        logger.info("  Its bytes in base 64 are: {}\n", Base64Utils.encode(bytes));

        // Define a string
        String string =
        "    \"Two roads diverged in a yellow wood,\n" +
        "    And sorry I could not travel both\n" +
        "    And be one traveler, long I stood\n" +
        "    And looked down one as far as I could\n" +
        "    To where it bent in the undergrowth;\"";
        bytes = ByteUtils.stringToBytes(string);
        String indentation = "    ";

        // Print it out using different number bases
        logger.info("  The string is:\n{}\n", string);
        logger.info("  Its bytes in base 2 are: {}\n", Base02Utils.encode(bytes, indentation));
        logger.info("  Its bytes in base 16 are: {}\n", Base16Utils.encode(bytes, indentation));
        logger.info("  Its bytes in base 32 are: {}\n", Base32Utils.encode(bytes, indentation));
        logger.info("  Its bytes in base 64 are: {}\n", Base64Utils.encode(bytes, indentation));

        logger.info("Completed testUtilsClassesExamples().\n");
    }


    /**
     * This method tests the natural comparator examples.
     */
    @Test
    public void testNaturalComparatorExamples() {
        logger.info("Beginning testNaturalComparatorExamples()...");

        // Compare two booleans
        boolean firstBoolean = true;
        boolean secondBoolean = false;
        logger.info("  {} is greater than {}.", firstBoolean, secondBoolean);
        Comparator<Boolean> booleanComparator = new NaturalComparator<>();
        assert booleanComparator.compare(firstBoolean, secondBoolean) > 0;

        // Compare two characters
        char firstCharacter = 'b';
        char secondCharacter = 'a';
        logger.info("  '{}' is greater than '{}'.", firstCharacter, secondCharacter);
        Comparator<Character> characterComparator = new NaturalComparator<>();
        assert characterComparator.compare(firstCharacter, secondCharacter) > 0;

        // Compare two numbers
        double firstNumber = Math.PI;
        double secondNumber = Math.E;
        logger.info("  {} is greater than {}.", firstNumber, secondNumber);
        Comparator<Number> numberComparator = new NaturalComparator<>();
        assert numberComparator.compare(firstNumber, secondNumber) > 0;

        // Compare two dates
        Date firstDate = new Date(987654321);
        Date secondDate = new Date(123456789);
        logger.info("  {} is greater than {}.", firstDate, secondDate);
        Comparator<Date> dateComparator = new NaturalComparator<>();
        assert dateComparator.compare(firstDate, secondDate) > 0;

        // Compare two strings
        String firstString = "This is a string...";
        String secondString = "And this is another string...";
        logger.info("  \"{}\" is greater than \"{}\".", firstString, secondString);
        Comparator<String> stringComparator = new NaturalComparator<>();
        assert stringComparator.compare(firstString, secondString) > 0;

        Comparator<Integer[]> arrayComparator = new NaturalComparator<>();
        Integer[] array0 = { };
        Integer[] array1 = {1, 1, 2, 3, 5, 8, 13};
        Integer[] array2 = {0, 1, 1, 2, 3, 5, 8, 13, 21};
        Integer[] array3 = {1, 1, 2, 3, 5, 8};

        // Compare various arrays
        logger.info("  {} is equal to {}.", array0, array0);
        assert arrayComparator.compare(array0, array0) == 0;
        logger.info("  {} is less than {}.", array0, array1);
        assert arrayComparator.compare(array0, array1) < 0;
        logger.info("  {} is equal to {}.", array1, array1);
        assert arrayComparator.compare(array1, array1) == 0;
        logger.info("  {} is greater than {}.", array1, array2);
        assert arrayComparator.compare(array1, array2) > 0;
        logger.info("  {} is greater than {}.", array1, array3);
        assert arrayComparator.compare(array1, array3) > 0;

        logger.info("Completed testNaturalComparatorExamples().\n");
    }


    /**
     * This method tests the random utilities examples.
     */
    @Test
    public void testRandomUtilsExamples() {
        logger.info("Beginning testRandomUtilsExamples()...");

        // Create a new random universal hash function with hash width of 32 bits
        UniversalHashFunction function = new UniversalHashFunction();
        String string = "This is a string that needs hashing...";
        int hashValue = function.hashValue(string);
        logger.info("  The 32 bit hash value of \"{}\" is {}.", string, Integer.toBinaryString(hashValue));

        // Create a new random universal hash function with hash width of 13 bits
        function = new UniversalHashFunction(13);
        double pi = Math.PI;
        hashValue = function.hashValue(pi);
        assert Integer.highestOneBit(hashValue) >> 13 == 0;
        logger.info("  The 13 bit hash value of {} is {}.", pi, Integer.toBinaryString(hashValue));

        // Create a new random universal hash function with hash width of 8 bits
        function = new UniversalHashFunction(8);
        Date date = new Date();
        hashValue = function.hashValue(date);
        assert Integer.highestOneBit(hashValue) >> 8 == 0;
        logger.info("  The 8 bit hash value of {} is {}.", date, Integer.toBinaryString(hashValue));

        // Create a list of random hash values for sequential integers
        function = new UniversalHashFunction(6);
        for (int i = 0; i < 16; i++) {
            hashValue = function.hashValue(i);
            logger.info("  The 6 bit hash value of {} is {}.", i, Integer.toBinaryString(hashValue));
        }
        logger.info("Completed testRandomUtilsExamples().\n");
    }

}
