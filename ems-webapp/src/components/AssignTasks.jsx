import React, { Component } from 'react';
import { addEmpProTask, getEmployees, getProjects, getTasks } from '../services/backEndService';
import Axios from 'axios';
import AppSelectOption from './common/AppSelectOption';

class AssignTasks extends Component {
    state = {
        employees: '',
        projects: '',
        tasks: '',
        value: {
            empId: '',
            proId: '',
            taskId: ''
        }
    }

    handleFormOnChange = (e) => {
        const value = { ...this.state.value };
        value[e.currentTarget.name] = e.currentTarget.value;
        this.setState({ value });
    }

    onFormSubmit = (e) => {
        e.preventDefault();
        addEmpProTask(this.state.value, this.callbackAddEPT)
    }

    render() {
        return (
            <div className="app-center-container">
                <h1>Assign Task</h1>
                <form onSubmit={this.onFormSubmit} >
                    <div >
                        <div style={{ padding: '15px' }} ><b>Select Employee</b></div>
                        <AppSelectOption
                            onChanegeHandeler={this.handleFormOnChange}
                            name="empId"
                            title="Employee"
                            values={this.state.employees}
                            disVal1="empId"
                            disVal2="firstName"
                            disVal3="lastName"
                        />
                        <div style={{ padding: '15px' }}  ><b>Select Project</b></div>
                        <AppSelectOption
                            onChanegeHandeler={this.handleFormOnChange}
                            name="proId"
                            title="Project"
                            values={this.state.projects}
                            disVal1="proId"
                            disVal2="proName"
                            disVal3="companyName"
                        />
                        <div style={{ padding: '15px' }}  ><b>Select Task</b></div>
                        <AppSelectOption
                            onChanegeHandeler={this.handleFormOnChange}
                            name="taskId"
                            title="Task"
                            values={this.state.tasks}
                            disVal1="taskId"
                            disVal2="taskTitle"
                            disVal3='description'
                        />
                        <br />
                        <button style={{ margin: '25px 0' }} className="btn btn-success">Assign Task</button>
                    </div>
                </form>
            </div>
        );
    }



    callBackGetEmployees = (values) => {
        this.setState({ employees: values });
    }
    callBackGetProjects = (values) => {
        this.setState({ projects: values });
    }
    callBackGetTasks = (values) => {
        this.setState({ tasks: values });
    }
    callbackAddEPT = (values) => {

    }

    componentDidMount() {
        getEmployees(this.callBackGetEmployees);
        getProjects(this.callBackGetProjects);
        getTasks(this.callBackGetTasks);
    }
}

const styles = {

    container: {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column'
    }
}

export default AssignTasks;