import axios, {AxiosResponse} from "axios";
import {LoginResponse} from "./model";

export function createAccount(username: string, password: string){
        return axios.post("/api/user", {username, password})
}

export function loginUser(username: string, password: string){
    return axios.post("/api/login", {username, password})
        .then((response:AxiosResponse<LoginResponse>)=> response.data)
}