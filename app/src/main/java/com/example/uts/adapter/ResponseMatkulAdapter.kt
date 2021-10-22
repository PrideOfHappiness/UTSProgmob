package com.example.uts.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.GetActivity
import com.example.uts.R
import com.example.uts.UpdateActivity
import com.example.uts.model.ResponseAddMatkul
import com.example.uts.model.ResponseItem
import com.example.uts.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseMatkulAdapter (val matkul: List<ResponseItem>?): RecyclerView.Adapter<com.example.uts.adapter.ResponseMatkulAdapter.MatkulHolder>(){
    lateinit var mContext : Context
    lateinit var adapter : ResponseMatkulAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseMatkulAdapter.MatkulHolder {
        return MatkulHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_matkul, parent, false))
    }
        override fun onBindViewHolder(holder: ResponseMatkulAdapter.MatkulHolder, position: Int) {
            holder.bindMatkul(matkul?.get(position))
            var popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
            popupMenu.menu.add(Menu.NONE, 0, 0, "Edit")
            popupMenu.menu.add(Menu.NONE, 1, 1, "Delete")
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val id = menuItem.itemId
                mContext = holder.itemView.context
                if (id==0){
                    var bundle = Bundle()
                    var idTmp = matkul?.get(position)?.id.toString()
                    var nama = matkul?.get(position)?.nama.toString()
                    bundle.putString("idMatkul", idTmp)
                    bundle.putString("namaMatkul", nama)
                    var intent = Intent(mContext, UpdateActivity::class.java)
                    intent.putExtras(bundle)
                    mContext.startActivity(intent)
                }
                else if(id==1) {
                    var idTmp = matkul?.get(position)?.id.toString()
                    NetworkConfig().getService()
                        .deleteMatkul(idTmp.toInt())
                        .enqueue(object : Callback<ResponseAddMatkul?> {
                            override fun onFailure(call : Call<ResponseAddMatkul?>, t: Throwable) {
                                Toast.makeText(holder.itemView.context, t.localizedMessage, Toast.LENGTH_SHORT)
                            }
                            override fun onResponse ( call: Call<ResponseAddMatkul?>, response: Response<ResponseAddMatkul?> ){
                                Toast.makeText(holder.itemView.context, "Berhasil Hapus Data", Toast.LENGTH_SHORT)
                                (mContext as GetActivity).finish()
                                var intent = Intent(mContext, GetActivity::class.java)
                                mContext.startActivity(intent)
                            }
                        })
                }
                false
            }
            holder.itemView.setOnClickListener(View.OnClickListener { view ->
                popupMenu.show()
            })
        }

        override fun getItemCount(): Int {
            return matkul?.size ?: 0
        }

        class MatkulHolder(view: View) : RecyclerView.ViewHolder(view){
            lateinit var kodeMatkul : TextView
            lateinit var namaMatkul : TextView
            lateinit var hari : TextView
            lateinit var sesi : TextView
            lateinit var sks : TextView

            fun bindMatkul(matkul : ResponseItem?){
                itemView.apply {
                    kodeMatkul = findViewById(R.id.txtKode)
                    namaMatkul = findViewById(R.id.txtKodeNama)
                    hari = findViewById(R.id.txtHari)
                    sesi = findViewById(R.id.txtSesi)
                    sks = findViewById(R.id.txtSks)

                    kodeMatkul.text = matkul?.kode
                    namaMatkul.text = matkul?.nama
                    hari.text = matkul?.hari
                    sesi.text = matkul?.sesi
                    sks.text = matkul?.sks
                }

            }
    }
}