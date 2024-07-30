package ca.mpringle.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ByteSizeTest {

    @Test
    void fomBitsShouldWorkForBase2() {

        final long onePibInBits = 8L * 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromBits(onePibInBits).toBits());
        assertEquals("9007199254740992", ByteSize.fromBits(onePibInBits).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromBits(onePibInBits).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromBits(onePibInBits).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromBits(onePibInBits).toBytes());
        assertEquals("1125899906842624", ByteSize.fromBits(onePibInBits).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromBits(onePibInBits).toKiB());
        assertEquals("1099511627776", ByteSize.fromBits(onePibInBits).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromBits(onePibInBits).toMiB());
        assertEquals("1073741824", ByteSize.fromBits(onePibInBits).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromBits(onePibInBits).toGiB());
        assertEquals("1048576", ByteSize.fromBits(onePibInBits).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromBits(onePibInBits).toTiB());
        assertEquals("1024", ByteSize.fromBits(onePibInBits).toTiB("#"));

        assertEquals(1D, ByteSize.fromBits(onePibInBits).toPiB());
        assertEquals("1", ByteSize.fromBits(onePibInBits).toPiB("#"));
    }

    @Test
    void fomBitsShouldWorkForBase10() {

        final long onePibInBits = 8L * 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromBits(onePibInBits).toKB());
        assertEquals("1125899906842.624", ByteSize.fromBits(onePibInBits).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromBits(onePibInBits).toMB());
        assertEquals("1125899906.843", ByteSize.fromBits(onePibInBits).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromBits(onePibInBits).toGB());
        assertEquals("1125899.907", ByteSize.fromBits(onePibInBits).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromBits(onePibInBits).toTB());
        assertEquals("1125.9", ByteSize.fromBits(onePibInBits).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromBits(onePibInBits).toPB());
        assertEquals("1.126", ByteSize.fromBits(onePibInBits).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromBits(onePibInBits).toEB());
        assertEquals("0.001", ByteSize.fromBits(onePibInBits).toEB("#.###"));
    }

    @Test
    void fomNibblesShouldWork() {

        final long onePibInNibbles = 2L * 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromNibbles(onePibInNibbles).toBits());
        assertEquals("9007199254740992", ByteSize.fromNibbles(onePibInNibbles).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromNibbles(onePibInNibbles).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromNibbles(onePibInNibbles).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromNibbles(onePibInNibbles).toBytes());
        assertEquals("1125899906842624", ByteSize.fromNibbles(onePibInNibbles).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromNibbles(onePibInNibbles).toKiB());
        assertEquals("1099511627776", ByteSize.fromNibbles(onePibInNibbles).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromNibbles(onePibInNibbles).toMiB());
        assertEquals("1073741824", ByteSize.fromNibbles(onePibInNibbles).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromNibbles(onePibInNibbles).toGiB());
        assertEquals("1048576", ByteSize.fromNibbles(onePibInNibbles).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromNibbles(onePibInNibbles).toTiB());
        assertEquals("1024", ByteSize.fromNibbles(onePibInNibbles).toTiB("#"));

        assertEquals(1D, ByteSize.fromNibbles(onePibInNibbles).toPiB());
        assertEquals("1", ByteSize.fromNibbles(onePibInNibbles).toPiB("#"));
    }

    @Test
    void fomNibblesShouldWorkForBase10() {

        final long onePibInNibbles = 2L * 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromNibbles(onePibInNibbles).toKB());
        assertEquals("1125899906842.624", ByteSize.fromNibbles(onePibInNibbles).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromNibbles(onePibInNibbles).toMB());
        assertEquals("1125899906.843", ByteSize.fromNibbles(onePibInNibbles).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromNibbles(onePibInNibbles).toGB());
        assertEquals("1125899.907", ByteSize.fromNibbles(onePibInNibbles).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromNibbles(onePibInNibbles).toTB());
        assertEquals("1125.9", ByteSize.fromNibbles(onePibInNibbles).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromNibbles(onePibInNibbles).toPB());
        assertEquals("1.126", ByteSize.fromNibbles(onePibInNibbles).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromNibbles(onePibInNibbles).toEB());
        assertEquals("0.001", ByteSize.fromNibbles(onePibInNibbles).toEB("#.###"));
    }

    @Test
    void fromBytesShouldWorkForBase2() {

        final long onePibInBytes = 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromBytes(onePibInBytes).toBits());
        assertEquals("9007199254740992", ByteSize.fromBytes(onePibInBytes).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromBytes(onePibInBytes).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromBytes(onePibInBytes).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromBytes(onePibInBytes).toBytes());
        assertEquals("1125899906842624", ByteSize.fromBytes(onePibInBytes).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromBytes(onePibInBytes).toKiB());
        assertEquals("1099511627776", ByteSize.fromBytes(onePibInBytes).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromBytes(onePibInBytes).toMiB());
        assertEquals("1073741824", ByteSize.fromBytes(onePibInBytes).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromBytes(onePibInBytes).toGiB());
        assertEquals("1048576", ByteSize.fromBytes(onePibInBytes).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromBytes(onePibInBytes).toTiB());
        assertEquals("1024", ByteSize.fromBytes(onePibInBytes).toTiB("#"));

        assertEquals(1D, ByteSize.fromBytes(onePibInBytes).toPiB());
        assertEquals("1", ByteSize.fromBytes(onePibInBytes).toPiB("#"));
    }

    @Test
    void fromBytesShouldWorkForBase10() {

        final long onePibInBytes = 1024L * 1024L * 1024L * 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromBytes(onePibInBytes).toKB());
        assertEquals("1125899906842.624", ByteSize.fromBytes(onePibInBytes).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromBytes(onePibInBytes).toMB());
        assertEquals("1125899906.843", ByteSize.fromBytes(onePibInBytes).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromBytes(onePibInBytes).toGB());
        assertEquals("1125899.907", ByteSize.fromBytes(onePibInBytes).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromBytes(onePibInBytes).toTB());
        assertEquals("1125.9", ByteSize.fromBytes(onePibInBytes).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromBytes(onePibInBytes).toPB());
        assertEquals("1.126", ByteSize.fromBytes(onePibInBytes).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromBytes(onePibInBytes).toEB());
        assertEquals("0.001", ByteSize.fromBytes(onePibInBytes).toEB("#.###"));
    }

    @Test
    void fromKbShouldWorkForBase2() {

        final double onePibInKb = 1_125_899_906_842.624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromKb(onePibInKb).toBits());
        assertEquals("9007199254740992", ByteSize.fromKb(onePibInKb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromKb(onePibInKb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromKb(onePibInKb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromKb(onePibInKb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromKb(onePibInKb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromKb(onePibInKb).toKiB());
        assertEquals("1099511627776", ByteSize.fromKb(onePibInKb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromKb(onePibInKb).toMiB());
        assertEquals("1073741824", ByteSize.fromKb(onePibInKb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromKb(onePibInKb).toGiB());
        assertEquals("1048576", ByteSize.fromKb(onePibInKb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromKb(onePibInKb).toTiB());
        assertEquals("1024", ByteSize.fromKb(onePibInKb).toTiB("#"));

        assertEquals(1D, ByteSize.fromKb(onePibInKb).toPiB());
        assertEquals("1", ByteSize.fromKb(onePibInKb).toPiB("#"));
    }

    @Test
    void fromKbShouldWorkForBase10() {

        final double onePibInKb = 1_125_899_906_842.624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromKb(onePibInKb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromKb(onePibInKb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromKb(onePibInKb).toMB());
        assertEquals("1125899906.843", ByteSize.fromKb(onePibInKb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromKb(onePibInKb).toGB());
        assertEquals("1125899.907", ByteSize.fromKb(onePibInKb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromKb(onePibInKb).toTB());
        assertEquals("1125.9", ByteSize.fromKb(onePibInKb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromKb(onePibInKb).toPB());
        assertEquals("1.126", ByteSize.fromKb(onePibInKb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromKb(onePibInKb).toEB());
        assertEquals("0.001", ByteSize.fromKb(onePibInKb).toEB("#.###"));
    }

    @Test
    void fromKibShouldWorkForBase2() {

        final long onePibInKib = 1024L * 1024L * 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromKib(onePibInKib).toBits());
        assertEquals("9007199254740992", ByteSize.fromKib(onePibInKib).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromKib(onePibInKib).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromKib(onePibInKib).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromKib(onePibInKib).toBytes());
        assertEquals("1125899906842624", ByteSize.fromKib(onePibInKib).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromKib(onePibInKib).toKiB());
        assertEquals("1099511627776", ByteSize.fromKib(onePibInKib).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromKib(onePibInKib).toMiB());
        assertEquals("1073741824", ByteSize.fromKib(onePibInKib).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromKib(onePibInKib).toGiB());
        assertEquals("1048576", ByteSize.fromKib(onePibInKib).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromKib(onePibInKib).toTiB());
        assertEquals("1024", ByteSize.fromKib(onePibInKib).toTiB("#"));

        assertEquals(1D, ByteSize.fromKib(onePibInKib).toPiB());
        assertEquals("1", ByteSize.fromKib(onePibInKib).toPiB("#"));
    }

    @Test
    void fromKibShouldWorkForBase10() {

        final long onePibInKib = 1024L * 1024L * 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromKib(onePibInKib).toKB());
        assertEquals("1125899906842.624", ByteSize.fromKib(onePibInKib).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromKib(onePibInKib).toMB());
        assertEquals("1125899906.843", ByteSize.fromKib(onePibInKib).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromKib(onePibInKib).toGB());
        assertEquals("1125899.907", ByteSize.fromKib(onePibInKib).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromKib(onePibInKib).toTB());
        assertEquals("1125.9", ByteSize.fromKib(onePibInKib).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromKib(onePibInKib).toPB());
        assertEquals("1.126", ByteSize.fromKib(onePibInKib).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromKib(onePibInKib).toEB());
        assertEquals("0.001", ByteSize.fromKib(onePibInKib).toEB("#.###"));
    }

    @Test
    void fromMbShouldWorkForBase2() {

        final double onePibInMb = 1_125_899_906.842_624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromMb(onePibInMb).toBits());
        assertEquals("9007199254740992", ByteSize.fromMb(onePibInMb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromMb(onePibInMb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromMb(onePibInMb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromMb(onePibInMb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromMb(onePibInMb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromMb(onePibInMb).toKiB());
        assertEquals("1099511627776", ByteSize.fromMb(onePibInMb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromMb(onePibInMb).toMiB());
        assertEquals("1073741824", ByteSize.fromMb(onePibInMb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromMb(onePibInMb).toGiB());
        assertEquals("1048576", ByteSize.fromMb(onePibInMb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromMb(onePibInMb).toTiB());
        assertEquals("1024", ByteSize.fromMb(onePibInMb).toTiB("#"));

        assertEquals(1D, ByteSize.fromMb(onePibInMb).toPiB());
        assertEquals("1", ByteSize.fromMb(onePibInMb).toPiB("#"));
    }

    @Test
    void fromMbShouldWorkForBase10() {

        final double onePibInMb = 1_125_899_906.842_624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromMb(onePibInMb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromMb(onePibInMb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromMb(onePibInMb).toMB());
        assertEquals("1125899906.843", ByteSize.fromMb(onePibInMb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromMb(onePibInMb).toGB());
        assertEquals("1125899.907", ByteSize.fromMb(onePibInMb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromMb(onePibInMb).toTB());
        assertEquals("1125.9", ByteSize.fromMb(onePibInMb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromMb(onePibInMb).toPB());
        assertEquals("1.126", ByteSize.fromMb(onePibInMb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromMb(onePibInMb).toEB());
        assertEquals("0.001", ByteSize.fromMb(onePibInMb).toEB("#.###"));
    }

    @Test
    void fromMibShouldWorkForBase2() {

        final long onePibInMib = 1024L * 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromMib(onePibInMib).toBits());
        assertEquals("9007199254740992", ByteSize.fromMib(onePibInMib).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromMib(onePibInMib).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromMib(onePibInMib).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromMib(onePibInMib).toBytes());
        assertEquals("1125899906842624", ByteSize.fromMib(onePibInMib).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromMib(onePibInMib).toKiB());
        assertEquals("1099511627776", ByteSize.fromMib(onePibInMib).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromMib(onePibInMib).toMiB());
        assertEquals("1073741824", ByteSize.fromMib(onePibInMib).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromMib(onePibInMib).toGiB());
        assertEquals("1048576", ByteSize.fromMib(onePibInMib).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromMib(onePibInMib).toTiB());
        assertEquals("1024", ByteSize.fromMib(onePibInMib).toTiB("#"));

        assertEquals(1D, ByteSize.fromMib(onePibInMib).toPiB());
        assertEquals("1", ByteSize.fromMib(onePibInMib).toPiB("#"));
    }

    @Test
    void fromMibShouldWorkForBase10() {

        final long onePibInMib = 1024L * 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromMib(onePibInMib).toKB());
        assertEquals("1125899906842.624", ByteSize.fromMib(onePibInMib).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromMib(onePibInMib).toMB());
        assertEquals("1125899906.843", ByteSize.fromMib(onePibInMib).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromMib(onePibInMib).toGB());
        assertEquals("1125899.907", ByteSize.fromMib(onePibInMib).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromMib(onePibInMib).toTB());
        assertEquals("1125.9", ByteSize.fromMib(onePibInMib).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromMib(onePibInMib).toPB());
        assertEquals("1.126", ByteSize.fromMib(onePibInMib).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromMib(onePibInMib).toEB());
        assertEquals("0.001", ByteSize.fromMib(onePibInMib).toEB("#.###"));
    }

    @Test
    void fromGbShouldWorkForBase2() {

        final double onePibInGb = 1_125_899.906_842_624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromGb(onePibInGb).toBits());
        assertEquals("9007199254740992", ByteSize.fromGb(onePibInGb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromGb(onePibInGb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromGb(onePibInGb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromGb(onePibInGb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromGb(onePibInGb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromGb(onePibInGb).toKiB());
        assertEquals("1099511627776", ByteSize.fromGb(onePibInGb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromGb(onePibInGb).toMiB());
        assertEquals("1073741824", ByteSize.fromGb(onePibInGb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromGb(onePibInGb).toGiB());
        assertEquals("1048576", ByteSize.fromGb(onePibInGb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromGb(onePibInGb).toTiB());
        assertEquals("1024", ByteSize.fromGb(onePibInGb).toTiB("#"));

        assertEquals(1D, ByteSize.fromGb(onePibInGb).toPiB());
        assertEquals("1", ByteSize.fromGb(onePibInGb).toPiB("#"));
    }

    @Test
    void fromGbShouldWorkForBase10() {

        final double onePibInGb = 1_125_899.906_842_624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromGb(onePibInGb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromGb(onePibInGb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromGb(onePibInGb).toMB());
        assertEquals("1125899906.843", ByteSize.fromGb(onePibInGb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromGb(onePibInGb).toGB());
        assertEquals("1125899.907", ByteSize.fromGb(onePibInGb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromGb(onePibInGb).toTB());
        assertEquals("1125.9", ByteSize.fromGb(onePibInGb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromGb(onePibInGb).toPB());
        assertEquals("1.126", ByteSize.fromGb(onePibInGb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromGb(onePibInGb).toEB());
        assertEquals("0.001", ByteSize.fromGb(onePibInGb).toEB("#.###"));
    }

    @Test
    void fromGibShouldWorkForBase2() {

        final long onePibInGib = 1024L * 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromGib(onePibInGib).toBits());
        assertEquals("9007199254740992", ByteSize.fromGib(onePibInGib).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromGib(onePibInGib).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromGib(onePibInGib).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromGib(onePibInGib).toBytes());
        assertEquals("1125899906842624", ByteSize.fromGib(onePibInGib).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromGib(onePibInGib).toKiB());
        assertEquals("1099511627776", ByteSize.fromGib(onePibInGib).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromGib(onePibInGib).toMiB());
        assertEquals("1073741824", ByteSize.fromGib(onePibInGib).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromGib(onePibInGib).toGiB());
        assertEquals("1048576", ByteSize.fromGib(onePibInGib).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromGib(onePibInGib).toTiB());
        assertEquals("1024", ByteSize.fromGib(onePibInGib).toTiB("#"));

        assertEquals(1D, ByteSize.fromGib(onePibInGib).toPiB());
        assertEquals("1", ByteSize.fromGib(onePibInGib).toPiB("#"));
    }

    @Test
    void fromGibShouldWorkForBase10() {

        final long onePibInGib = 1024L * 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromGib(onePibInGib).toKB());
        assertEquals("1125899906842.624", ByteSize.fromGib(onePibInGib).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromGib(onePibInGib).toMB());
        assertEquals("1125899906.843", ByteSize.fromGib(onePibInGib).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromGib(onePibInGib).toGB());
        assertEquals("1125899.907", ByteSize.fromGib(onePibInGib).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromGib(onePibInGib).toTB());
        assertEquals("1125.9", ByteSize.fromGib(onePibInGib).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromGib(onePibInGib).toPB());
        assertEquals("1.126", ByteSize.fromGib(onePibInGib).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromGib(onePibInGib).toEB());
        assertEquals("0.001", ByteSize.fromGib(onePibInGib).toEB("#.###"));
    }

    @Test
    void fromTbShouldWorkBase2() {

        final double onePibInTb = 1_125.899_906_842_624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromTb(onePibInTb).toBits());
        assertEquals("9007199254740992", ByteSize.fromTb(onePibInTb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromTb(onePibInTb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromTb(onePibInTb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromTb(onePibInTb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromTb(onePibInTb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromTb(onePibInTb).toKiB());
        assertEquals("1099511627776", ByteSize.fromTb(onePibInTb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromTb(onePibInTb).toMiB());
        assertEquals("1073741824", ByteSize.fromTb(onePibInTb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromTb(onePibInTb).toGiB());
        assertEquals("1048576", ByteSize.fromTb(onePibInTb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromTb(onePibInTb).toTiB());
        assertEquals("1024", ByteSize.fromTb(onePibInTb).toTiB("#"));

        assertEquals(1D, ByteSize.fromTb(onePibInTb).toPiB());
        assertEquals("1", ByteSize.fromTb(onePibInTb).toPiB("#"));
    }

    @Test
    void fromTbShouldWorkForBase10() {

        final double onePibInTb = 1_125.899_906_842_624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromTb(onePibInTb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromTb(onePibInTb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromTb(onePibInTb).toMB());
        assertEquals("1125899906.843", ByteSize.fromTb(onePibInTb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromTb(onePibInTb).toGB());
        assertEquals("1125899.907", ByteSize.fromTb(onePibInTb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromTb(onePibInTb).toTB());
        assertEquals("1125.9", ByteSize.fromTb(onePibInTb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromTb(onePibInTb).toPB());
        assertEquals("1.126", ByteSize.fromTb(onePibInTb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromTb(onePibInTb).toEB());
        assertEquals("0.001", ByteSize.fromTb(onePibInTb).toEB("#.###"));
    }

    @Test
    void fromTibShouldWorkForBase2() {

        final long onePibInTib = 1024L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromTib(onePibInTib).toBits());
        assertEquals("9007199254740992", ByteSize.fromTib(onePibInTib).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromTib(onePibInTib).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromTib(onePibInTib).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromTib(onePibInTib).toBytes());
        assertEquals("1125899906842624", ByteSize.fromTib(onePibInTib).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromTib(onePibInTib).toKiB());
        assertEquals("1099511627776", ByteSize.fromTib(onePibInTib).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromTib(onePibInTib).toMiB());
        assertEquals("1073741824", ByteSize.fromTib(onePibInTib).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromTib(onePibInTib).toGiB());
        assertEquals("1048576", ByteSize.fromTib(onePibInTib).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromTib(onePibInTib).toTiB());
        assertEquals("1024", ByteSize.fromTib(onePibInTib).toTiB("#"));

        assertEquals(1D, ByteSize.fromTib(onePibInTib).toPiB());
        assertEquals("1", ByteSize.fromTib(onePibInTib).toPiB("#"));
    }

    @Test
    void fromTibShouldWorkForBase10() {

        final long onePibInTib = 1024L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromTib(onePibInTib).toKB());
        assertEquals("1125899906842.624", ByteSize.fromTib(onePibInTib).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromTib(onePibInTib).toMB());
        assertEquals("1125899906.843", ByteSize.fromTib(onePibInTib).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromTib(onePibInTib).toGB());
        assertEquals("1125899.907", ByteSize.fromTib(onePibInTib).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromTib(onePibInTib).toTB());
        assertEquals("1125.9", ByteSize.fromTib(onePibInTib).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromTib(onePibInTib).toPB());
        assertEquals("1.126", ByteSize.fromTib(onePibInTib).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromTib(onePibInTib).toEB());
        assertEquals("0.001", ByteSize.fromTib(onePibInTib).toEB("#.###"));
    }

    @Test
    void fromPbShouldWorkForBase2() {

        final double onePibInPb = 1.125_899_906_842_624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromPb(onePibInPb).toBits());
        assertEquals("9007199254740992", ByteSize.fromPb(onePibInPb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromPb(onePibInPb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromPb(onePibInPb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromPb(onePibInPb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromPb(onePibInPb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromPb(onePibInPb).toKiB());
        assertEquals("1099511627776", ByteSize.fromPb(onePibInPb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromPb(onePibInPb).toMiB());
        assertEquals("1073741824", ByteSize.fromPb(onePibInPb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromPb(onePibInPb).toGiB());
        assertEquals("1048576", ByteSize.fromPb(onePibInPb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromPb(onePibInPb).toTiB());
        assertEquals("1024", ByteSize.fromPb(onePibInPb).toTiB("#"));

        assertEquals(1D, ByteSize.fromPb(onePibInPb).toPiB());
        assertEquals("1", ByteSize.fromPb(onePibInPb).toPiB("#"));
    }

    @Test
    void fromPbShouldWorkForBase10() {

        final double onePibInPb = 1.125_899_906_842_624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromPb(onePibInPb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromPb(onePibInPb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromPb(onePibInPb).toMB());
        assertEquals("1125899906.843", ByteSize.fromPb(onePibInPb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromPb(onePibInPb).toGB());
        assertEquals("1125899.907", ByteSize.fromPb(onePibInPb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromPb(onePibInPb).toTB());
        assertEquals("1125.9", ByteSize.fromPb(onePibInPb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromPb(onePibInPb).toPB());
        assertEquals("1.126", ByteSize.fromPb(onePibInPb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromPb(onePibInPb).toEB());
        assertEquals("0.001", ByteSize.fromPb(onePibInPb).toEB("#.###"));
    }

    @Test
    void fromPibShouldWorkForBase2() {

        final long onePibInPib = 1L;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromPib(onePibInPib).toBits());
        assertEquals("9007199254740992", ByteSize.fromPib(onePibInPib).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromPib(onePibInPib).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromPib(onePibInPib).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromPib(onePibInPib).toBytes());
        assertEquals("1125899906842624", ByteSize.fromPib(onePibInPib).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromPib(onePibInPib).toKiB());
        assertEquals("1099511627776", ByteSize.fromPib(onePibInPib).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromPib(onePibInPib).toMiB());
        assertEquals("1073741824", ByteSize.fromPib(onePibInPib).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromPib(onePibInPib).toGiB());
        assertEquals("1048576", ByteSize.fromPib(onePibInPib).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromPib(onePibInPib).toTiB());
        assertEquals("1024", ByteSize.fromPib(onePibInPib).toTiB("#"));

        assertEquals(1D, ByteSize.fromPib(onePibInPib).toPiB());
        assertEquals("1", ByteSize.fromPib(onePibInPib).toPiB("#"));
    }

    @Test
    void fromPibShouldWorkForBase10() {

        final long onePibInPib = 1L;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromPib(onePibInPib).toKB());
        assertEquals("1125899906842.624", ByteSize.fromPib(onePibInPib).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromPib(onePibInPib).toMB());
        assertEquals("1125899906.843", ByteSize.fromPib(onePibInPib).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromPib(onePibInPib).toGB());
        assertEquals("1125899.907", ByteSize.fromPib(onePibInPib).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromPib(onePibInPib).toTB());
        assertEquals("1125.9", ByteSize.fromPib(onePibInPib).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromPib(onePibInPib).toPB());
        assertEquals("1.126", ByteSize.fromPib(onePibInPib).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromPib(onePibInPib).toEB());
        assertEquals("0.001", ByteSize.fromPib(onePibInPib).toEB("#.###"));
    }

    @Test
    void fromEbShouldWorkForBase2() {

        final double onePibInEb = 0.001_125_899_906_842_624D;

        assertEquals(9_007_199_254_740_992D, ByteSize.fromEb(onePibInEb).toBits());
        assertEquals("9007199254740992", ByteSize.fromEb(onePibInEb).toBits("#"));

        assertEquals(2_251_799_813_685_248D, ByteSize.fromEb(onePibInEb).toNibbles());
        assertEquals("2251799813685248", ByteSize.fromEb(onePibInEb).toNibbles("#"));

        assertEquals(1_125_899_906_842_624D, ByteSize.fromEb(onePibInEb).toBytes());
        assertEquals("1125899906842624", ByteSize.fromEb(onePibInEb).toBytes("#"));

        assertEquals(1_099_511_627_776D, ByteSize.fromEb(onePibInEb).toKiB());
        assertEquals("1099511627776", ByteSize.fromEb(onePibInEb).toKiB("#"));

        assertEquals(1_073_741_824D, ByteSize.fromEb(onePibInEb).toMiB());
        assertEquals("1073741824", ByteSize.fromEb(onePibInEb).toMiB("#"));

        assertEquals(1_048_576D, ByteSize.fromEb(onePibInEb).toGiB());
        assertEquals("1048576", ByteSize.fromEb(onePibInEb).toGiB("#"));

        assertEquals(1_024D, ByteSize.fromEb(onePibInEb).toTiB());
        assertEquals("1024", ByteSize.fromEb(onePibInEb).toTiB("#"));

        assertEquals(1D, ByteSize.fromEb(onePibInEb).toPiB());
        assertEquals("1", ByteSize.fromEb(onePibInEb).toPiB("#"));
    }

    @Test
    void fromEbShouldWorkForBase10() {

        final double onePibInEb = 0.001_125_899_906_842_624D;

        assertEquals(1_125_899_906_842.624D, ByteSize.fromEb(onePibInEb).toKB());
        assertEquals("1125899906842.624", ByteSize.fromEb(onePibInEb).toKB("#.###"));

        assertEquals(1_125_899_906.842_624D, ByteSize.fromEb(onePibInEb).toMB());
        assertEquals("1125899906.843", ByteSize.fromEb(onePibInEb).toMB("#.###"));

        assertEquals(1_125_899.906_842_624D, ByteSize.fromEb(onePibInEb).toGB());
        assertEquals("1125899.907", ByteSize.fromEb(onePibInEb).toGB("#.###"));

        assertEquals(1_125.899_906_842_624D, ByteSize.fromEb(onePibInEb).toTB());
        assertEquals("1125.9", ByteSize.fromEb(onePibInEb).toTB("#.###"));

        assertEquals(1.125_899_906_842_624D, ByteSize.fromEb(onePibInEb).toPB());
        assertEquals("1.126", ByteSize.fromEb(onePibInEb).toPB("#.###"));

        assertEquals(0.001_125_899_906_842_624D, ByteSize.fromEb(onePibInEb).toEB());
        assertEquals("0.001", ByteSize.fromEb(onePibInEb).toEB("#.###"));
    }
}
