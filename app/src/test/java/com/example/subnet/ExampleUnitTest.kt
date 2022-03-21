package com.example.subnet

import com.example.subnet.model.Address
import com.example.subnet.model.IPAddress
import com.example.subnet.model.SubnetMask
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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
        assertEquals((256*256*256) + (256 * 256) + 256 + 1, Address(1, 1, 1, 1).numericValue)
    }
}