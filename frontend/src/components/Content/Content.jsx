import React from 'react';
import styles from './style.module.css';
import {Route, Routes} from "react-router-dom";
import Sidebar from "../Sidebar/Sidebar";
import Departments from "../Departments/Departments";

const Content = ({ authentication }) => {
    return (
        <div className={styles.content}>
            <Sidebar authentication={authentication}/>
            <Routes>
                <Route path="*" element={<React.StrictMode><Departments authentication={authentication}/></React.StrictMode>}/>
            </Routes>
        </div>
    );
};

export default Content;