import React, { useState, useEffect } from 'react'

export const ApproveLeave = (props) => {
    // {classNames('modal', { show: this.state.visible, fade: this.props.fade }, className)
    const { show, leave, onSave } = props;
    const [showModal, setShowModal] = useState(false)
    useEffect(() => {
            setShowModal(show)
        },[props]
    )
    return (
        <div 
            className={showModal ? "modal modal-dialog modal-xl fade show" : "modal modal-dialog modal-xl fade"} 
            data-show = "true" 
            tabindex="-1" 
            role="dialog"
            style={{ display: showModal ? "block" : "none"}} 
            aria-hidden="true">
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Approved Leave:</h5>
                        <button type="button" className="btn-close" data-dismiss="modal" aria-label="Close" onClick={() => setShowModal(false)}/>
                    </div>
                    <div className="modal-body">
                        <p>Modal body text goes here.</p>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" data-dismiss="modal" onClick={() => setShowModal(false)}>Close</button>
                        <button type="button" className="btn btn-primary" onClick={() => onSave}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    )
}