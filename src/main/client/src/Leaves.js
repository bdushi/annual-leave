import React, { useState, useEffect } from "react";
import { LeavesBody } from "./LeavesBody"
import { Alert } from "./Alert";
import { ApproveLeaveModal } from "./ApproveLeaveModal";

export const Leaves = (props) => {
    const { leaves, onRefresh } = props;
    const [visibility, setVisibility] = useState(false);
    const [messages, setMessages] = useState();
    const [show, setShow] = useState(false);
    const [leave, setLeave] = useState();

    const time = () => {
        return setTimeout(() => { 
            setVisibility(false)
        }, 2000);
    }

    const alertVisibility = () => {
        setVisibility(true);
        time();
    };

    const alertClose = () => {
        setVisibility(false)
        clearTimeout(time);
    }

    const onMessages = (messages) => {
        setMessages(messages);
    }

    const onApproveLeave = (leave) => {
        setShow(true);
        setLeave(leave)
    }
    
    useEffect(() => {
        setVisibility(props.visibility)
        setMessages(props.messages)
    }
    ,[props]
    )

    return (
        <section>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Requested By</th>
                    <th scope="col">Requested Date</th>
                    <th scope="col">Start Date</th>
                    <th scope="col">End Date</th>
                    <th scope="col">Leave Type</th>
                    <th scope="col">Leave Description</th>
                    <th scope="col">Leave Comment</th>
                    <th scope="col">Approved</th>
                </tr>
                </thead> 
                    <tbody>
                        {
                            leaves.map((leave, index) => {
                                return(
                                    <LeavesBody 
                                        key = {leave.id} 
                                        leave = {leave} 
                                        onLeavesApproved = {alertVisibility}
                                        onMessages = { onMessages }
                                        onApproveLeave = { onApproveLeave }/>
                                )
                            })
                        }
                    </tbody>
            </table>
            <Alert 
                messages = { messages } 
                visibility = {visibility  ? "alert alert-primary alert-dismissible fade show" : "alert alert-primary alert-dismissible fade close"} 
                onAlertClose = {alertClose}
            />
            <ApproveLeaveModal
                show = { show }
                leave = { leave }
                onButtonClick = { button => {
                    if(button == 'close') 
                        setShow(false);
                    if(button == 'save') {
                        setShow(false);
                        onRefresh()
                    }
                } 
            }/>
        </section>
    );
}

