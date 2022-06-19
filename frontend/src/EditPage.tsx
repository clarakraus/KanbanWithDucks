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
        return axios.put(`http://localhost:8080/api/kanbanjk`, item)
            .then(response => response.data)
            .catch(() => setErrorMessage("Ooopsies, something went wrong. Guess you have to stick to your task as it is 🫠"))
    }

    const updateKanban = () =>{
      item &&  getUpdateKanban({id:item.id, task:task, description:newDescription, status:item?.status})
          .then(errorMessage => (errorMessage === "")? nav("/"):console.log(errorMessage)
    )
    }
    const bringMeBack = () =>{
        nav("/")
    }

    return (
        <div className={"editPage"}>

                <span>To Do <input type = "text" onChange={ev => setTask(ev.target.value)} value={task}/><br/></span>
                <span>Description <input type = "text" onChange={ev => setNewDescription(ev.target.value)} value={newDescription}/></span>
                <button type= "submit" onClick={updateKanban}>save changes</button>
            {errorMessage &&
                <div className="error">
                    {errorMessage}
                    <img src="data:image/jpeg;base64,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" alt="Ducky says uck!" width= "200"/>
                    <span> <button onClick= {bringMeBack}>bring me back to the main page</button></span>
                </div>
            }
        </div>
    )
}