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
 * This class performs unit tests on the <code>NaturalComparator</code> class.
 *
 * @author Derk Norton
 */
public class NaturalComparatorTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(NaturalComparatorTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running NaturalComparator Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed NaturalComparator Unit Tests.\n");
    }

    /**
     * This method tests the natural comparator on primitive values.
     */
    @Test
    public void testPrimitiveValues() {
        logger.info("Beginning testPrimitiveValues()...");

        // test a boolean
        boolean firstBoolean = true;
        boolean secondBoolean = false;
        logger.info("  {} is greater than {}.", firstBoolean, secondBoolean);
        Comparator<Boolean> booleanComparator = new NaturalComparator<>();
        assert booleanComparator.compare(firstBoolean, secondBoolean) > 0;

        // test a char
        char firstCharacter = 'b';
        char secondCharacter = 'a';
        logger.info("  '{}' is greater than '{}'.", firstCharacter, secondCharacter);
        Comparator<Character> characterComparator = new NaturalComparator<>();
        assert characterComparator.compare(firstCharacter, secondCharacter) > 0;

        // test a number
        double firstNumber = Math.PI;
        double secondNumber = Math.E;
        logger.info("  {} is greater than {}.", firstNumber, secondNumber);
        Comparator<Number> numberComparator = new NaturalComparator<>();
        assert numberComparator.compare(firstNumber, secondNumber) > 0;

        // test a date
        Date firstDate = new Date(987654321);
        Date secondDate = new Date(123456789);
        logger.info("  {} is greater than {}.", firstDate, secondDate);
        Comparator<Date> dateComparator = new NaturalComparator<>();
        assert dateComparator.compare(firstDate, secondDate) > 0;

        // test a string
        String firstString = "This is a string...";
        String secondString = "And this is another string...";
        logger.info("  \"{}\" is greater than \"{}\".", firstString, secondString);
        Comparator<String> stringComparator = new NaturalComparator<>();
        assert stringComparator.compare(firstString, secondString) > 0;

        logger.info("Completed testPrimitiveValues().\n");
    }


    /**
     * This method tests the natural comparator on arrays.
     */
    @Test
    public void testArrays() {
        logger.info("Beginning testArrays()...");

        Comparator<Integer[]> arrayComparator = new NaturalComparator<>();
        Integer[] array0 = { };
        Integer[] array1 = {1, 1, 2, 3, 5, 8, 13};
        Integer[] array2 = {0, 1, 1, 2, 3, 5, 8, 13, 21};
        Integer[] array3 = {1, 1, 2, 3, 5, 8};

        // test empty arrays
        logger.info("  {} is equal to {}.", array0, array0);
        assert arrayComparator.compare(array0, array0) == 0;
        logger.info("  {} is less than {}.", array0, array1);
        assert arrayComparator.compare(array0, array1) < 0;
        logger.info("  {} is greater than {}.", array1, array0);
        assert arrayComparator.compare(array1, array0) > 0;

        // test non-empty arrays
        logger.info("  {} is equal to {}.", array1, array1);
        assert arrayComparator.compare(array1, array1) == 0;
        logger.info("  {} is greater than {}.", array1, array2);
        assert arrayComparator.compare(array1, array2) > 0;
        logger.info("  {} is greater than {}.", array1, array3);
        assert arrayComparator.compare(array1, array3) > 0;

        logger.info("Completed testArrays().\n");
    }

}
