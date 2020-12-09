import React, { Component } from 'react';
import AppSelectOption from './common/AppSelectOption';
import { getEmployees, getEmpProTaskByEID, getProjectsByIds, getEmpProTaskByEIDPID, getTasksByIds, removeEmpProTask } from '../services/backEndService';

class UnAssignTask extends Component {
    state = {
        employees: null,
        projects: null,
        tasks: null,
        selectedEmpId: null,
        selectedProId: null,
        selectedTaskId: null,

    }


    setEmployeesValues = (values) => {
        this.setState({ employees: values });
    }

    setProjectValues = (values) => {
        this.setState({ projects: values });
    }

    setTasksValues = (values) => {
        this.setState({ tasks: values });

    }

    getEmployeeProjects = (value) => {
        let proIds = [];
        for (let x of value) {
            proIds.push(x.id.proId)
        }
        //remove duplicates
        proIds.sort((a, b) => a - b);
        console.log(proIds)
        proIds = proIds.filter((a, b) => {
            if (b > 0 && a === proIds[b - 1]) {
                return false;
            }
            return true;
        })
        getProjectsByIds(proIds, this.setProjectValues);
    }
    getEmployeeProjectTask = (taskIds) => {
        console.log(taskIds)
        getTasksByIds(taskIds, this.setTasksValues)
    }
    restValues = () => {
        // this.setState({ employees: null });
        this.setState({ projects: null });
        this.setState({ tasks: null });
        // this.setState({ selectedEmpId: null });
        this.setState({ selectedProId: null });
        this.setState({ selectedTaskId: null });
        getEmpProTaskByEID(this.state.selectedEmpId, this.getEmployeeProjects);
    }
    handleOnSubmit = (e) => {
        e.preventDefault();
        let value = {
            empId: this.state.selectedEmpId,
            proId: this.state.selectedProId,
            taskId: this.state.selectedTaskId,
        }
        const { selectedEmpId, selectedProId, selectedTaskId } = this.state;
        if (selectedEmpId && selectedProId && selectedTaskId) {
            removeEmpProTask(value, this.restValues);
            window.alert("Successfully unassigned task!");
        }
        else
            window.alert("Please Select All three values!");
    }

    handleOnChange = (e) => {
        let val = e.currentTarget.value;
        if (e.currentTarget.name === 'empId') {
            this.setState({ selectedEmpId: val })
            this.setState({ projects: null });
            this.setState({ tasks: null });
            getEmpProTaskByEID(val, this.getEmployeeProjects);
        }
        else if (e.currentTarget.name === 'proId') {
            this.setState({ selectedProId: val })
            console.log(this.state.selectedEmpId)
            getEmpProTaskByEIDPID(this.state.selectedEmpId, val, this.getEmployeeProjectTask)
        }
        else if (e.currentTarget.name === 'taskId') {
            this.setState({ selectedTaskId: val })
        }
    }

    render() {

        const { employees, projects, tasks } = this.state;

        return (
            <div className="container app-center-container" >
                <h1>Unassign Task</h1>
                <div>
                    <form onSubmit={this.handleOnSubmit} >
                        {
                            employees &&
                            <div>
                                <div style={{ padding: '15px' }}  ><b>Select Employee</b></div>
                                <AppSelectOption
                                    name='empId'
                                    title='Select Employee'
                                    values={this.state.employees}
                                    disVal1='empId'
                                    disVal2='firstName'
                                    disVal3='lastName'
                                    onChanegeHandeler={this.handleOnChange}
                                />
                            </div>
                        }

                        {
                            projects ?
                                <div>
                                    <div style={{ padding: '15px' }}  ><b>Select Project</b></div>
                                    <AppSelectOption
                                        name='proId'
                                        title='Select Project'
                                        values={this.state.projects}
                                        disVal1='proId'
                                        disVal2='proName'
                                        disVal3='country'
                                        onChanegeHandeler={this.handleOnChange}
                                    />
                                </div>
                                : <div style={{ padding: '15px' }}  ><b>There are no tasks available!</b></div>
                        }

                        {
                            tasks &&
                            <div>
                                <div style={{ padding: '15px' }}  ><b>Select Task</b></div>
                                <AppSelectOption
                                    name='taskId'
                                    title='Select Task'
                                    values={this.state.tasks}
                                    disVal1='taskId'
                                    disVal2='taskTitle'
                                    disVal3='description'
                                    onChanegeHandeler={this.handleOnChange}
                                />
                            </div>
                        }
                        <br />
                        <button className="btn btn-sm btn-danger"  >
                            Unassign
                        </button>
                    </form>
                </div>

            </div>
        );
    }

    componentDidMount() {
        getEmployees(this.setEmployeesValues);
    }

}

export default UnAssignTask;