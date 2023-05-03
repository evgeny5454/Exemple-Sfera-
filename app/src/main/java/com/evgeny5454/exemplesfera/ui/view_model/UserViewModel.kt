package com.evgeny5454.exemplesfera.ui.view_model

import androidx.lifecycle.*
import com.evgeny5454.exemplesfera.data.entities.User
import com.evgeny5454.exemplesfera.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {

    private var _searchText = MutableLiveData<String>()
    var searchText: LiveData<String> = _searchText

    private var _download = MutableLiveData<Boolean>()
    var download: LiveData<Boolean> = _download

    init {
        _download.value = false
    }

    fun getAllRepositoryList() = repository.getAllRecords()


    fun setUserList() {
        repository.getPersonList()
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
    }

    fun search(searchQuery: String) = repository.search(searchQuery)

    fun setDownloadState() {
        _download.value = true
    }
    fun initSearchText(text: String){
        _searchText.postValue(text)
    }
}