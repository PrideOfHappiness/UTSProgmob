package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.adapter.ResponseMatkulAdapter
import com.example.uts.model.ResponseItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.uts.model.ResponseMatkul
import com.example.uts.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetActivity : AppCompatActivity() {
    lateinit var rvMatkul: RecyclerView
    lateinit var btnTambah: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        rvMatkul = findViewById(R.id.rv_get_matkul)
        btnTambah = findViewById(R.id.btnTambah)

        NetworkConfig().getService()
            .getMatkul("72190301")
            .enqueue(object : Callback<List<ResponseMatkul>> {
                override fun onFailure(call: Call<List<ResponseMatkul>>, t: Throwable) {
                    Toast.makeText(this@GetActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<List<ResponseMatkul>>,
                    response: Response<List<ResponseMatkul>>
                ) {
                    rvMatkul.apply {
                        //var data = response.body()
                        layoutManager = LinearLayoutManager(this@GetActivity)
                        adapter = ResponseMatkulAdapter(response.body() as List<ResponseItem>?)
                    }
                }
            })

        btnTambah.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@GetActivity, AddActivity::class.java)
            startActivity(intent)
        })
    }
}

