package com.alivedev.bankscan

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar


class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private lateinit var scannerView: ScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).fullScreen(true).init()
        scannerView = findViewById(R.id.sv)
        scannerView.setViewFinder(DefaultViewFinder(this))
        scannerView.setCallback { result ->
            Toast.makeText(this, result.data, Toast.LENGTH_SHORT).show()
            scannerView.restartPreviewAfterDelay(2000)
        }
    }

    override fun onResume() {
        super.onResume()
        scannerView.onResume()
    }


    override fun onPause() {
        super.onPause()
        scannerView.onPause()
    }

}