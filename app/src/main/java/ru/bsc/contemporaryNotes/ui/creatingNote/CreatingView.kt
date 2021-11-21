package ru.bsc.contemporaryNotes.ui.creatingNote

interface CreatingView {
    fun saveSuccess()
    fun saveError()
    fun inappropriateData()
}