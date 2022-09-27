package br.edu.infnet.telesjokesapp

import retrofit2.Call
import retrofit2.http.GET

//https://teles-jokes.azurewebsites.net/api/GetJoke?code=7skvGJHPnh6jOiZhwNKV0dL0awj9qTorLElJq596sKVHmrmgFJ6u4w==

interface JokeService {

    @GET("GetJoke?code=7skvGJHPnh6jOiZhwNKV0dL0awj9qTorLElJq596sKVHmrmgFJ6u4w==")
    fun getJoke(): Call<JokeEntity>

}