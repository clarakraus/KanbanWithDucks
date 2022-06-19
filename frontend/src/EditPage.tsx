import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Task} from "./model";

export default function EditPage() {

    const [task, setTask] = useState(localStorage.getItem("taskinput")?? "");
    const [newDescription, setNewDescription] = useState(localStorage.getItem("descriptioninput")?? "");
    const [errorMessage, setErrorMessage] = useState("")
    const [item, setItem] = useState<Task>()
    const params = useParams()
    const nav = useNavigate()

    useEffect(() =>{
        localStorage.setItem("taskinput", task)
        localStorage.setItem("descriptioninput", newDescription)
    }, [task || newDescription]);

    function getTaskById(id: string){
       return axios.get(`http://localhost:8080/api/kanban/${id}`)
           .then(response => response.data)
    }

    useEffect(()=>{
        getTaskById(params.taskid || "undefined")
            .then(data => {
                setTask(data.task)
                setNewDescription(data.description)
                setItem(data)
                setErrorMessage("")
                }
            )

    }, [params.taskid])

    const getUpdateKanban = (item: Task) => {
        return axios.put(`http://localhost:8080/api/kanbangit`, item)
            .then(response => response.data)
            .catch(() => setErrorMessage("Ooopsies, something went wrong. Guess you have to stick to your task as it is 🫠"))
    }

    const updateKanban = () =>{
      item &&  getUpdateKanban({id:item.id, task:task, description:newDescription, status:item?.status})
          .then(errorMessage => (errorMessage === "")? nav("/"):console.log(errorMessage)
    )
    }

    return (
        <div className={"editPage"}>

                <span>To Do <input type = "text" onChange={ev => setTask(ev.target.value)} value={task}/><br/></span>
                <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
                <button type= "submit" onClick={updateKanban}>save changes</button>
            {errorMessage &&
                <div className="error">
                    {errorMessage}
                </div>
            }
        </div>
    )
}