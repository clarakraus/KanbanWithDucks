import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./MainPage";
import EditPage from "./EditPage";
import RegisterPage from "./RegisterPage";
import LoginPage from "./LoginPage";

export function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
                <Route path="/mainpage" element={<MainPage/>}/>
                <Route path= "/:taskid" element={<EditPage/>}/>
            </Routes>
        </BrowserRouter>
    )

}

