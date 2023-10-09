import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kz.just_code.todolist.adapter.ToDoListAdapter
import kz.just_code.todolist.databinding.FragmentMainBinding
import kz.just_code.todolist.model.ToDoItem

class MainFragment: Fragment() {
    private lateinit var binding:FragmentMainBinding
    private var toDoListAdapter:ToDoListAdapter? = null



    private var items = listOf(
        ToDoItem(
            id = 0,
            title = "Do lab work",
            description = "Be ready for lab work",
            status = false
        ),
        ToDoItem(
            id = 1,
            title = "Prepare presentation",
            description = "Prepare for presentation",
            status = false
        ),
        ToDoItem(
            id = 2,
            title = "FBIS Assignment #2",
            description = "Do second assignment",
            status = false
        )
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toDoListAdapter = ToDoListAdapter()
        binding.toDoList.adapter = toDoListAdapter
        binding.toDoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        toDoListAdapter?.onToDoItemClicked = { toDoItem ->
            updateToDoItem(toDoItem)
        }
        submitList(items)

    }
    private fun updateToDoItem(toDoItem:ToDoItem){
        val newList = items.map{
            if(it.id == toDoItem.id){
                it.copy(
                    status = if(!toDoItem.status) {
                        true.also { toDoItem.status = it }
                    }
                    else {
                        false.also { toDoItem.status = it }
                    }
                )
            }
            else{
                it
            }
        }
        items = newList
        submitList(items)
    }
    private fun submitList(list:List<ToDoItem>){
        toDoListAdapter?.submitList(list)
    }
}