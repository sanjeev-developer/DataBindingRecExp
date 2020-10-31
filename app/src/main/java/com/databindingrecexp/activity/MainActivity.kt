package com.databindingrecexp.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.databindingexp.model.ApiResponse
import com.databindingrecexp.R
import com.databindingrecexp.adapter.DataAdapter
import com.leaderboard.repo.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hitapi()
    }

    private fun hitapi() {
        pb_load.visibility = View.VISIBLE

        val call: Call<ApiResponse>? = ApiClient.getClient.fetchdata()
        call!!.enqueue(object : Callback<ApiResponse?> {
            @RequiresApi(api = Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {

                if (response.isSuccessful()) {
                      var dataget = response.body()?.jsondata?.let { ApiResponse(it) }
                    //  binding?.data = response.body()?.jsondata?.let { ApiResponse(it) }
                    //  binding?.data = response.body()

                    rv_data.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = response.body()?.let { DataAdapter(it) }
                    }
                    if (dataget != null) {
                        Toast.makeText(this@MainActivity, "data received" + dataget.jsondata!![0]?.name.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                call.cancel()
            }
        })
    }}


