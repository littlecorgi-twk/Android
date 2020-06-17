package com.littlecorgi.thefirstlineofcode3.jetpacktest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.littlecorgi.thefirstlineofcode3.jetpacktest.room.bean.Book

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>
}