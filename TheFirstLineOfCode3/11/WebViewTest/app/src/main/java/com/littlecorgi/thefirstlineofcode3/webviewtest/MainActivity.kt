package com.littlecorgi.thefirstlineofcode3.webviewtest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.INTERNET), 1)
        } else {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.baidu.com/")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = WebViewClient()
                    webView.loadUrl("https://www.baidu.com/")
                } else {
                    Toast.makeText(this, "没有联网权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}