package com.example.boockshelf

import com.google.gson.annotations.SerializedName


data class Epub (

  @SerializedName("isAvailable"  ) var isAvailable  : Boolean? = null,
  @SerializedName("acsTokenLink" ) var acsTokenLink : String?  = null

)