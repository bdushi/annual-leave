import axios from "axios"
import React, { useState, useEffect } from "react";

export const LeaveStatus = (props) => {
    const { leaveId } = props;
    const [approved, setApproved] = useState([])

    useEffect(() => {
        axios
            .get(`/approved?id=${leaveId}`)
            .then(function (response) {
                setApproved(response.data)
            }).catch((error) => {
                console.log(error)
            });
    }
    ,[leaveId]
)
return(
    <button 
            className={ approved.length !== 0 ? approved[approved.length - 1].approved ? "btn btn-sm btn-success" : "btn btn-sm btn-danger" : "btn btn-sm btn-secondary"} 
            type="button" 
            onClick={() => {
                console.log("Open Modal")
            }}>
            { approved.length !== 0 ? approved[approved.length - 1].comment : "Not Defined" }
        </button>)
}