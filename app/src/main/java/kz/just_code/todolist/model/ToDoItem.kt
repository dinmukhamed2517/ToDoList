package kz.just_code.todolist.model

data class ToDoItem(
    var id:Int,
    var title: String,
    var description:String,
    var status:Boolean,
)
