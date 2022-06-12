package com.example.atg.models

data class SectionItem(val sectionId: Int, val title: String, val content: String) : Section {
    override fun type(): Int {
        return Section.ITEM
    }

    override fun sectionId(): Int {
        return sectionId
    }
}

fun SectionItem.getDummyData(sectionId: Int): SectionItem {
    return SectionItem(sectionId, "What is net neutrality?", "Net neutrality is the idea that all")
}