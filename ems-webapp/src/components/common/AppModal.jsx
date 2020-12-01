import React from 'react';
import { Modal, Button } from 'react-bootstrap';
import EmployeeForm from './EmployeeForm';

const AppModal = (props) => {
    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    <div style={{ textAlign: "center" }}>EMS</div>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <h4></h4>
                {props.form}
            </Modal.Body>
            {/* <Modal.Footer>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer> */}
        </Modal>
    );
}

export default AppModal;