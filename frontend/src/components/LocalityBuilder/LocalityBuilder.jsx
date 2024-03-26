import React, {useState} from 'react';
import styles from './style.module.css'
import IconButton from "../UI/IconButton/IconButton";
import GetLocalityForm from "../GetLocalityForm/GetLocalityForm";

const LocalityBuilder = ({ items }) => {
    const [chosenLocalities, setChosenLocalities] = useState([]);
    const [isGetLocalityFormHidden, setIsGetLocalityFormHidden] = useState(true);

    function add() {
        setIsGetLocalityFormHidden(false);
    }

    function appendLocality(locality) {
        setChosenLocalities([...chosenLocalities, locality]);
    }

    return (
        <div className={styles.builder}>
            <GetLocalityForm level={chosenLocalities.length + 1} appendLocality={appendLocality} setIsHidden={setIsGetLocalityFormHidden} isHidden={isGetLocalityFormHidden}/>
            {chosenLocalities.map(locality => {
                return <p className={styles.builderLabel}> {locality.name} </p>
            })}
            <IconButton src={'/assets/icons/add.png'} onClick={add}/>
        </div>
    );
};

export default LocalityBuilder;