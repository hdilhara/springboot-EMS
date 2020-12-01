import React, { Component } from 'react';
import { Nav } from 'react-bootstrap';
import AssignTasks from './AssignTasks';
import Employee from './Employee';
import AppSideBar from './common/AppSideBar';
import EmployeeOperations from './EmployeeOperations';
import ProjectOperations from './ProjectOperations';
import TaskOperations from './TaskOperations';
import UnAssignTask from './UnAssignTask';

class Operations extends Component {
    state = {
        currentTab: 'Employee'
    }

    handleSideBar = (val) => {
        console.log(val);
        this.setState({ currentTab: val })
    }

    render() {
        return (
            <div >
                <div className="row">
                    <div className="col-md-2">
                        <AppSideBar
                            values={["Employee", "Project", "Task", "Assign Task", "Unassign Task"]}
                            handleClick={this.handleSideBar}
                        />
                    </div>
                    <div className="col-md-10">
                        {this.state.currentTab == 'Employee' && <EmployeeOperations />}
                        {this.state.currentTab == 'Project' && <ProjectOperations />}
                        {this.state.currentTab == 'Task' && <TaskOperations />}
                        {this.state.currentTab == 'Assign Task' && <AssignTasks />}
                        {this.state.currentTab == 'Unassign Task' && <UnAssignTask />}
                    </div>
                </div>
            </div>
            // <Nav variant="tabs" defaultActiveKey="/home">
            //     <Nav.Item>
            //         <Nav.Link eventKey="link-1">Active</Nav.Link>
            //     </Nav.Item>
            //     <Nav.Item>
            //         <Nav.Link eventKey="link-2">Option 2</Nav.Link>
            //     </Nav.Item>
            //     <Nav.Item>
            //         <Nav.Link eventKey="link-3" >
            //             Option 3
            //         </Nav.Link>
            //     </Nav.Item>
            // </Nav>
        );
    }
}



export default Operations;