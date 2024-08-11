package com.itl.kglab.notedatabase.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteEntityReadWriteTest {

    private lateinit var dao: NoteDao
    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.noteDao()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun testAddAndReadNote() = runBlocking {
        dao.addList(getTestList())
        val note = NoteData(title = "TestTitle0", date = "2024/08/22", note = "NoteNote0")
        dao.addNote(note)
        val target = dao.getAll().find { it.title == note.title }
        // 取出來會是一個新的物件，所以要比對內容
        Assert.assertEquals(note.title, target?.title)
    }

    @Test
    fun testDeleteNote() = runBlocking {
        dao.addList(getTestList())
        dao.deleteById(1)
        val target = dao.getNoteById(1)
        Assert.assertNull(target)
    }

    @Test
    fun testEditNote() = runBlocking {
        dao.addList(getTestList())
        val note = NoteData(id = 3, title = "This is a edited note", date = "2024/08/22", note = "NoteNote0")
        dao.editNote(note)
        val target = dao.getNoteById(3)

        Assert.assertEquals(note.title, target?.title)
    }


    private fun getTestList(): List<NoteData> {
        return mutableListOf<NoteData>().apply {
            repeat(10) {
                add(NoteData(title = "TestTitle$it", date = "2024/08/22", note = "NoteNote$it"))
            }
        }
    }
}