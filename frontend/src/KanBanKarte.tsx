import {EnumStatus, Task} from "./model";
import axios from "axios";
import {useEffect, useState} from "react";
import "./KanBanKarte.css"

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
            <div className= "TaskCard">
                <span>Task: {props.task.task}</span>
                <span>Description: {props.task.description} </span>
                <span>Status: {props.task.status} </span>
            </div>
            <div className="Buttons">
                <button onClick={()=> setEditMode(editMode =>!editMode)}>edit</button>
                {props.task.status === EnumStatus.OPEN && <span>
                        <button onClick={deleteTask}>delete</button>
                        <button onClick={setNext}>next</button> </span>
                    }
                {props.task.status === EnumStatus.IN_PROGRESS &&  <span>
                    <button onClick={setPrevious}>previous</button>
                    <button onClick={setNext}>next</button> </span>
                }

                {props.task.status === EnumStatus.DONE && <span>
                        <button onClick={setPrevious}>previous</button>
                    <button onClick={deleteTask}>delete</button> </span>}

            </div>
            {editMode && <div>
                <span>To Do <input type = "text" onChange={ev => setNewTask(ev.target.value)} value={newTask}/><br/></span>
                <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
            </div>}
        </div>
    )
}



