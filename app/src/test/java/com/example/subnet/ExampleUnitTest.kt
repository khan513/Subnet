package com.example.subnet

import com.example.subnet.model.Address
import com.example.subnet.model.IPAddress
import com.example.subnet.model.SubnetMask
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun subnetMask_isCorrect() {
        assertEquals("255.255.255.0", SubnetMask(24).toString())
    }

    @Test
    fun binarySubnetMask_isCorrect() {
        assertEquals("11111111.11111111.11111111.11110000", SubnetMask(28).toBinaryString())
    }

    @Test
    fun networkAddress_isCorrect() {
        assertEquals("192.168.10.0", IPAddress(192, 168, 10, 1).networkAddress.toString())
    }

    @Test
    fun broadcastAddress_isCorrect() {
        assertEquals("192.168.10.255", IPAddress("192.168.10.1").broadcastAddress.toString())
    }

    @Test
    fun numericValue_isCorrect() {
        assertEquals((256 * 256 * 256) + (256 * 256) + 256 + 1, Address(1, 1, 1, 1).numericValue)
    }

    @Test
    fun numberOfSubnets_isCorrect() {
        assertEquals(4, IPAddress("192.168.10.2", 26).numberOfSubnets)
    }

    @Test
    fun totalHostAddresses_isCorrect() {
        assertEquals(64, IPAddress("192.168.10.1", 26).totalNumberOfHosts)
    }

    @Test
    fun usableHostAddresses_isCorrect() {
        assertEquals(126, IPAddress("192.168.10.1", 25).usableNumberOfHosts)
    }

    @Test
    fun ipAddressClass_isCorrect() {
        assertEquals('C', IPAddress(192, 168, 0, 0).addressClass)
    }
}