import React, {useRef, useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import CsrfFetch from "../../utils/CsrfFetch";
import CentralServerLinksProvider from "../../utils/CentralServerLinksProvider";
import styles from './style.module.css';
import TextInput from "../UI/TextInput/TextInput";
import Button from "../UI/Button/Button";
import ErrorMessage from "../UI/ErrorMessage/ErrorMessage";

const Login = () => {
    const navigate = useNavigate();
    const [isErrorHidden, setIsErrorHidden] = useState(true);
    const loginRef = useRef(null);
    const passwordRef = useRef(null);

    async function login() {
        let response = await CsrfFetch(await CentralServerLinksProvider.getLink('login'), {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                username: loginRef.current.value,
                password: passwordRef.current.value
            })
        });

        if (response.ok)
            navigate('/');
        else
            setIsErrorHidden(false);
    }

    return (
        <div className={styles.login}>
            <img className={styles.loginLogo} src={'assets/icons/logo.png'} alt={'Logo'}/>
            <div className={styles.loginSignWrapper}>
                <p className={styles.loginSign}> АСУ </p>
                <p className={styles.loginSign}> Государственная </p>
                <p className={styles.loginSign}> Автоинспекция </p>
            </div>
            <div className={styles.loginTextInputsWrapper}>
                <TextInput placeholder={"Логин"} type={'text'} inputRef={loginRef}/>
                <TextInput placeholder={"Пароль"} type={'password'} inputRef={passwordRef}/>
                <Button text={'Войти'} onClick={login}/>
                <ErrorMessage isHidden={isErrorHidden} message={'Пользователь с таким логином и паролем не найден'}/>
            </div>
        </div>
    );
};

export default Login;