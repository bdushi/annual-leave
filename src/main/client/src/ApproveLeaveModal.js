import React, { useState, useEffect } from 'react'
import $ from 'jquery';
import axios from "axios"

export const ApproveLeaveModal = (props) => {
    const {leave, show, onButtonClick } = props;
    const [approved, setApproved] = useState(false)
    const [comment, setComment] = useState("")
    useEffect(() => { show ? $('#exampleModal').show(): $('#exampleModal').hide() }, [show])
    useEffect(() => { $('#exampleModal').on('hide.bs.modal', () => onButtonClick('close')) }, [])
    return(
        <div className="modal show fade" id="exampleModal" aria-hidden="true">
        <div className="modal-dialog">
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title">Approveed Leave: {leave?.description}</h5>
                    <button type="button" className="btn-close" data-dismiss="modal" aria-label="Close" onClick={() => onButtonClick('close')}></button>
                </div>
                <form className="align-items-center">
                    <div className="row g-3 align-items-center">
                        <div className="col-md-6" style={{ textAlign: "end", textAlignLast: "end"}}>
                            <h5>Requested By</h5>
                        </div>
                        <div className="col-md-6" style={{ textAlign: "left", textAlignLast: "left", padding: "3px 0"}}>
                            <h6>{new Date(leave?.createDate).toLocaleString('en-GB')}</h6>
                        </div>
                    </div>
                    <div className="row g-3">
                        <div className="col-md-6" style={{ textAlign: "end", textAlignLast: "end"}}>
                            <h5>Start Date</h5>
                        </div>
                        <div className="col-md-6" style={{ textAlign: "left", textAlignLast: "left", padding: "3px 0"}}>
                            <h6>{new Date(leave?.startDate).toLocaleString('en-GB')}</h6>
                        </div>
                    </div>
                    <div className="row g-3">
                        <div className="col-md-6" style={{ textAlign: "end", textAlignLast: "end"}}>
                            <h5>End Date</h5>
                        </div>
                        <div className="col-md-6" style={{ textAlign: "left", textAlignLast: "left", padding: "3px 0"}}>
                            <h6>{new Date(leave?.endDate).toLocaleString('en-GB')}</h6>
                        </div>
                    </div>
                    <div className="row g-3">
                        <div className="col-md-6" style={{ textAlign: "end", textAlignLast: "end"}}>
                            <h5 >Leave Type</h5>
                        </div>
                        <div className="col-md-6" style={{ textAlign: "left", textAlignLast: "left", padding: "3px 0"}}>
                            <h6>{leave?.leaveTypes?.description}</h6>
                        </div>
                    </div>
                    <div className="row g-3">
                        <div className="col-md-6" style={{ textAlign: "end", textAlignLast: "end"}}>
                            <h5>Approved</h5>
                        </div>
                        <div className="col-md-6"  style={{ padding: "1px 0"}}>
                            <input 
                                class="form-check-input" 
                                type="checkbox"
                                alue={approved}
                                onChange={e => setApproved(e.target.value)}/>
                        </div>
                    </div>
                    <div className="mb-3" style={{marginLeft: "2px", marginRight: "2px"}}>
                        <input 
                            type="text" 
                            class="form-control" 
                            placeholder="Comment" 
                            aria-label="Comment"
                            alue={comment}
                            onChange={e => setComment(e.target.value)}
                        />
                    </div>
                </form>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-dismiss="modal" onClick={() => onButtonClick('close')}>Close</button>
                    <button type="button" className="btn btn-primary" onClick={() => {
                        var formData = new URLSearchParams();
                        formData.append("comment", comment);
                        formData.append("approved", approved);
                        axios
                            .post(
                                `/leave/approved/${leave.id}`, 
                                formData, 
                                { "Content-Type": "application/x-www-form-urlencoded" }
                            )
                            .then(function (response) {
                                console.log(response)
                                onButtonClick('save');
                            }).catch((error) => {
                                console.log(error)
                            });
                        }
                    }>Save changes</button>
                </div>
            </div>
        </div>
    </div>
    )
}