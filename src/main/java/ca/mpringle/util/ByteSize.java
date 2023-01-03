package ca.mpringle.util;

import java.text.DecimalFormat;

public final class ByteSize {

    private static final long NIBBLE_BITS = 4L;
    private static final long BYTE_BITS = 8L;
    private static final long KiB_BITS = 8L * 1024L;

    private static final long KB_BITS = 8L * 1000L;

    private final long sizeInBits;

    private ByteSize(final long sizeInBits) {
        this.sizeInBits = sizeInBits;
    }

    private ByteSize(final double sizeInBits) {
        this.sizeInBits = (long) sizeInBits;
    }

    public static ByteSize fromBits(final long size) {
        return new ByteSize(size);
    }

    public static ByteSize fromNibbles(final double size) {
        return new ByteSize(size * NIBBLE_BITS);
    }

    public static ByteSize fromBytes(final double size) {
        return new ByteSize(size * BYTE_BITS);
    }

    public static ByteSize fromKb(final double size) {
        return new ByteSize(size * KB_BITS);
    }

    public static ByteSize fromKib(final double size) {
        return new ByteSize(size * KiB_BITS);
    }

    public long toBits() {
        return sizeInBits;
    }

    public String toBits(final String formatString) {
        return new DecimalFormat(formatString).format(sizeInBits);
    }

    public double toNibbles() {
        return convert(sizeInBits, NIBBLE_BITS);
    }

    public String toNibbles(final String formatString) {
        final double number = convert(sizeInBits, NIBBLE_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toBytes() {
        return convert(sizeInBits, BYTE_BITS);
    }

    public String toBytes(final String formatString) {
        final double number = convert(sizeInBits, BYTE_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toKB() {
        return convert(sizeInBits, KB_BITS);
    }

    public String toKB(final String formatString) {
        final double number = convert(sizeInBits, KB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toKiB() {
        return convert(sizeInBits, KiB_BITS);
    }

    public String toKiB(final String formatString) {
        final double number = convert(sizeInBits, KiB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    private static double convert(final long sizeInBits, final long conversionSize) {

        return 1.0D * sizeInBits / conversionSize;
    }
}
