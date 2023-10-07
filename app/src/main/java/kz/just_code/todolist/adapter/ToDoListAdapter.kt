package kz.just_code.todolist.adapter

import MainFragment
import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.just_code.todolist.R
import kz.just_code.todolist.databinding.ItemToDoBinding
import kz.just_code.todolist.diffutil.ToDoListDiffUtilCallback
import kz.just_code.todolist.fragments.DetailsFragment
import kz.just_code.todolist.model.ToDoItem

class ToDoListAdapter(
    private var toDoItems:List<ToDoItem>,
    private val onItemClicked: (ToDoItem) -> Unit
): ListAdapter<ToDoItem, ToDoListAdapter.ViewHolder>(ToDoListDiffUtilCallback()) {

    var onToDoItemClicked: ((ToDoItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemToDoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ToDoListAdapter.ViewHolder).bind(toDoItems[position])
    }

    fun setItems(toDoItems: List<ToDoItem>){
        this.toDoItems = toDoItems
    }

    override fun getItemCount(): Int {
        return toDoItems.size
    }


    inner class ViewHolder(
        private val binding:ItemToDoBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(toDoItem: ToDoItem){
            binding.todoTitle.text = toDoItem.title
            binding.todoDescription.text = toDoItem.description
            if(!toDoItem.status){
                binding.todoTitle.apply {
                        paintFlags = 0
                    }
                    binding.checkbox.setImageResource(R.drawable.baseline_check_box_outline_blank_24)
            }
            else{
                    binding.todoTitle.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }
                    binding.checkbox.setImageResource(R.drawable.baseline_check_box_24)
            }
            binding.root.setOnClickListener{
                onToDoItemClicked?.invoke(toDoItem)
            }



//            binding.checkbox.setOnClickListener {
//                if(!toDoItem.status){
//                    toDoItem.status = true
//                    binding.todoTitle.apply {
//                        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//                    }
//                    binding.checkbox.setImageResource(R.drawable.baseline_check_box_24)
//                }
//                else{
//                    toDoItem.status = false
//                    binding.todoTitle.apply {
//                        paintFlags = 0
//                    }
//                    binding.checkbox.setImageResource(R.drawable.baseline_check_box_outline_blank_24)
//                }
//            }
        }
    }

}