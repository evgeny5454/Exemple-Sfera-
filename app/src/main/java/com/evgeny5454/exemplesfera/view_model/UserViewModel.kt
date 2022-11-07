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

    private var _searchText = MutableLiveData<String>()
    var searchText: LiveData<String> = _searchText

    private var _download = MutableLiveData<Boolean>()
    var download: LiveData<Boolean> = _download

    //val tempList = mutableListOf<UserTwo>()

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
    fun initSearchText(text: String){
        _searchText.postValue(text)
    }
}