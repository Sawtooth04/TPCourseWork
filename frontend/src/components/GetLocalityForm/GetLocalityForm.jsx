import React, {useEffect, useState} from 'react';
import CentralServerLinksProvider from "../../utils/CentralServerLinksProvider";
import PopUpForm from "../UI/PopUpForm/PopUpForm";

const GetLocalityForm = ({ level, appendLocality, isHidden, setIsHidden }) => {
    const [localities, setLocalities] = useState([]);

    useEffect(() => {
        async function getLocalities() {
            let response = await fetch(`${await CentralServerLinksProvider.getLink(`locality-level`)}`.replace('{level}', level), {
                method: 'get',
                headers: {'Accept': 'application/json'}
            })
            setLocalities((await response.json()).localities);
        }

        void getLocalities();
    }, [level, isHidden]);

    function append(locality) {
        appendLocality(locality);
        setIsHidden(true);
    }

    return (
        <PopUpForm isHidden={isHidden} header={'Выберите населенный пункт'}>
            {localities.map(locality => {
                return <p onClick={() => {append(locality)}}> {locality.name} </p>
            })}
        </PopUpForm>
    );
};

export default GetLocalityForm;