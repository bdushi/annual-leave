import axios from "axios"
import React, { useState, useEffect } from "react";

export const LeaveStatus = (props) => {
    const { 
            leave,
            onApproveLeave 
        } = props;
    const [approved, setApproved] = useState(null)

    useEffect(() => {
        axios
            .get(`/approved?id=${leave.id}`)
            .then(function (response) {
                setApproved(response.data)
            }).catch((error) => {
                console.log(error)
            });
    }
    ,[leave]
)
return(
    <span class="d-inline-block" tabindex="0" data-bs-toggle="tooltip" data-bs-placement="top" title={ approved?.comment }>
        <button 
            className={ approved !== null ? approved.approved ? "btn btn-sm btn-success" : "btn btn-sm btn-danger" : "btn btn-sm btn-secondary"} 
            type="button" 
            style={{
                textOverflow: "ellipsis",
                whiteSpace: "nowrap",
                overflow: "hidden",
                width: "80px"
            }}
            onClick={() => {
                onApproveLeave(leave)
            }}>
            { approved !== null ? approved.comment : "Not Defined" }
        </button>
    </span>
    )
}