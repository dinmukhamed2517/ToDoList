package kz.just_code.todolist.diffutil

import androidx.recyclerview.widget.DiffUtil
import kz.just_code.todolist.model.ToDoItem

class ToDoListDiffUtilCallback: DiffUtil.ItemCallback<ToDoItem>() {
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem == newItem
    }

}