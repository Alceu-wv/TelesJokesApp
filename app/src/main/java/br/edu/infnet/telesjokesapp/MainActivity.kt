package br.edu.infnet.telesjokesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val FIRST_IMAGE_ORIENTATION = "right"
    val SECOND_IMAGE_ORIENTATION = "left"
    var imageOrientation: String = FIRST_IMAGE_ORIENTATION

    private lateinit var button: Button
    private lateinit var lblJoke: TextView
    private lateinit var lblJoke2: TextView
    private lateinit var imgAlceu: ImageView
    private lateinit var imgTeles: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = this.findViewById(R.id.button)
        lblJoke = this.findViewById(R.id.lblJoke)
        lblJoke2 = this.findViewById(R.id.lblJoke2)
        imgAlceu = this.findViewById(R.id.imgAlceu)
        imgTeles = this.findViewById(R.id.imgTeles)

        button.setOnClickListener {
            makeNewJoke()
            flipImages()
        }
    }

    private fun flipImages() {
        if (imageOrientation == FIRST_IMAGE_ORIENTATION) {
            imgAlceu.setImageResource(R.drawable.alceu_joking_left)
            imgTeles.setImageResource(R.drawable.teles_joking_rigth)
            imageOrientation = SECOND_IMAGE_ORIENTATION
        } else if (imageOrientation == SECOND_IMAGE_ORIENTATION) {
            imgAlceu.setImageResource(R.drawable.alceu_joking_right)
            imgTeles.setImageResource(R.drawable.teles_joking_left)
            imageOrientation = FIRST_IMAGE_ORIENTATION
        }
    }

    private fun makeNewJoke() {
        val call: Call<JokeEntity> = RetrofitClient.createGetJokeService().getJoke()
        call.enqueue(object : Callback<JokeEntity>{
            override fun onResponse(call: Call<JokeEntity>, r: Response<JokeEntity>) {
                val joke: List<String> = r.body()!!.joke.split("||")
                mountJoke(joke)
            }
            override fun onFailure(call: Call<JokeEntity>, t: Throwable) {
                funnyToast()
            }
        })
    }

    private fun mountJoke(joke: List<String>) {
        if (joke.size == 2) {
            lblJoke.text = joke[0]
            lblJoke2.text = joke[1]
        } else {
            funnyToast()
        }
    }

    private fun funnyToast() {
        Toast.makeText(applicationContext, "Desculpa, faltou graça para fazer uma nova piada", Toast.LENGTH_SHORT)
    }
}

//{"joke":"Por que o mundo e legal?||-Porque e uma bola, se fosse duas seria um saco\r"}