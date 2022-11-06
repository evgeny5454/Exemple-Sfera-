package com.evgeny5454.exemplesfera.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.evgeny5454.exemplesfera.data.model.UserTwo


@Dao
interface AppDao {

    @Query("SELECT * FROM repositories")
    fun getAllRecords(): LiveData<List<UserTwo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(userTwo: UserTwo)

    @Query("SELECT * FROM repositories WHERE fullName LIKE '%' ||:searchQuery || '%'")
    fun search(searchQuery: String): LiveData<List<UserTwo>>

    @Update
    fun updateUser(userTwo: UserTwo)

}