package edu.co.icesi.semana8kotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(arrayOf(
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
        ),1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){

            var allGrant = true
            for(result in grantResults){
                Log.e(">>>",""+result)
                if(result==PackageManager.PERMISSION_DENIED) allGrant = false
            }
            if(allGrant){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Tiene que aceptar todos los permisos para continuar", Toast.LENGTH_SHORT).show()
            }


        }
    }
}