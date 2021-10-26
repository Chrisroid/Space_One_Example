package com.example.spaceoneexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceoneexample.api.Aircraft
import com.example.spaceoneexample.api.Api
import com.example.spaceoneexample.api.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(Repository(Api.apiService))).get(MainViewModel::class.java)
    }

    private val aircrafts = mutableListOf<Aircraft>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        getAllData()
    }

    fun getAllData(){
        Api.apiService.getAllData().enqueue(object: Callback<List<Aircraft>> {
            override fun onResponse(
                call: Call<List<Aircraft>>,
                response: Response<List<Aircraft>>
            ) {
                if(response.isSuccessful){
                    recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply{
                        myAdapter = MainAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Aircraft>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}