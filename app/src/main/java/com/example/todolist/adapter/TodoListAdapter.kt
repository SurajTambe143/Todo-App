package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.domain.model.TodoList

class TodoListAdapter(val remove: (TodoList) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private  var _list: List<TodoList> = emptyList()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title =itemView.findViewById<TextView>(R.id.cv_title)
        val description =itemView.findViewById<TextView>(R.id.cv_description)
        val delete=itemView.findViewById<ImageView>(R.id.delete)
        val cv=itemView.findViewById<CardView>(R.id.card_view_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.todo_list_cv,parent,false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    fun updateList(list: List<TodoList>){
        _list=list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.title.text= _list[position].title
        holder.description.text=_list[position].description
        holder.delete.setOnClickListener {
            remove.invoke(_list[position])
        }
        holder.cv.setOnClickListener {
            remove.invoke(_list[position])
        }
    }
}