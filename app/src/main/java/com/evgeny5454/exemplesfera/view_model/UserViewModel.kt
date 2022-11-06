package com.evgeny5454.exemplesfera.view_model

import android.util.Log
import androidx.lifecycle.*
import com.evgeny5454.exemplesfera.data.model.UserTwo
import com.evgeny5454.exemplesfera.data.repository.MockPersonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repositoryImpl: MockPersonRepositoryImpl) :
    ViewModel() {

    //private var _searchList = MutableLiveData<List<UserTwo>>()
    //var searchList: LiveData<List<UserTwo>> = _searchList

    private var _download = MutableLiveData<Boolean>()
    var download: LiveData<Boolean> = _download

    val tempList = mutableListOf<UserTwo>()

    init {
        _download.value = false
    }

    fun getAllRepositoryList() = repositoryImpl.getAllRecords()


    fun setUserList() {
        repositoryImpl.getPersonList()
    }

    fun updateUser(userTwo: UserTwo) {
        repositoryImpl.updateUser(userTwo)
    }

    fun search(searchQuery: String) = repositoryImpl.search(searchQuery)

    fun setDownloadState() {
        _download.value = true
    }

    /*fun tempList(userList: List<UserTwo>?) {
        if (userList != null) {
            tempList.clear()
            tempList.addAll(userList)
            Log.d("jbsvdkhbdvvds", tempList.toString())
        }
    }*/
}