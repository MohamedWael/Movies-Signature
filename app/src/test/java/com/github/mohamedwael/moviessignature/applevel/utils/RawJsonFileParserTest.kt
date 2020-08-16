package com.github.mohamedwael.moviessignature.applevel.utils

import android.content.Context
import android.content.res.Resources
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class RawJsonFileParserTest {

    @Test
    fun testRawJsonFileParser() {
        val mockedContext = Mockito.mock(Context::class.java)
        val mockedResources = Mockito.mock(Resources::class.java)
        val json = "\"1\""
        val inputStream = json.byteInputStream()
        Mockito.`when`(mockedContext.resources).thenReturn(mockedResources)
        Mockito.`when`(mockedResources.openRawResource(1)).thenReturn(inputStream)
        val string = RawJsonFileParser<String>(mockedContext)(1, String::class.java)
        assertEquals("1", string)
    }
}