package com.miramicodigo.webview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        ibIr.setOnClickListener {
            irPaginaWeb(etURL.text.toString())
            hideSoftKeyboard()
        }

        etURL.setOnEditorActionListener { textView, i, keyEvent ->
            return@setOnEditorActionListener when(i) {
                EditorInfo.IME_ACTION_GO -> {
                    irPaginaWeb(etURL.text.toString())
                    hideSoftKeyboard()
                    true
                }
                else -> false
            }
        }

    }

    fun irPaginaWeb(url: String) {
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.setInitialScale(100)
        webView.loadUrl("https://$url")
    }

    fun hideSoftKeyboard() {
        if(currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}