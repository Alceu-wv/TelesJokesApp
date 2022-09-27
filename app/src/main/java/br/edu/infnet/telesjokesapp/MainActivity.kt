package br.edu.infnet.telesjokesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var lblJoke: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lblJoke = this.findViewById(R.id.lblJoke)

        val service = RetrofitClient.createGetJokeService()
        val call = service.getJoke()

        call.enqueue(object : Callback<JokeEntity>{
            override fun onResponse(call: Call<JokeEntity>, r: Response<JokeEntity>) {
                val joke = r.body()!!.joke
                lblJoke.setText(joke)
            }
            override fun onFailure(call: Call<JokeEntity>, t: Throwable) {
                Toast.makeText(applicationContext, "Desculpa, faltou graça para fazer uma nova piada", Toast.LENGTH_SHORT)
            }
        })
    }
}



//{"joke":"Por que o mundo e legal?||-Porque e uma bola, se fosse duas seria um saco\r"}