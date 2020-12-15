import React from 'react'
import ReactDOM from 'react-dom'
import { ApproveLeaveModal } from './ApproveLeaveModal';
import $ from 'jquery';

const sampleModalId = 'sample-modal-container';

export const Modal = () => {
    return (
        <div>
          {/* <a href="javascript:;" onClick={this.handleShowSampleModal}>show modal</a> */}
        </div>
      )
}

function handleShowSampleModal() {
    var modal = React.cloneElement(<ApproveLeaveModal></ApproveLeaveModal>);
    var modalContainer = document.createElement('div');
    modalContainer.id = sampleModalId;
    document.body.appendChild(modalContainer);
    ReactDOM.render(modal, modalContainer, function() {
      var modalObj = $('#'+sampleModalId+'>.modal');
      modalObj.modal('show');
      modalObj.on('hidden.bs.modal', this.handleHideSampleModal);
    }.bind(this));
}

function handleHideSampleModal() {
    $('#'+sampleModalId).remove();
}