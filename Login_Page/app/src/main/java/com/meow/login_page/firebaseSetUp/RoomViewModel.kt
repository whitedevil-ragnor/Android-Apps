package com.meow.login_page.firebaseSetUp

import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class RoomViewModel :ViewModel(){
    private val _rooms=MutableLiveData<List<Room>>()
    public val rooms:LiveData<List<Room>>get() =_rooms
    private var roomRepository: RoomRepository
    object Injection {
        private val instance: FirebaseFirestore by lazy {
            FirebaseFirestore.getInstance()
        }

        fun instance(): FirebaseFirestore {
            return instance
        }
    }
    init {
        roomRepository=RoomRepository(Injection.instance())
        loadRooms()
    }
    fun createRoom(name:String){
        viewModelScope.launch {
            roomRepository.createRoom(name = name)
        }
    }
    fun loadRooms(){
        viewModelScope.launch {
            when(val result=roomRepository.getRooms()){
                is Result.Success->_rooms.value=result.data
                is Result.Error->{

                }
            }
        }

    }    }

