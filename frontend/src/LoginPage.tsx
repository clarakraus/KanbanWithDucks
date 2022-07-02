import {FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import {createAccount, loginUser} from "./KanbanService";

export default function LoginPage(){

    const [userLoginName, setUserLoginName] = useState("")
    const [userLoginPassword, setUserLoginPassword] = useState("")

    const nav = useNavigate()

    function sendUserDetails(ev:FormEvent){
        ev.preventDefault()
        loginUser(userLoginName, userLoginPassword)
            .then(localStorage.setItem("token", response.data.token))
            .then(nav("/"))


    }


    return(
        <>
            <div>
                <h4>create account</h4>
            </div>
            <div>
                <form onSubmit={sendUserDetails}>
                    <input type="text" placeholder="choose a username" value={userLoginName}
                           onChange={event => setUserLoginName(event.target.value)}/>
                    <input type="password" placeholder="choose a safe password" value={userLoginPassword}
                           onChange={event => setUserLoginPassword(event.target.value)}/>
                    <input type="submit" value="create account" />
                </form>
            </div>
        </>
    )

}