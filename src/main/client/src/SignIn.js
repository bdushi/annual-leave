import logo from './logo.svg';
import React, { useState } from "react";
import axios from "axios"

function SingIn(props) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    return (
        <div className="form-signin text-center">
            <img src={logo} alt="logo" width="72" height="72" className="d-inline-block"/>
            <h1 className="h3 mb-3 fw-normal">Please sign in</h1>
            <label 
                htmlFor="inputUsername" 
                className="visually-hidden">
                    Username
            </label>
            <input
                value={username}
                onChange={e => setUsername(e.target.value)}
                id="inputUsername"
                type="text" 
                className="form-control input-lg" 
                placeholder="Username" 
                required 
                autoFocus/>
            <label 
                htmlFor="inputPassword" 
                className="visually-hidden">
                    Password
            </label>
            <input 
                value={password}
                onChange={e => setPassword(e.target.value)}
                type="password" 
                id="inputPassword" 
                className="form-control" 
                placeholder="Password" 
                required=""/>
            <div className="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"/> Remember me
            </label>
            </div>
            <button 
                className="btn btn-lg btn-primary btn-block" 
                type="submit" 
                onClick={() => submit(props, username, password) }>
                    Sign in
                </button>
            </div>
    );
}

function submit(props, username, password) {
    var formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);
    axios
        .post(
            '/auth/login', 
            formData, 
            { "Content-Type": "application/x-www-form-urlencoded" })
        // .post("/auth/login", formData, { "Content-Type": "multipart/form-data" })
        .then(function (response) {
            localStorage.setItem("token", response.data);
            props.history.push("/");
            console.log(response.headers);
            // feach user
            axios({
                method: 'get',
                url: '/user',
                responseType: 'stream'
              })
                .then(function (response) {
                    localStorage.setItem("user", JSON.stringify(response.data))
                }).catch(function (error) {
                    console.log(error)
                });
            // Pop to root
        })
        .catch(function (error) {
            console.log(error)
        });
}

export default SingIn;