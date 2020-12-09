// import Axios from 'axios';
import { authAxios } from '../services/backEndService';
import React, { Component } from 'react';
import Table from './common/Table';
import TaskForm from './common/TaskForm';
import { taskAPIurl } from '../config/config';
import AppModal from './common/AppModal';
import AppLoading from './common/AppLoading';

class Task extends Component {
    state = {
        values: null,
        modalShow: false,
        initialValue: null,
        initialHandeler: null,
        objectName: 'Task',
        apiUrl: taskAPIurl,
        isLoading: true
    }


    loadValues() {
        authAxios.get(this.state.apiUrl)
            .then(res => { res.data.length > 0 ? this.setState({ values: res.data }) : this.setState({ values: null }); this.setState({ isLoading: false }) })
            .catch(e => console.log(e));
    }

    handleAddNew = () => {
        this.setState({ initialValue: null })
        this.setState({ initialHandeler: this.saveObject })
        const modalShow = !this.state.modalShow;
        this.setState({ modalShow });
    }
    saveObject = (value) => {
        authAxios
            .post(this.state.apiUrl, value)
            .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
            .catch(err => console.log(err));
    }
    updateObject = (value) => {
        authAxios
            .put(this.state.apiUrl, value)
            .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
            .catch(err => console.log(err));
    }
    handleUpdate = (value) => {
        value = { ...value };
        delete value.UPDATE;
        delete value.DELETE;
        this.setState({ initialValue: value })
        this.setState({ initialHandeler: this.updateObject })
        const modalShow = !this.state.modalShow;
        this.setState({ modalShow });
    }
    deleteObject = (value) => {
        authAxios.delete(this.state.apiUrl + value.taskId)
            .then(res => { this.loadValues() });
    }

    addButtonToValueSet(values, attrName, btnName, handeler, type) {
        //add update btn
        values
            .map(v => { v[attrName] = <button className={'btn btn-sm btn-outline-' + type} onClick={() => handeler(v)}>{btnName}</button > });
    }

    render() {
        let { values, isLoading } = this.state;

        if (isLoading)
            return <AppLoading />
        if (values == null)
            return <h1>There is no data availabele</h1>
        else {
            this.addButtonToValueSet(values, 'UPDATE', "update", this.handleUpdate, 'success');
            this.addButtonToValueSet(values, 'DELETE', "delete", this.deleteObject, 'danger');

            return (<div className="container">
                <AppModal
                    show={this.state.modalShow}
                    onHide={() => this.setState({ modalShow: false })}
                    // initialHandeler={this.state.initialHandeler}
                    // initialValue={this.state.initialValue}
                    form={<TaskForm initialHandeler={this.state.initialHandeler} initialValue={this.state.initialValue} />}
                />
                <h1>{this.state.objectName}</h1>
                <button onClick={this.handleAddNew} className="btn btn-sm btn-success">Add {this.state.objectName}</button>
                <Table headers={Object.keys(values[0])} values={values} rowKeyProName={'taskId'} />
            </div>)
        }


    }

    componentDidMount() {
        this.loadValues();
    }

}

export default Task;