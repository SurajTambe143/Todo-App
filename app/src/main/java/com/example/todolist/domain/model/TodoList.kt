package com.example.todolist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo")
data class TodoList(
    val title: String?="",
    val description:String?=""
): Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int =0

//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString()
//    ) {
//        id = parcel.readInt()
//    }

//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(p0: Parcel, p1: Int) {
//        p0.writeString(title)
//        p0.writeString(description)
//    }

//    companion object CREATOR : Parcelable.Creator<TodoList> {
//        override fun createFromParcel(parcel: Parcel): TodoList {
//            return TodoList(parcel)
//        }
//
//        override fun newArray(size: Int): Array<TodoList?> {
//            return arrayOfNulls(size)
//        }
//    }
}
