package me.ruyeo.permissionexamplekt

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        requestPermissions()
    }

    private fun hasSmsPermission() = ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
    private fun hasCameraPermission() = ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    private fun hasExternalStrogePermission() = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions(){
        var permissionToRequest = mutableListOf<String>()
        if(!hasExternalStrogePermission()){
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasCameraPermission()){
            permissionToRequest.add(Manifest.permission.CAMERA)
        }
        if(!hasSmsPermission()){
            permissionToRequest.add(Manifest.permission.SEND_SMS)
        }

        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"${permissions[i]} granted",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}