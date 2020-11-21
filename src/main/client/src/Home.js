
import axios from "axios"
import React, { useState, useEffect } from "react";
import { Leaves } from "./Leaves";

function leave() {
    axios
        .get("/leave", {
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        }).then(function (response) {
            console.log(response.data)
        }).catch((error) => {
            console.log(error)
            // this.setState({ open: true, vertical: "top", horizontal: "right", message:"Incorrect Username or Password!" });
        });
}

function Home() {
    const  [leaves, setLeaves]= useState([])
    useEffect(
        () => {
            axios
                .get("/leave")
                .then(function (response) {
                    setLeaves(response.data)
                }).catch((error) => {
                    console.log(error)
                    // this.setState({ open: true, vertical: "top", horizontal: "right", message:"Incorrect Username or Password!" });
                });
        }
        ,[]
    )

    return(<Leaves leaves = { leaves }/>)
}

export default Home;