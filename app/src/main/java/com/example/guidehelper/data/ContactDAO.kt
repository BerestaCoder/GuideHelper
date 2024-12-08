package com.example.guidehelper.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE id IN (:contactsIds)")
    fun loadAllByIds(contactsIds: IntArray): List<Event>

    @Query("SELECT * FROM event WHERE id = :id")
    fun getContactById(id: Int)

    @Insert
    fun insertAll(vararg contacts: Event)

    @Delete
    fun delete(contact: Event)
}