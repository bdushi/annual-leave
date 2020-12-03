import axios from "axios"
import React, { useState, useEffect } from "react";

export const LeavesApproved = (props) => {
    const { leaveId } = props.location.state;
    const [approved, setApproved] = useState([])
    console.log(props)
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
        <table className="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Approved Date</th>
                    <th scope="col">Comment</th>
                    <th scope="col">Approved</th>
                    <th scope="col">Approved By</th>
                </tr>
            </thead>
                <tbody>
                    {
                        approved.map((approve, index) => {
                            return(
                                <tr key = { approve.id }>
                                    <th scope="row">{approve.id}</th>
                                    <td>{new Date(approve.approvedDate).toLocaleString('en-GB')} </td>
                                    <td>{approve.comment}</td>
                                    <td>{approve.approved ? "Approved" : "Not Approved"}</td>
                                    <td>{approve.approvedBy.username}</td>
                                </tr>
                            )
                        })
                    }
                </tbody>
        </table> 
    )
}