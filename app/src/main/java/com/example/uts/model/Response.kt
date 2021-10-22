package com.example.uts.model

import com.google.gson.annotations.SerializedName

data class ResponseMatkul(

	@field:SerializedName("data")
	val data: List<ResponseItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResponseItem(

	@field:SerializedName("hari")
	var hari: String? = null,

	@field:SerializedName("nama")
	var nama: String? = null,

	@field:SerializedName("kode")
    var kode: String? = null,

	@field:SerializedName("sks")
	var sks: String? = null,

	@field:SerializedName("id")
	var id: String? = null,

	@field:SerializedName("sesi")
	var sesi: String? = null,

	@field:SerializedName("nim_progmob")
	var nim_progmob: String? = null
)
