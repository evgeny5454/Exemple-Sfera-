package com.evgeny5454.exemplesfera.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.evgeny5454.exemplesfera.data.entities.User


@Dao
interface AppDao {

    @Query("SELECT * FROM user_table")
    fun getAllRecords(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(user: User)

    @Query("SELECT * FROM user_table WHERE fullName LIKE '%' ||:searchQuery || '%'")
    fun search(searchQuery: String): LiveData<List<User>>

    @Update
    fun updateUser(user: User)

}