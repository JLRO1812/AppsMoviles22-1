package edu.co.icesi.deezertest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SongListViewModel : ViewModel() {

    //Mutable hace observable el array
    var songList: MutableLiveData<ArrayList<Song>> = MutableLiveData()

    fun GETListOfSongs(search:String){
        //LLamado a Internet
        viewModelScope.launch(Dispatchers.IO) {
            //Se ejcuta en un hilo aparte
            val url = URL("https://api.deezer.com/search?q=$search")
            val connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"
            val json = connection.inputStream.bufferedReader().readText()
            Log.e(">>>", json)
        }
    }
    //var songList = ArrayList<Song>()
}