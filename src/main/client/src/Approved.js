import axios from "axios"
import React, { useState, useEffect } from "react";

export const Approved = (props) => {
    const { leaveId } = props;

    const [approved, setApproved] = useState([])

    const [toggle, setToggle] = useState(false)
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
    <div className="dropdown">
        <button 
            className={ approved.length !== 0 ? approved[approved.length - 1].approved ? "btn btn-sm dropdown-toggle btn-success" : "btn btn-sm dropdown-toggle btn-danger" : "btn btn-sm dropdown-toggle btn-secondary"} 
            type="button" 
            id="dropdownMenuButton" 
            data-toggle="dropdown" 
            aria-expanded="false"
            onClick={() => setToggle(!toggle)}
            data-offset="0,0">
            { approved.length !== 0 ? approved[approved.length - 1].comment : "Not Defined" }
        </button>
        <ul className={ toggle ? "dropdown-menu show" : "dropdown-menu hide" } aria-labelledby="dropdownMenuButton">
            { approved.map((approve, index) => <li key = { approve.id }> <a className="dropdown-item btn-success" href="#">{approve.comment}</a></li>) }
        </ul>
    </div>
)
}