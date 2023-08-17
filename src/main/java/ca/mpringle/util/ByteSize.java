package ca.mpringle.util;

import java.text.DecimalFormat;

/**
 * See wikipedia for <a href="https://en.wikipedia.org/wiki/Unit_prefix">unit prefix</a>  details,
 * and an explanation of ?B vs ?iB notation.
 */
public final class ByteSize {

    private static final long NIBBLE_BITS = 4L;
    private static final long BYTE_BITS = NIBBLE_BITS * 2L;
    private static final long KIB_BITS = BYTE_BITS * 1024L;
    private static final long MIB_BITS = KIB_BITS * 1024L;
    private static final long GIB_BITS = MIB_BITS * 1024L;
    private static final long TIB_BITS = GIB_BITS * 1024L;
    private static final long PIB_BITS = TIB_BITS * 1024L;

    private static final long KB_BITS = BYTE_BITS * 1000L;
    private static final long MB_BITS = KB_BITS * 1000L;
    private static final long GB_BITS = MB_BITS * 1000L;
    private static final long TB_BITS = GB_BITS * 1000L;
    private static final long PB_BITS = TB_BITS * 1000L;
    private static final long EB_BITS = PB_BITS * 1000L;

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
        return new ByteSize(size * KIB_BITS);
    }

    public static ByteSize fromMb(final double size) {
        return new ByteSize(size * MB_BITS);
    }

    public static ByteSize fromMib(final double size) {
        return new ByteSize(size * MIB_BITS);
    }

    public static ByteSize fromGb(final double size) {
        return new ByteSize(size * GB_BITS);
    }

    public static ByteSize fromGib(final double size) {
        return new ByteSize(size * GIB_BITS);
    }

    public static ByteSize fromTb(final double size) {
        return new ByteSize(size * TB_BITS);
    }

    public static ByteSize fromTib(final double size) {
        return new ByteSize(size * TIB_BITS);
    }

    public static ByteSize fromPb(final double size) {
        return new ByteSize(size * PB_BITS);
    }

    public static ByteSize fromPib(final double size) {
        return new ByteSize(size * PIB_BITS);
    }

    public static ByteSize fromEb(final double size) {
        return new ByteSize(size * EB_BITS);
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
        return convert(sizeInBits, KIB_BITS);
    }

    public String toKiB(final String formatString) {
        final double number = convert(sizeInBits, KIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toMB() {
        return convert(sizeInBits, MB_BITS);
    }

    public String toMB(final String formatString) {
        final double number = convert(sizeInBits, MB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toMiB() {
        return convert(sizeInBits, MIB_BITS);
    }

    public String toMiB(final String formatString) {
        final double number = convert(sizeInBits, MIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toGB() {
        return convert(sizeInBits, GB_BITS);
    }

    public String toGB(final String formatString) {
        final double number = convert(sizeInBits, GB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toGiB() {
        return convert(sizeInBits, GIB_BITS);
    }

    public String toGiB(final String formatString) {
        final double number = convert(sizeInBits, GIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toTB() {
        return convert(sizeInBits, TB_BITS);
    }

    public String toTB(final String formatString) {
        final double number = convert(sizeInBits, TB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toTiB() {
        return convert(sizeInBits, TIB_BITS);
    }

    public String toTiB(final String formatString) {
        final double number = convert(sizeInBits, TIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toPB() {
        return convert(sizeInBits, PB_BITS);
    }

    public String toPB(final String formatString) {
        final double number = convert(sizeInBits, PB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toPiB() {
        return convert(sizeInBits, PIB_BITS);
    }

    public String toPiB(final String formatString) {
        final double number = convert(sizeInBits, PIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toEB() {
        return convert(sizeInBits, EB_BITS);
    }

    public String toEB(final String formatString) {
        final double number = convert(sizeInBits, EB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    private static double convert(final long sizeInBits,
                                  final long conversionSize) {

        return 1.0D * sizeInBits / conversionSize;
    }
}
