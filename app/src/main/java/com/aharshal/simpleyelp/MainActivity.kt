package com.aharshal.simpleyelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aharshal.simpleyelp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "HdoTKYLJJqqHS9u5BCvSvj89-7 HvajbrQaf7N9l0o-rZMoesUBP6k1umUFW9j1nyGnwQT4THHIZNtxn4KCedISYV_SSugfX4k2_lI-IRtF344GBl84WNYWyDwJuUYXYx"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private lateinit var tvName: TextView
    private lateinit var rvRestaurants: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        //binding = ActivityMainBinding.inflate(layoutInflater)
       // val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //tvName = findViewById(R.id.tvName)
        rvRestaurants = findViewById(R.id.rvRestaurants)
        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantAdapter(this, restaurants)
        rvRestaurants.adapter = adapter;
        rvRestaurants.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build   ()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants( "Bearer $API_KEY","Avocado Toast", "New York").enqueue(object: Callback<YelpSearchResult> {
            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")


            }

            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>
            ) {
                val body = response.body()
                if (body == null){
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

        })


    }
}