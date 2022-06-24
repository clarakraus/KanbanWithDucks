import {EnumStatus, Task} from "./model";
import axios from "axios";
import {useEffect, useState} from "react";
import "./KanBanKarte.css"
import {NavLink} from "react-router-dom";

interface KanBanKartenProps{
    task: Task;
    onTaskChange: () => void;

}

const url = "/api/kanban"

export function KanBanKarte(props: KanBanKartenProps) {


    const [duckInProgress, setDuckInProgress] = useState(false)

    function setNext() {
        axios.put(`${url}/next`, props.task)
            .then(props.onTaskChange)
    }

    useEffect(() =>{
        if(props.task.status ===EnumStatus.IN_PROGRESS){
            setDuckInProgress(true)
          setTimeout(()=> setDuckInProgress(false), 6000)
        }
    }, [props.task.status])



    function setPrevious() {
        axios.put(`${url}/prev`, props.task)
            .then(props.onTaskChange)
    }

    const deleteTask = () => {
        axios.delete(`${url}/${props.task.id}`)
            .then(props.onTaskChange)
    }

    return (
        <div>
            <div className= "TaskCard">
                <span>Task: {props.task.task}</span>
                <span>Description: {props.task.description} </span>
                <span>Status: {props.task.status} </span>
            </div>
            <div className="Buttons">
                <NavLink to= {`/${props.task.id}`}>
                    <button>edit</button>
                </NavLink>
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
            {duckInProgress &&
                <div>
                    <img src="https://i.gifer.com/XOsX.gif" alt = "funny duck gif"/>
                </div>}
        </div>
    )
}



