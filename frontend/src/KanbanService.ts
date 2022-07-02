import axios, {AxiosResponse} from "axios";
import {LoginResponse} from "./model";

export function createAccount(username: string, password: string){
        return axios.post("/api/user", {username, password})
        .then(response => response.data)
}

export function loginUser(username: string, password: string){
    return axios.post("/api/user/login", {username, password})
        .then((response:AxiosResponse<LoginResponse>)=> response.data.token)

}