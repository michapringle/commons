package ca.mpringle.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ByteSizeTest {

    @Test
    public void fomBitsShouldWork() {

        final long oneKiBInBits = 8L * 1024L;

        assertEquals(8192D, ByteSize.fromBits(oneKiBInBits).toBits());
        assertEquals("8192", ByteSize.fromBits(oneKiBInBits).toBits("#"));

        assertEquals(2048D, ByteSize.fromBits(oneKiBInBits).toNibbles());
        assertEquals("2048", ByteSize.fromBits(oneKiBInBits).toNibbles("#"));

        assertEquals(1024D, ByteSize.fromBits(oneKiBInBits).toBytes());
        assertEquals("1024", ByteSize.fromBits(oneKiBInBits).toBytes("#"));

        assertEquals(1.024D, ByteSize.fromBits(oneKiBInBits).toKB());
        assertEquals("1.024", ByteSize.fromBits(oneKiBInBits).toKB("#.###"));

        assertEquals(1D, ByteSize.fromBits(oneKiBInBits).toKiB());
        assertEquals("1", ByteSize.fromBits(oneKiBInBits).toKiB("#"));
    }

    @Test
    public void fomNibblesShouldWork() {

        final long oneKibInNibbles = 2L * 1024L;

        assertEquals(8192D, ByteSize.fromNibbles(oneKibInNibbles).toBits());
        assertEquals("8192", ByteSize.fromNibbles(oneKibInNibbles).toBits("#"));

        assertEquals(2048D, ByteSize.fromNibbles(oneKibInNibbles).toNibbles());
        assertEquals("2048", ByteSize.fromNibbles(oneKibInNibbles).toNibbles("#"));

        assertEquals(1024D, ByteSize.fromNibbles(oneKibInNibbles).toBytes());
        assertEquals("1024", ByteSize.fromNibbles(oneKibInNibbles).toBytes("#"));

        assertEquals(1.024D, ByteSize.fromNibbles(oneKibInNibbles).toKB());
        assertEquals("1.024", ByteSize.fromNibbles(oneKibInNibbles).toKB("#.###"));

        assertEquals(1D, ByteSize.fromNibbles(oneKibInNibbles).toKiB());
        assertEquals("1", ByteSize.fromNibbles(oneKibInNibbles).toKiB("#"));
    }

    @Test
    public void fromBytesShouldWork() {

        final long oneKibInBytes = 1024L;

        assertEquals(8192D, ByteSize.fromBytes(oneKibInBytes).toBits());
        assertEquals("8192", ByteSize.fromBytes(oneKibInBytes).toBits("#"));

        assertEquals(2048D, ByteSize.fromBytes(oneKibInBytes).toNibbles());
        assertEquals("2048", ByteSize.fromBytes(oneKibInBytes).toNibbles("#"));

        assertEquals(1024D, ByteSize.fromBytes(oneKibInBytes).toBytes());
        assertEquals("1024", ByteSize.fromBytes(oneKibInBytes).toBytes("#"));

        assertEquals(1.024D, ByteSize.fromBytes(oneKibInBytes).toKB());
        assertEquals("1.024", ByteSize.fromBytes(oneKibInBytes).toKB("#.###"));

        assertEquals(1D, ByteSize.fromBytes(oneKibInBytes).toKiB());
        assertEquals("1", ByteSize.fromBytes(oneKibInBytes).toKiB("#"));
    }

    @Test
    public void fromKbShouldWork() {

        final double oneKibInKb = 1.024D;

        assertEquals(8192D, ByteSize.fromKb(oneKibInKb).toBits());
        assertEquals("8192", ByteSize.fromKb(oneKibInKb).toBits("#"));

        assertEquals(2048D, ByteSize.fromKb(oneKibInKb).toNibbles());
        assertEquals("2048", ByteSize.fromKb(oneKibInKb).toNibbles("#"));

        assertEquals(1024D, ByteSize.fromKb(oneKibInKb).toBytes());
        assertEquals("1024", ByteSize.fromKb(oneKibInKb).toBytes("#"));

        assertEquals(1.024D, ByteSize.fromKb(oneKibInKb).toKB());
        assertEquals("1.024", ByteSize.fromKb(oneKibInKb).toKB("#.###"));

        assertEquals(1D, ByteSize.fromKb(oneKibInKb).toKiB());
        assertEquals("1", ByteSize.fromKb(oneKibInKb).toKiB("#"));
    }

    @Test
    public void fromKibShouldWork() {

        final double oneKibInKib = 1D;

        assertEquals(8192D, ByteSize.fromKib(oneKibInKib).toBits());
        assertEquals("8192", ByteSize.fromKib(oneKibInKib).toBits("#"));

        assertEquals(2048D, ByteSize.fromKib(oneKibInKib).toNibbles());
        assertEquals("2048", ByteSize.fromKib(oneKibInKib).toNibbles("#"));

        assertEquals(1024D, ByteSize.fromKib(oneKibInKib).toBytes());
        assertEquals("1024", ByteSize.fromKib(oneKibInKib).toBytes("#"));

        assertEquals(1.024D, ByteSize.fromKib(oneKibInKib).toKB());
        assertEquals("1.024", ByteSize.fromKib(oneKibInKib).toKB("#.###"));

        assertEquals(1D, ByteSize.fromKib(oneKibInKib).toKiB());
        assertEquals("1", ByteSize.fromKib(oneKibInKib).toKiB("#"));
    }
}
