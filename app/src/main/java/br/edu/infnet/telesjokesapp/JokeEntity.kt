package br.edu.infnet.telesjokesapp

import com.google.gson.annotations.SerializedName

class JokeEntity {
    @SerializedName("joke")
    var joke: String = ""

}


//{"joke":"Por que o mundo e legal?||-Porque e uma bola, se fosse duas seria um saco\r"}