import Axios from 'axios';
import React, { Component } from 'react';
import Table from './common/Table';
import AppModal from './common/AppModal';
import { getProjectsByIds, getEmpProTaskByEID, getTasksByIds } from '../services/backEndService';
import { empProTaskAPIurl } from '../config/config';
import AppLoading from './common/AppLoading';


class ViewProjectsTasks extends Component {
    state = {
        projects: null,
        modalShow: false,
        task: null,
        tasks: null,
        isLoading: true
    }

    empId = this.props.match.params.empId;

    setProjects = (data) => {
        this.setState({ projects: data });
        console.log(this.state.projects);
        this.setState({ isLoading: false });
    }

    handleData = (data) => {
        let proIds = [];
        for (let d of data) {
            proIds.push(d.id.proId);
        }
        if (proIds.length > 0) {
            //remove dulicates
            proIds.sort((a, b) => a - b)
            proIds = proIds.filter((a, b) => {
                if ((b - 1) >= 0 && a != proIds[b - 1]) {
                    return true
                }
                else
                    return false
            });
            getProjectsByIds(proIds, this.setProjects)
        }
        else {
            this.setState({ isLoading: false })
        }

    }

    addButtonToValueSet(values, attrName, btnName, handeler, type) {
        //add update btn
        values
            .map(v => { v[attrName] = <button className={'btn btn-sm btn-' + type} onClick={() => handeler(v)}>{btnName}</button > });
    }

    handleViewTask = (value) => {
        let modalShow = true;
        this.setState({ modalShow })
        this.handleTask(value.proId)
    }

    handleTask = (pid) => {
        Axios.get(empProTaskAPIurl + 'eid/pid/' + this.empId + '/' + pid)
            .then(res => {
                let taskIds = [];
                for (let x of res.data) {
                    taskIds.push(x.id.taskId)
                }
                getTasksByIds(taskIds, this.setTasks);
            })
            .catch(e => console.log(e));
    }

    setTasks = (values) => {
        this.setState({ tasks: values });
        let task = <div>
            <Table headers={Object.keys(values[0])} values={values} rowKeyProName={'taskId'} />
        </div>
        this.setState({ task })
    }

    render() {

        if (this.state.isLoading)
            return <AppLoading />

        if (this.state.projects === null)
            return <div className="container"><h1>Assigined tasks not available!</h1></div>

        this.addButtonToValueSet(this.state.projects, 'TASKS', "tasks", this.handleViewTask, 'success');

        return (
            <div className="container">
                <AppModal
                    show={this.state.modalShow}
                    onHide={() => this.setState({ modalShow: false })}
                    // initialHandeler={this.state.initialHandeler}
                    // initialValue={this.state.initialValue}
                    form={this.state.task}
                />
                <h1>View Employee Project Tasks</h1>
                <Table headers={Object.keys(this.state.projects[0])} rowKeyProName={'proId'} values={this.state.projects} />
            </div>

        );
    }

    componentDidMount() {
        getEmpProTaskByEID(this.empId, this.handleData);
    }

}

export default ViewProjectsTasks;