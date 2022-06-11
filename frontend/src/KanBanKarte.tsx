import {Task} from "./model";
import axios from "axios";
import {useEffect, useState} from "react";

interface KanBanKartenProps{
    task: Task;
    onTaskChange: () => void;

}

const url = "http://localhost:8080/api/kanban"

export function KanBanKarte(props: KanBanKartenProps) {

    const[editMode, setEditMode] = useState(false)
    const [newTask, setNewTask] = useState(props.task.task)
    const [newDescription, setNewDescription] = useState(props.task.description)

    useEffect(() =>{
        edit()
    }, [newTask, newDescription])

    function setNext() {
        axios.put(`${url}/next`, props.task)
            .then(props.onTaskChange)

    }
    function setPrevious() {
        axios.put(`${url}/prev`, props.task)
            .then(props.onTaskChange)
    }

    const deleteTask = () => {
        axios.delete(`${url}/${props.task.id}`)
            .then(props.onTaskChange)
    }

    function edit(){
        axios.put(url, {task: newTask, description: newDescription, status: props.task.status, id: props.task.id})
            .then( () => props.onTaskChange())
            .catch(error => console.log(error))

    }

    return (
        <div>
            <div>
                <span>Task</span>{props.task.task}
                <span>Description</span>{props.task.description}
                <span>Status</span>{props.task.status}
            </div>
            <div>
                <button onClick={setPrevious}>previous</button>
                <button onClick={setNext}>next</button>
                <button onClick={deleteTask}>delete</button>
                <button onClick={()=> setEditMode(editMode =>!editMode)}>edit</button>
            </div>
            {editMode && <div>
                <span>To Do <input type = "text" onChange={ev => setNewTask(ev.target.value)} value={newTask}/><br/></span>
                <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
            </div>}
        </div>
    )
}



