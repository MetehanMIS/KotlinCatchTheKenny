package com.metehanpolat.kotlincatchthekenny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.metehanpolat.kotlincatchthekenny.databinding.ActivityGameBinding
import java.util.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        countDown()
        imageSelect()
        hideImages()
    }
    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: $score"
    }

    fun countDown() {
        //CountDownTimer
        object : CountDownTimer(15500, 1000) {
            override fun onFinish() {
                binding.timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)

                for(image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Out Of Time")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    val alert2 = AlertDialog.Builder(this@GameActivity)
                    alert2.setTitle("Game Over")
                    alert2.setMessage("Choose One")
                    alert2.setPositiveButton("Quit") { dialog, which ->
                        finish()
                    }
                    alert2.setNegativeButton("Main Menu") { dialog, which ->
                        val intent = Intent(this@GameActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    alert2.show()
                }
                alert.show()
            }

            override fun onTick(p0: Long) {
                binding.timeText.text = "Time: ${p0 / 1000}"
            }
        }.start()
    }

    fun imageSelect(){
        val count : Int = (binding.gridLayout.childCount -1)
        for(index in 0..count){
            imageArray.add(binding.gridLayout.getChildAt(index) as ImageView)
        }
    }

    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
}