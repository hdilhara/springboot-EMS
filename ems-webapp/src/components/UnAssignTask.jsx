import React, { Component } from 'react';
import { addEmpProTask, getEmployees, getProjects, getTasks, getEmpProTaskByEID, removeEmpProTask } from '../services/backEndService';
import Axios from 'axios';
import AppSelectOption from './common/AppSelectOption';
import AppLoading from './common/AppLoading';

class UnAssignTask extends Component {
    state = {
        employees: '',
        projects: '',
        tasks: '',
        selectedEmpId: '',
        selectedProIds: '',
        selectedTaskIds: '',
        value: {
            empId: '',
            proId: '',
            taskId: ''
        },
        isLoading: true
    }

    handleFormOnChange = (e) => {
        const value = { ...this.state.value };
        value[e.currentTarget.name] = e.currentTarget.value;
        this.setState({ value });
        if (e.currentTarget.name === 'empId') {
            this.setState({ selectedEmpId: e.currentTarget.value });
            getEmpProTaskByEID(e.currentTarget.value, this.filterCallback)
        }
    }

    filterCallback = (vals) => {
        if (vals) {
            let selectedProIds = [];
            let selectedTaskIds = [];
            for (let x of vals) {
                selectedProIds.push(x.id.proId);
                selectedTaskIds.push(x.id.taskId);
            }
            selectedProIds = [...(new Set(selectedProIds))];
            selectedTaskIds = [...(new Set(selectedTaskIds))];
            this.setState({ selectedProIds })
            this.setState({ selectedTaskIds })
        }

    }



    onFormSubmit = (e) => {
        e.preventDefault();
        removeEmpProTask(this.state.value, this.callbackAddEPT)
    }

    render() {

        const { selectedEmpId, projects, selectedProIds, tasks, selectedTaskIds, isLoading } = this.state;

        return (
            <div className="app-center-container" >
                <h1>Unassign Task</h1>
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
                            values={(selectedEmpId) ? projects.filter(a => selectedProIds.includes(a.proId)) : null}
                            disVal1="proId"
                            disVal2="proName"
                            disVal3="companyName"
                        />
                        <div style={{ padding: '15px' }}  ><b>Select Task</b></div>
                        <AppSelectOption
                            onChanegeHandeler={this.handleFormOnChange}
                            name="taskId"
                            title="Task"
                            values={(selectedEmpId) ? tasks.filter(a => selectedTaskIds.includes(a.taskId)) : null}
                            disVal1="taskId"
                            disVal2="taskTitle"
                            disVal3='description'
                        />
                        <br />
                        <button style={{ margin: '25px 0' }} className="btn btn-danger">Unassign Task</button>
                    </div>
                </form>
            </div>
        );
    }



    callBackGetEmployees = (values) => {
        this.setState({ employees: values });
    }
    callBackGetProjects = (values) => {
        if (values.length > 0)
            this.setState({ projects: values });
    }
    callBackGetTasks = (values) => {
        this.setState({ tasks: values });
    }
    callbackAddEPT = (values) => {
        getEmpProTaskByEID(0, this.filterCallback)
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

export default UnAssignTask;