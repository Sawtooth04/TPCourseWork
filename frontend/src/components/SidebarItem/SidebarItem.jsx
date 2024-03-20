import React from 'react';
import styles from './style.module.css'
import {useNavigate} from "react-router-dom";

const SidebarItem = ({ link, text }) => {
    const navigate = useNavigate();

    function onClick() {
        navigate(link);
    }

    return (
        <div className={styles.sidebarItem} onClick={onClick}>
            <p className={styles.sidebarItemText}> {text} </p>
        </div>
    );
};

export default SidebarItem;