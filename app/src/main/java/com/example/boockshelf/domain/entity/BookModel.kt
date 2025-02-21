package com.example.boockshelf.domain.entity

import android.os.Parcel
import android.os.Parcelable

data class BookModel(
    val title: String?,
    val previewLink: String?,
    val imageLink: String?,
    val pageCount: Int?,
    val description: String?,
    val id: String?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        previewLink = parcel.readString(),
        imageLink = parcel.readString(),
        pageCount = parcel.readValue(Int::class.java.classLoader) as? Int,
        description = parcel.readString(),
        id = parcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(previewLink)
        parcel.writeString(imageLink)
        parcel.writeValue(pageCount)
        parcel.writeString(description)
        parcel.writeString(id)
    }

    companion object CREATOR : Parcelable.Creator<BookModel> {
        override fun createFromParcel(parcel: Parcel): BookModel {
            return BookModel(parcel)
        }

        override fun newArray(size: Int): Array<out BookModel?> {
            return arrayOfNulls(size)
        }
    }
}
