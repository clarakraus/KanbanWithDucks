import {useEffect, useState} from "react";
import axios from "axios";
import {EnumStatus, Task} from "./model";
import {KanBanKarte} from "./KanBanKarte";



export function KanBanBoard(){
    const [newTask, setNewTask] = useState("")
    const [newDescription, setNewDescription] = useState("")
    const [taskArray, setTaskArray] = useState<Array<Task>>([])

    function getStuff(){
        console.log("Hallo")
        fetch("http://localhost:8080/api/kanban", {method: "GET"})
            .then(response =>response.json())
            .then(data => setTaskArray(data))
    }

    useEffect(()=> {
        getStuff()
    }, [])

    function taskClick (){
        axios.post("http://localhost:8080/api/kanban",
            {task: newTask, description: newDescription, status: EnumStatus.OPEN})
            .then(getStuff)


    }
    function regelt(){
        return taskArray.map(t => <KanBanKarte task={t} onTaskChange={getStuff} />)
    }
    function sort (){

    }
    return(
        <div>
            <div>
                <span>To Do <input type = "text" onChange={ev => setNewTask(ev.target.value)} value={newTask}/><br/></span>
                <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
                <button onClick={taskClick}>Send</button>
            </div>
                <div className={"Stati"}>
                    {regelt()}
                </div>

        </div>
    )

}