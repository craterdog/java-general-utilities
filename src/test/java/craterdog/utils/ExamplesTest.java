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

    static private final XLogger log = XLoggerFactory.getXLogger(ExamplesTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        log.info("Running Tag Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        log.info("Completed Tag Unit Tests.\n");
    }

    /**
     * This method tests the various utility class examples.
     */
    @Test
    public void testUtilsClassesExamples() {
        log.info("Beginning testUtilsClassesExamples()...");

        // Generate a random integer
        int integer = RandomUtils.pickRandomInt();
        byte[] bytes = ByteUtils.intToBytes(integer);

        // Print it out using different number bases
        log.info("The random integer is: {}\n", integer);
        log.info("Its bytes in base 2 are: {}\n", Base02Utils.encode(bytes));
        log.info("Its bytes in base 16 are: {}\n", Base16Utils.encode(bytes));
        log.info("Its bytes in base 32 are: {}\n", Base32Utils.encode(bytes));
        log.info("Its bytes in base 64 are: {}\n", Base64Utils.encode(bytes));

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
        log.info("The string is:\n{}\n", string);
        log.info("Its bytes in base 2 are: {}\n", Base02Utils.encode(bytes, indentation));
        log.info("Its bytes in base 16 are: {}\n", Base16Utils.encode(bytes, indentation));
        log.info("Its bytes in base 32 are: {}\n", Base32Utils.encode(bytes, indentation));
        log.info("Its bytes in base 64 are: {}\n", Base64Utils.encode(bytes, indentation));

        log.info("Completed testUtilsClassesExamples().\n");
    }

}
