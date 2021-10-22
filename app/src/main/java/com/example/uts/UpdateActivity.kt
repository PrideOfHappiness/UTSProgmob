package com.example.uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.uts.model.ResponseAddMatkul
import com.example.uts.model.ResponseItem
import com.example.uts.network.NetworkConfig
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var edId : TextView
    lateinit var edKode : EditText
    lateinit var edNama : EditText
    lateinit var edHari : EditText
    lateinit var edSesi : EditText
    lateinit var edSKS : EditText
    lateinit var edNimProgmob : EditText
    lateinit var btnEdit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        edId = findViewById(R.id.edId)
        if(intent.extras != null){
            var bundle : Bundle ?= intent.extras
            var strTes : String = bundle?.getString("id").toString()
            edId.setText(strTes)
        }

        edKode = findViewById(R.id.edKode)
        edNama = findViewById(R.id.edNama)
        edHari = findViewById(R.id.edHari)
        edSesi = findViewById(R.id.edSesi)
        edSKS = findViewById(R.id.edSKS)
        edNimProgmob = findViewById(R.id.edNimProgmob)
        btnEdit = findViewById(R.id.btnEdit)

        btnEdit.setOnClickListener(View.OnClickListener { view ->
            var matkul = ResponseItem()
            matkul.id = edId.text.toString()
            matkul.kode = edKode.text.toString()
            matkul.nama = edNama.text.toString()
            matkul.hari = edHari.text.toString()
            matkul.sesi = edSesi.text.toString()
            matkul.sks = edSKS.text.toString()
            matkul.nim_progmob = edNimProgmob.text.toString()

            NetworkConfig().getService()
                .updateMatkul(edId.toString().toInt(), matkul)
                .enqueue(object : Callback<ResponseAddMatkul> {
                    override fun onFailure(call: retrofit2.Call<ResponseAddMatkul>, t: Throwable) {
                        Toast.makeText(this@UpdateActivity,  t.localizedMessage, Toast.LENGTH_SHORT)
                    }

                    override fun onResponse(
                        call: retrofit2.Call<ResponseAddMatkul>,
                        response: Response<ResponseAddMatkul>
                    ) {
                        Toast.makeText(this@UpdateActivity, "Berhasil Update Data", Toast.LENGTH_SHORT)
                    }
                })
        })

    }
}