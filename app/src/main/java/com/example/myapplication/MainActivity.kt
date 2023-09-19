package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()

        binding.btnLoadUser.setOnClickListener{
            fetchData()
        }
    }

    private fun initializeViews(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun createApiService():ApiService{
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    private fun fetchData(){

        val call = createApiService().getUser()

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful){
                    val userResponse = response.body()
                    if (userResponse != null){
                        Glide.with(this@MainActivity).load(userResponse.results[0].picture.large).into(binding.ivUser)
                        binding.tvEmailAdressResponse.text = userResponse.results[0].email
                        binding.tvNameResponse.text = userResponse.results[0].name.first
                        binding.tvtelephoneResponse.text = userResponse.results[0].phone
                        binding.tvBirthdayResponse.text = userResponse.results[0].dob.date
                        binding.tvPasswordResponse.text = userResponse.results[0].login.password
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

        })
    }
}