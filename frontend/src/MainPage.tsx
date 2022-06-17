import React, { useState, useEffect } from 'react';
import {KanBanBoard} from "./KanBanBoard";
import "./Body.css"

function MainPage() {

    /*const [greeting, setGreeting] = useState('')

    useEffect(() => {
        fetch('/api/greeting', {
            method: 'GET',
            headers: {
                'Accept': 'text/plain'
            }
        })
            .then(response => response.text())
            .then(text => setGreeting(text))
            .catch(err => setGreeting('Da ist etwas schief gelaufen'));
    }, []);

     */

    return (
        <div>
            <KanBanBoard/>
        </div>
    );
}

export default MainPage;
