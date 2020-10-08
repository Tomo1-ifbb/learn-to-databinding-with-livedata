package com.resocoder.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.resocoder.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //？よくわからないけどActivtiyで指定しているtypeのことっぽい
        val mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)

        //Data Binding の設定　
        //thisというのはMainActivityのこと
        //LiveDataがLifecycleを追える？ようにlifecyleOwnerを指定すること
        DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel = mainViewModel
        }

        // LiveDataの役割 observe 編集されたStringデータが変更される度にToastで設定された文言を表示
        mainViewModel.editTextContent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
