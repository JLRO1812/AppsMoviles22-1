package edu.co.icesi.semana7kotlinv2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Counter : ViewModel(){

    private var i = MutableLiveData(0)

    //Dato observable
    val iLive : LiveData<Int> get() = i

    fun start(){
        viewModelScope.launch(Dispatchers.IO) {
            //Corre en segundo plano
            while (true){
                withContext(Dispatchers.Main){
                    i.value = i.value?.plus(1)
                }
                delay(1000)
                Log.e(">>>",">>>"+i.value)
            }
        }
    }

}