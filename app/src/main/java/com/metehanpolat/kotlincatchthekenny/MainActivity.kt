package com.metehanpolat.kotlincatchthekenny

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.metehanpolat.kotlincatchthekenny.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun play(view: View){
        val intent = Intent(this@MainActivity,GameActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun quit(view: View){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Quit")
        alert.setMessage("Are You Sure?")
        alert.setPositiveButton("Yes") { dialog, which ->
            finish()
        }
        alert.setNegativeButton("No") {dialog, which ->
            dialog.cancel()
        }

        alert.show()
    }
}