package com.github.maximedezette.tddhelperforrider.actions.service

class NamespaceExtractor {
    companion object {
        fun extract(fileContent: String): String? {
            val regexPattern = Regex("namespace \\w+;")
            val matchResult = regexPattern.find(fileContent);

            return matchResult?.value
        }
    }
}