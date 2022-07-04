import {FormEvent, useState} from "react";
import {Link, NavLink, useNavigate} from "react-router-dom";
import {createAccount, loginUser} from "./KanbanService";

export default function LoginPage(){

    const [userLoginName, setUserLoginName] = useState("")
    const [userLoginPassword, setUserLoginPassword] = useState("")

    const nav = useNavigate()

    function sendUserDetails(ev:FormEvent){
        ev.preventDefault()
        loginUser(userLoginName, userLoginPassword)
            .then((loginResponse) =>localStorage.setItem("token", loginResponse.token))
            .then(() => nav("/mainpage"))
    }


    return(
        <>
            <div>
                <h4>Log In</h4>
            </div>
            <div>
                <form onSubmit={sendUserDetails}>
                    <input type="text" placeholder="username" value={userLoginName}
                           onChange={event => setUserLoginName(event.target.value)}/>
                    <input type="password" placeholder="password" value={userLoginPassword}
                           onChange={event => setUserLoginPassword(event.target.value)}/>
                    <input type="submit" value="log in" />
                </form>
                <div>
                    <Link to={"/register"}>No account yet?</Link>
                </div>
            </div>
        </>
    )

}