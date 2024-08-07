package ca.mpringle.util;

import javax.validation.constraints.NotNull;
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

    @NotNull
    public static ByteSize fromBits(final long size) {
        return new ByteSize(size);
    }

    @NotNull
    public static ByteSize fromNibbles(final double size) {
        return new ByteSize(size * NIBBLE_BITS);
    }

    @NotNull
    public static ByteSize fromBytes(final double size) {
        return new ByteSize(size * BYTE_BITS);
    }

    @NotNull
    public static ByteSize fromKb(final double size) {
        return new ByteSize(size * KB_BITS);
    }

    @NotNull
    public static ByteSize fromKib(final double size) {
        return new ByteSize(size * KIB_BITS);
    }

    @NotNull
    public static ByteSize fromMb(final double size) {
        return new ByteSize(size * MB_BITS);
    }

    @NotNull
    public static ByteSize fromMib(final double size) {
        return new ByteSize(size * MIB_BITS);
    }

    @NotNull
    public static ByteSize fromGb(final double size) {
        return new ByteSize(size * GB_BITS);
    }

    @NotNull
    public static ByteSize fromGib(final double size) {
        return new ByteSize(size * GIB_BITS);
    }

    @NotNull
    public static ByteSize fromTb(final double size) {
        return new ByteSize(size * TB_BITS);
    }

    @NotNull
    public static ByteSize fromTib(final double size) {
        return new ByteSize(size * TIB_BITS);
    }

    @NotNull
    public static ByteSize fromPb(final double size) {
        return new ByteSize(size * PB_BITS);
    }

    @NotNull
    public static ByteSize fromPib(final double size) {
        return new ByteSize(size * PIB_BITS);
    }

    @NotNull
    public static ByteSize fromEb(final double size) {
        return new ByteSize(size * EB_BITS);
    }

    public long toBits() {
        return sizeInBits;
    }

    @NotNull
    public String toBits(@NotNull final String formatString) {
        return new DecimalFormat(formatString).format(sizeInBits);
    }

    public double toNibbles() {
        return convert(sizeInBits, NIBBLE_BITS);
    }

    @NotNull
    public String toNibbles(@NotNull final String formatString) {
        final double number = convert(sizeInBits, NIBBLE_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toBytes() {
        return convert(sizeInBits, BYTE_BITS);
    }

    @NotNull
    public String toBytes(@NotNull final String formatString) {
        final double number = convert(sizeInBits, BYTE_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toKB() {
        return convert(sizeInBits, KB_BITS);
    }

    @NotNull
    public String toKB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, KB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toKiB() {
        return convert(sizeInBits, KIB_BITS);
    }

    @NotNull
    public String toKiB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, KIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toMB() {
        return convert(sizeInBits, MB_BITS);
    }

    @NotNull
    public String toMB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, MB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toMiB() {
        return convert(sizeInBits, MIB_BITS);
    }

    @NotNull
    public String toMiB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, MIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toGB() {
        return convert(sizeInBits, GB_BITS);
    }

    @NotNull
    public String toGB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, GB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toGiB() {
        return convert(sizeInBits, GIB_BITS);
    }

    @NotNull
    public String toGiB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, GIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toTB() {
        return convert(sizeInBits, TB_BITS);
    }

    @NotNull
    public String toTB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, TB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toTiB() {
        return convert(sizeInBits, TIB_BITS);
    }

    @NotNull
    public String toTiB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, TIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toPB() {
        return convert(sizeInBits, PB_BITS);
    }

    @NotNull
    public String toPB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, PB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toPiB() {
        return convert(sizeInBits, PIB_BITS);
    }

    @NotNull
    public String toPiB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, PIB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    public double toEB() {
        return convert(sizeInBits, EB_BITS);
    }

    @NotNull
    public String toEB(@NotNull final String formatString) {
        final double number = convert(sizeInBits, EB_BITS);
        return new DecimalFormat(formatString).format(number);
    }

    private static double convert(final long sizeInBits,
                                  final long conversionSize) {

        return 1.0D * sizeInBits / conversionSize;
    }
}
