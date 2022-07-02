import {FormEvent, useState} from "react";
import {createAccount} from "./KanbanService";
import {useNavigate} from "react-router-dom";

export default function RegisterPage(){

    const [userName, setUserName] = useState("")
    const [password, setPassword] = useState("")

    const nav = useNavigate()

    function sendUserDetails(ev:FormEvent){
        ev.preventDefault()
        createAccount(userName, password)
  //          .then(nav("/api/login"))

    }


    return(
        <>
            <div>
                <h4>create account</h4>
            </div>
            <div>
                <form onSubmit={sendUserDetails}>
                    <input type="text" placeholder="choose a username" value={userName}
                           onChange={event => setUserName(event.target.value)}/>
                    <input type="password" placeholder="choose a safe password" value={password}
                           onChange={event => setPassword(event.target.value)}/>
                    <input type="submit" value="create account" />
                </form>
            </div>
        </>
    )

}