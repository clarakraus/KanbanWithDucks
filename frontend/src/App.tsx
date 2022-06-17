import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./MainPage";
import EditPage from "./EditPage";

export function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path= "/:taskid" element={<EditPage/>}/>
            </Routes>
        </BrowserRouter>
    )

}

