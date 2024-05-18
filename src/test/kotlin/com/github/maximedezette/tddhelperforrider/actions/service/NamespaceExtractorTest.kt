package com.github.maximedezette.tddhelperforrider.actions.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class NamespaceExtractorTest {

    @Test
    fun shouldExtractNamespaceFromFileContent() {
        val fileContent = getFileContent()

        val namespace = NamespaceExtractor.extract(fileContent)

        val expected = "namespace PlantAPITest;"
        assertEquals(expected, namespace);
    }

    private fun getFileContent() = "using Microsoft.AspNetCore.Authentication.OAuth.Claims;\n" +
            "using PlantAPI;\n" +
            "\n" +
            "namespace PlantAPITest;\n" +
            "\n" +
            "public class PlantTest\n" +
            "{\n" +
            "\n" +
            "    [Fact]\n" +
            "    public void METHOD()\n" +
            "    {\n" +
            "        var a = \"\";\n" +
            "        new MyTestClass(a);\n" +
            "    }\n" +
            "}"


}