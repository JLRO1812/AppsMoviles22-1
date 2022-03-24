package edu.co.icesi.semana7kotlinv2.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import edu.co.icesi.semana7kotlinv2.viewmodel.Counter
import edu.co.icesi.semana7kotlinv2.viewmodel.HttpUtil
import edu.co.icesi.semana7kotlinv2.databinding.ActivityMainBinding
import edu.co.icesi.semana7kotlinv2.model.Request

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var counter: Counter
    private lateinit var httpUtil: HttpUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Scroll para el textView
        binding.outputTV.movementMethod = ScrollingMovementMethod()

        httpUtil = ViewModelProvider(this).get(HttpUtil::class.java)

        counter = ViewModelProvider(this).get(Counter::class.java)

        counter.start()

        counter.iLive.observe(this, Observer {
            binding.counterTV.text = "$it"
        })

        binding.goBtn.setOnClickListener(::goToSite)
    }
    fun goToSite(v: View){
        httpUtil.responseLive.observe(this, Observer {
            binding.outputTV.text = it
        })
        httpUtil.GETRequest(binding.siteET.text.toString())
    }

    override fun onPause() {
        super.onPause()
        val request = Request(binding.siteET.text.toString(), binding.outputTV.text.toString())
        
        //Serialización
        val json = Gson().toJson(request)
        Log.e(">>>",json)
        
        //Deserialización
        val objdeserie = Gson().fromJson(json, Request::class.java)
        Log.e(">>>",objdeserie.url)
        Log.e(">>>",objdeserie.responde)
    }
}