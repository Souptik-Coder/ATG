package com.example.atg.models

interface Section {
    fun type(): Int
    fun sectionId(): Int

    companion object {
        const val HEADER = 0
        const val ITEM = 1
    }
}