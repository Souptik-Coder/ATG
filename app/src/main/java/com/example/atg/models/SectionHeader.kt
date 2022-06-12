package com.example.atg.models

data class SectionHeader(val sectionId: Int, val title:String) : Section {
    override fun type(): Int {
        return Section.HEADER
    }

    override fun sectionId(): Int {
        return sectionId
    }
}