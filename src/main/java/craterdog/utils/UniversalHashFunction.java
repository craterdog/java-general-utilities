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
import java.util.Date;


/**
 * This class implements the universal hash function algorithm described by
 * Dietzfelbinger, et al, (1997). "A Reliable Randomized Algorithm for the Closest-Pair Problem".
 * The algorithm provides a very efficient (mod free) way to create and use a universal hash
 * function that creates an evenly distributed hash value for hash tables that have a number of
 * buckets that are a power of two.  This function works even for data types that have poor builtin
 * hash functions like are often seen for strings.
 *
 * @author Derk Norton
 */
public final class UniversalHashFunction {

    static private final int WORD_WIDTH = Integer.SIZE;

    private final int d;  // difference between word width and hash width
    private final int a;  // random positive even integer < 2^w
    private final int b;  // random non-negative integer < 2^d


    /**
     * This constructor creates a new hash function with a hash width equal to the word width.
     * The universal hash function will be chosen at random so this will not generate consistent
     * hash functions across different JVM invocations.
     */
    public UniversalHashFunction() {
        this(WORD_WIDTH);
    }


    /**
     * This constructor creates a new hash function with the specified hash width.  The universal
     * hash function will be chosen at random so this will not generate consistent hash functions
     * across different JVM invocations.
     *
     * @param hashWidth The number of bits required of the hash for the index size.  In general,
     * this will be the log base 2 of the number of buckets in the hash table (must be a power of
     * 2).
     */
    public UniversalHashFunction(int hashWidth) {
        this.d = WORD_WIDTH - hashWidth;
        this.a = (RandomUtils.pickRandomInt() & 0x7FFFFFFF) | 0x00000001;  // make sure a is positive and odd
        this.b = RandomUtils.pickRandomIndex(1 << d);  // in range [0..2^d)
    }


    /**
     * This constructor creates a new hash function with the specified hash width, and random
     * numbers a and b.  It should be used when the hash values must be consistent across different
     * JVM invocations.
     *
     * @param hashWidth The number of bits required of the hash for the index size.  In general,
     * this will be the log base 2 of the number of buckets in the hash table (must be a power of
     * 2).
     * @param a A positive odd random integer.
     * @param b A random integer in the range [0..2^(wordWidth - hashWidth)).
     */
    public UniversalHashFunction(int hashWidth, int a, int b) {
        this.d = WORD_WIDTH - hashWidth;
        this.a = a;
        this.b = b;
    }


    /**
     * This method generates a hash value for the specified object using the universal hash
     * function parameters specified in the constructor.  The result will be a hash value
     * containing the hash width number of bits.
     *
     * @param object The object to be hashed.
     * @return A universal hash of the object.
     */
    public int hashValue(Object object) {
        int x;
        // make sure the hash value is consistent across JVM invocations
        if (object instanceof Boolean || object instanceof Character || object instanceof String || object instanceof Number || object instanceof Date) {
            x = object.hashCode();
        } else if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            x = Arrays.toString(array).hashCode();  // array.hashCode() returns the memory address of the array!
        } else {
            x = object.toString().hashCode();  // make sure it isn't calling Object.hashCode()!
        }
        int hash = (a * x + b) >>> d;  // unsigned shift right
        return hash;
    }

}

