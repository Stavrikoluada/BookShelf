package com.example.boockshelf

import com.google.gson.annotations.SerializedName


data class BookShelf(

  @SerializedName("kind")
  var kind: String?,

  @SerializedName("totalItems")
  var totalItems: Int?,

  @SerializedName("items")
  var items: ArrayList<Items>
) {


  data class Items(

    @SerializedName("id")
    var id: String?,

    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo?
  ) {


    data class VolumeInfo(

      @SerializedName("title")
      var title: String?,

      @SerializedName("authors")
      var authors: ArrayList<String>?,

      @SerializedName("description")
      var description: String?,

      @SerializedName("pageCount")
      var pageCount: Int?,

      @SerializedName("printType")
      var printType: String?,

      @SerializedName("categories")
      var categories: ArrayList<String>?,

      @SerializedName("maturityRating")
      var maturityRating: String?,

      @SerializedName("imageLinks")
      var imageLinks: ImageLinks?,

      @SerializedName("language")
      var language: String?,

      @SerializedName("previewLink")
      var previewLink: String?,
    ) {


      data class ImageLinks(
        @SerializedName("thumbnail")
        var thumbnail: String?
      )
    }
  }
}

