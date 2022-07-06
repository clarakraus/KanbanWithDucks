import {useEffect, useState} from "react";
import axios from "axios";
import {EnumStatus, Task} from "./model";
import {KanBanKarte} from "./KanBanKarte";
import "./KanBanBoard.css";



export function KanBanBoard(){
    const [newTask, setNewTask] = useState("")
    const [newDescription, setNewDescription] = useState("")
    const [taskArray, setTaskArray] = useState<Array<Task>>([])
    const taskComponents = taskArray.map(t => <KanBanKarte key= {t.id} task={t} onTaskChange={getStuff}/>)

    function getStuff(){
       return axios.get("/api/kanban", {
           headers: {
               Authorization: `Bearer ${localStorage.getItem('token')}`
           }
       })
            .then(response => response.data)
            .then(data => setTaskArray(data))
    }

    useEffect(()=> {
        getStuff()
    }, [])

    function taskClick (){
        axios.post("/api/kanban",
            {task: newTask, description: newDescription, status: EnumStatus.OPEN},
            {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('token')}`
                }
            })
            .then(getStuff)
            .then(() => setNewTask(""))
            .then(() => setNewDescription(""))
    }


    return(
        <div>
            <div className="BoardOrga">

                <div><h1>Kanban Board</h1></div>
                <div><img src={"https://c.tenor.com/kn-kEb3yEJ0AAAAC/ducks-funny.gif"} alt="duck" width={200}/></div>
                <div>
                    <span>To Do <input type = "text" onChange={ev => setNewTask(ev.target.value)} value={newTask}/><br/></span>
                    <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
                    <button onClick={taskClick}>Send</button>
                </div>
            </div>
            <div className= "FunWithColumns">
                <span> <h2>Open</h2> {taskComponents.filter(t => t.props.task.status === EnumStatus.OPEN)} </span>
                <span> <h2>In Progress</h2> {taskComponents.filter(t => t.props.task.status === EnumStatus.IN_PROGRESS)} </span>
                <span> <h2>Done</h2> {taskComponents.filter(t => t.props.task.status === EnumStatus.DONE)} </span>

            </div>

        </div>
    )

}