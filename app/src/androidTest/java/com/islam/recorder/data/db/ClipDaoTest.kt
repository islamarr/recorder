package com.islam.recorder.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.islam.data.db.AppDatabase
import com.islam.data.db.ClipDao
import com.islam.domain.entites.ClipEntity
import com.islam.recorder.getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ClipDaoTest : TestCase() {

    private lateinit var clipDao: ClipDao
    private lateinit var db: AppDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        clipDao = db.getClipDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertNewClip_ReadInList() = runBlockingTest {
        val clip = ClipEntity(
            "file",
            2100,
            0
        )
        clipDao.upsert(clip)
        val clipList = clipDao.getAllClips().getOrAwaitValue()
        assertThat(clipList[0], equalTo(clip))
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDeleteClip_ReturnEmptyList() = runBlockingTest {
        val clip = ClipEntity(
            "file",
            2100,
            0
        )
        clipDao.upsert(clip)
        clipDao.hideDeletedItem(clipId = 1)
        val clipList = clipDao.getAllClips().getOrAwaitValue()
        assertThat(clipList.size, equalTo(0))
    }

}