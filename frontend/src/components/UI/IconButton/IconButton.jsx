import React from 'react';
import styles from './style.module.css'

const IconButton = ({ onClick, src }) => {
    return (
        <button className={styles.button} onClick={onClick}>
            <img src={src} alt={'IconButton'}/>
        </button>
    );
};

export default IconButton;