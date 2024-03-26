import React, {useEffect, useRef} from 'react';
import PopUpForm from "../UI/PopUpForm/PopUpForm";
import {useNavigate} from "react-router-dom";
import requireRole from "../../utils/RequireRole";
import TextInput from "../UI/TextInput/TextInput";
import Button from "../UI/Button/Button";
import LocalityBuilder from "../LocalityBuilder/LocalityBuilder";

const AddDepartment = ({isHidden, setIsHidden, authentication }) => {
    const nameRef = useRef(null);
    const addressRef = useRef(null);
    const navigate = useNavigate();

    useEffect(() => {
        if (!requireRole("admin", authentication) && !requireRole("department_owner", authentication))
            navigate("/");
    }, [authentication, navigate]);

    async function add() {

    }

    return (
        <PopUpForm isHidden={isHidden} header={'Добавить новый отдел'}>
            <p>Населенный пункт</p>
            <LocalityBuilder items={[]}/>
            <TextInput type={'text'} placeholder={'Название'} inputRef={nameRef}/>
            <TextInput type={'text'} placeholder={'Адрес'} inputRef={addressRef}/>
            <Button text={'Добавить'} onClick={add}/>
            <Button text={'Отменить'} onClick={() => {setIsHidden(true);}}/>
        </PopUpForm>
    );
};

export default AddDepartment;