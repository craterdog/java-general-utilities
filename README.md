#Java General Utilities

This project contains a set of useful Java utility classes. The classes focus on the following areas:

 * number base conversions (bases 2, 16, 32, and 64)
 * primitive type to byte array conversions
 * cryptographically secure random number generation

To get started using these utilities, include the following dependency in your maven pom.xml file:

```xml
    <dependency>
        <groupId>com.craterdog</groupId>
        <artifactId>java-general-utilities</artifactId>
        <version>3.3</version>
    </dependency>
```

##Base 2 Conversions
The Base02Utils class provides static methods for encoding byte arrays into base 2 strings and decoding base 2 strings containing arbitrary white space into their corresponding byte array. All encoded strings are formatted into 80 character lines, each prepended with an arbitrary amount of white space. For example, a random 20 byte array might have the following base 2 output:

        00100001011100100100011000011010111100001000010111110010100110110010101000110101
        01000011101101110010000010001000010110000011110111001101000010001011100011010011

##Base 16 Conversions
Similarly, the Base16Utils class provides static methods for encoding byte arrays into base 16 strings and decoding base 16 strings containing arbitrary white space into their corresponding byte array. All encoded strings are formatted into 80 character lines, each prepended with an arbitrary amount of white space. For example, a random 120 byte array might have the following base 16 output:

        E9EAEAF429E9C27C42D637EB438EDF65CB53FACBC659CAA19655CB7115CF9ED520131598527B03C6
        90B14E975D733EA042751755C686DF0081C6F26C57E43E905D1CCD96ACEFFD1CE437867B53FE31DC
        89202ADD79F5393BD2882ADEE2B86FC99629C152C4E3F1B111CDF34FFBF6A7E529CA612265B91832

##Base 32 Conversions
Similarly, the Base32Utils class provides static methods for encoding byte arrays into base 32 ('0'..'9', 'A'..'D', 'F'..'H', 'J'..'N', 'P'..'T', 'V'..'Z') strings and decoding base 32 strings containing arbitrary white space into their corresponding byte array. All encoded strings are formatted into 80 character lines, each prepended with an arbitrary amount of white space. For example, a random 200 byte array might have the following base 32 output:

        3S6CGCCVGVRS30AY24SS04NWLZF688VB1W9X79Q5YVMXTT01BMHDSLV94XK9BQC6AM4J6CFFNNAT42GD
        VTJ3ZHD2A1VTK1HMDGPSBNA3WMR00J8TARZ52YWQBJQVCK2QJT22RRZNK6QWHNSL86HFYVJFMAH7MB2Y
        GW495HSG3TCT5HT7GTHC27LRZ5TPRAWDD0MGX884V9DGAXQCGXKFSHFPMP25DCNL0PR9CXF5QN2K3V3V
        8Y7PBYYQXWMK5HRVM8HYXANXZQJ6XQ6AFQLXARSGTNR8HXA84C65W32MSRMXAX5ZRSGYNDS0MBA0XSAY

Note, this base 32 character encoding eliminates the vowels 'E', 'I', 'O', and 'U' to avoid confusion with '1' and '0' and to avoid spelling (possibly offensive) words.

##Base 64 Conversions
And finally, the Base64Utils class provides static methods for encoding byte arrays into standard base 64 ('0'..'9', 'a'..'z', 'A'..'Z', '+', '/') strings and decoding base 64 strings containing arbitrary white space into their corresponding byte array. All encoded strings are formatted into 80 character lines, each prepended with an arbitrary amount of white space. For example, a random 360 byte array might have the following base 64 output:

        SZm6+QD0gVl21T9ocqqIjL5lTE38pmU6Zm06rzMG5fJIxSemYoPUtSgKf1FVqD3WD4aWhfgHpn2aSYmI
        8jq3OpMFrB+MQ73o4Pabx7fhE2GvGLzcgxJ8tQx9uDyq7Vy2deSqEXKOO3ONFq1RSSPCnQMKn8rjVJMu
        sbOHs4RD7RrD6qv9m68S2H5/QCECKH0ghMxUmBCiJ9O6L3lK/laFJr9xUzwxiKWt8JKTAM/u6azvJ6aX
        r37k90nkf41u9y+iq2LejSn0qRcP4/ywIIOcD6D6jSwyEvyiqn6+TNY4irgsoa5cN7ysob73hOsHnrUU
        MtMZR78FNMYy9JxMfwBkwHtQXoNnF/VLA5eOR+K6o2QOnqReU1cIz72KtPlmMxF5W0Z6mW2w52EuGkzQ
        Xms9xGP6atYs7NzVI5SZ57oZFskYwt7hTrGQa5ffiD/brQGcIc06svEUSRsMa3V8N78icFsyV3IxaSFH

##Primitive Type to Byte Array Conversions
The java.lang.reflect.Array class provides nice methods for storing Java primitive types into a byte array and reading them back out.  But the methods are not as flexible as they might be and they don't handle non-primitive types like String, BigInteger, and BigDecimal. The ByteUtils class addresses some of these short-comings and provides a consistent interface for all types. This class handles the following type conversions to an from byte arrays:

 * boolean
 * short
 * int
 * long
 * double
 * BigInteger
 * BigDecimal
 * String

##Random Number Generation Utilities
The RandomUtils class provides some simple static methods that generate cryptographically secure random numbers for specific purposes. The following types of random numbers can be generated:

 * a random integer (int)
 * a random index in a range [0..n)
 * a random probability [0.0..1.0)
 * a random gaussian with mean of 0.0 and standard deviation of 1.0
 * a byte array initialized with random byte values

##Example Code
The following example code demonstrates some of the utility methods:

```java
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
```

It will generate the following output:

    The random integer is: -1791641115
    
    Its bytes in base 2 are: 10010101001101011011100111100101
    
    Its bytes in base 16 are: 9535B9E5
    
    Its bytes in base 32 are: KMTVLS8
    
    Its bytes in base 64 are: lTW55Q==
    
    The string is:
        "Two roads diverged in a yellow wood,
        And sorry I could not travel both
        And be one traveler, long I stood
        And looked down one as far as I could
        To where it bent in the undergrowth;"
    
    Its bytes in base 2 are: 
        00100000001000000010000000100000001000100101010001110111011011110010000001110010
        01101111011000010110010001110011001000000110010001101001011101100110010101110010
        01100111011001010110010000100000011010010110111000100000011000010010000001111001
        01100101011011000110110001101111011101110010000001110111011011110110111101100100
        00101100000010100010000000100000001000000010000001000001011011100110010000100000
        01110011011011110111001001110010011110010010000001001001001000000110001101101111
        01110101011011000110010000100000011011100110111101110100001000000111010001110010
        01100001011101100110010101101100001000000110001001101111011101000110100000001010
        00100000001000000010000000100000010000010110111001100100001000000110001001100101
        00100000011011110110111001100101001000000111010001110010011000010111011001100101
        01101100011001010111001000101100001000000110110001101111011011100110011100100000
        01001001001000000111001101110100011011110110111101100100000010100010000000100000
        00100000001000000100000101101110011001000010000001101100011011110110111101101011
        01100101011001000010000001100100011011110111011101101110001000000110111101101110
        01100101001000000110000101110011001000000110011001100001011100100010000001100001
        01110011001000000100100100100000011000110110111101110101011011000110010000001010
        00100000001000000010000000100000010101000110111100100000011101110110100001100101
        01110010011001010010000001101001011101000010000001100010011001010110111001110100
        00100000011010010110111000100000011101000110100001100101001000000111010101101110
        01100100011001010111001001100111011100100110111101110111011101000110100000111011
        00100010
    
    Its bytes in base 16 are: 
        202020202254776F20726F61647320646976657267656420696E20612079656C6C6F7720776F6F64
        2C0A20202020416E6420736F727279204920636F756C64206E6F742074726176656C20626F74680A
        20202020416E64206265206F6E652074726176656C65722C206C6F6E6720492073746F6F640A2020
        2020416E64206C6F6F6B656420646F776E206F6E6520617320666172206173204920636F756C640A
        20202020546F2077686572652069742062656E7420696E2074686520756E64657267726F7774683B
        22
    
    Its bytes in base 32 are: 
        40H20812AJVPY83KDXHP8WS0CJMQCSBKCXKP8839DRH6283SCNP6RVVQ41VPYVV45H520810410PWS10
        FDQQ4WLS414K0RVGFNP6883FDXT20X3KC5V6AV10C9QQ8T0A40H20821DSK20RL541QPWS90FJS62XL5
        DJKQ4B10DJQPWSS094H76X3GDXK0M81040H42VL441P6YVVBCNK20S3GFXQ20VVFCMH62WS0CSHQ4831
        FCH4K833DXTPRS0A40H2082MDWH7FT35F9KK0TBM41J6AVLM41MPW83MD1KK0XBFCJKQ4SVKDXVQ8T1V
        48
    
    Its bytes in base 64 are: 
        ICAgICJUd28gcm9hZHMgZGl2ZXJnZWQgaW4gYSB5ZWxsb3cgd29vZCwKICAgIEFuZCBzb3JyeSBJIGNv
        dWxkIG5vdCB0cmF2ZWwgYm90aAogICAgQW5kIGJlIG9uZSB0cmF2ZWxlciwgbG9uZyBJIHN0b29kCiAg
        ICBBbmQgbG9va2VkIGRvd24gb25lIGFzIGZhciBhcyBJIGNvdWxkCiAgICBUbyB3aGVyZSBpdCBiZW50
        IGluIHRoZSB1bmRlcmdyb3d0aDsi
    

