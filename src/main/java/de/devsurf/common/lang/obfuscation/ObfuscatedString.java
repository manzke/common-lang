package de.devsurf.common.lang.obfuscation;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.devsurf.common.lang.formatter.ToStringMessage;

public final class ObfuscatedString {

    private static final String UTF8
            = new String(new char[] { '\u0055', '\u0054', '\u0046', '\u0038' }); // => "UTF8"

    public static ObfuscatedString obfuscate(final String s) {
        if (-1 != s.indexOf(0)) {
            throw new IllegalArgumentException(new ObfuscatedString(Arrays.asList(
                "241005931110FC70", "DCD925A88EAD9F37", "19ADA1C861E2A85D",
                "9A5948E700FCAD8A", "2E11C83A72441DE2"
            )).toString()); // => "Null characters are not allowed!";
        }

        // Obtain the string as a sequence of UTF-8 encoded bytes.
        final byte[] encoded;
        try {
            encoded = s.getBytes(UTF8);
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex); // UTF8 is always supported
        }

        // Create and seed a Pseudo Random Number Generator (PRNG) with a
        // random long number.
        final Random prng = new Random(); // randomly seeded
        final long seed = prng.nextLong(); // seed strength is effectively 48 bits
        prng.setSeed(seed);
        
        List<String> values = new ArrayList<String>();
        
        values.add(Long.toHexString(seed).toUpperCase());

        final int length = encoded.length;
        for (int i = 0; i < length; i += 8) {
            final long key = prng.nextLong();
            // Compute the value of the next array element as an obfuscated
            // version of the next eight bytes of the UTF8 encoded string.
            final long obfuscated = toLong(encoded, i) ^ key;
            values.add(Long.toHexString(obfuscated).toUpperCase());
        }

        return new ObfuscatedString(values);
    }


    /**
     * Decodes a long value from eight bytes in little endian order,
     * beginning at index {@code off}.
     * This is the inverse of {@link #toBytes(long, byte[], int)}.
     * If less than eight bytes are remaining in the array,
     * only these low order bytes are processed and the complementary high
     * order bytes of the returned value are set to zero.
     *
     * @param bytes The array containing the bytes to decode in little endian
     *        order.
     * @param off The offset of the bytes in the array.
     *
     * @return The decoded long value.
     */
    private static long toLong(final byte[] bytes, int off) {
        final int end = Math.min(bytes.length, off + 8);
        long l = 0;
        for (int i = end; --i >= off; ) {
            l <<= 8;
            l |= bytes[i] & 0xFF;
        }
        return l;
    }

    /**
     * Encodes a long value to eight bytes in little endian order,
     * beginning at index {@code off}.
     * This is the inverse of {@link #toLong(byte[], int)}.
     * If less than eight bytes are remaining in the array,
     * only these low order bytes of the long value are processed and the
     * complementary high order bytes are ignored.
     *
     * @param l The long value to encode.
     * @param bytes The array which holds the encoded bytes upon return.
     * @param off The offset of the bytes in the array.
     */
    private static void toBytes(long l, byte[] bytes, int off) {
        final int end = Math.min(bytes.length, off + 8);
        for (int i = off; i < end; i++) {
            bytes[i] = (byte) l;
            l >>= 8;
        }
    }

    /** The obfuscated string. */
    private final List<String> virtual;

    /**
     * Constructs an obfuscated string.
     *
     * @param obfuscated The obfuscated string.
     * @throws NullPointerException If {@code obfuscated} is
     *         {@code null}.
     * @throws ArrayIndexOutOfBoundsException If the provided array does not
     *         contain at least one element.
     * @see    #obfuscate(String)
     */
    public ObfuscatedString(final List<String> virtual) {
        this.virtual = virtual;
    }
    
    @Override
    public String toString() {
    	 return ToStringMessage.format(getClass()).addParameter("virtual", virtual).build();
    }
    
    public List<String> values() {
    	return virtual;
    }

    /** Returns the original string. */
    public String deobfuscate() {
        final int length = virtual.size();

        // The original UTF8 encoded string was probably not a multiple
        // of eight bytes long and is thus actually shorter than this array.
        final byte[] encoded = new byte[8 * (length - 1)];

        // Obtain the seed and initialize a new PRNG with it.
        final String hex = virtual.get(0);
        long seed = new BigInteger(hex, 16).longValue();
        final Random prng = new Random(seed);

        // De-obfuscate.
        for (int i = 1; i < length; i++) {
            final long key = prng.nextLong();
            final long part = new BigInteger(virtual.get(i), 16).longValue();
            toBytes(part ^ key, encoded, 8 * (i - 1));
        }

        // Decode the UTF-8 encoded byte array into a string.
        // This will create null characters at the end of the decoded string
        // in case the original UTF8 encoded string was not a multiple of
        // eight bytes long.
        final String decoded;
        try {
            decoded = new String(encoded, UTF8);
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex); // UTF-8 is always supported
        }

        // Cut off trailing null characters in case the original UTF8 encoded
        // string was not a multiple of eight bytes long.
        final int i = decoded.indexOf(0);
        return -1 == i ? decoded : decoded.substring(0, i);
    }
}
