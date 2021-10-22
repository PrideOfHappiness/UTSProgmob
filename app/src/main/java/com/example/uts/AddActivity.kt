package com.example.uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uts.model.ResponseAddMatkul
import com.example.uts.model.ResponseItem
import com.example.uts.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var edKode : EditText
    lateinit var edNama : EditText
    lateinit var edHari : EditText
    lateinit var edSesi : EditText
    lateinit var edSKS : EditText
    lateinit var edNimProgmob : EditText
    lateinit var btnTambah : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        edKode = findViewById(R.id.edKode)
        edNama = findViewById(R.id.edNama)
        edHari = findViewById(R.id.edHari)
        edSesi = findViewById(R.id.edSesi)
        edSKS = findViewById(R.id.edSKS)
        edNimProgmob = findViewById(R.id.edNimProgmob)
        btnTambah = findViewById(R.id.btnSimpan)

        btnTambah.setOnClickListener(View.OnClickListener { view ->
            var matkul = ResponseItem()
            matkul.kode = edKode.text.toString()
            matkul.nama = edNama.text.toString()
            matkul.hari = edHari.text.toString()
            matkul.sesi = edSesi.text.toString()
            matkul.sks = edSKS.text.toString()
            matkul.nim_progmob = edNimProgmob.text.toString()
            matkul.id = null

            NetworkConfig().getService()
                .addMatkul(matkul)
                .enqueue(object : Callback<ResponseAddMatkul?>{
                    override fun onFailure(call: Call<ResponseAddMatkul?>, t: Throwable) {
                        Toast.makeText(this@AddActivity,  t.localizedMessage, Toast.LENGTH_SHORT)
                    }

                    override fun onResponse(
                        call: Call<ResponseAddMatkul?>,
                        response: Response<ResponseAddMatkul?>
                    ) {
                        Toast.makeText(this@AddActivity, "Berhasil tambah Data", Toast.LENGTH_SHORT)
                    }
                })
        })
    }
}