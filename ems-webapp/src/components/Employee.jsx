import Axios from 'axios';
import React, { Component } from 'react';
import Table from './common/Table';
import { employeeApiUrl } from '../config/config';
import AppModal from './common/AppModal';
import EmployeeForm from './common/EmployeeForm';
import AppLoading from './common/AppLoading';

class Employee extends Component {
    state = {
        values: null,
        modalShow: false,
        initialValue: null,
        initialHandeler: null,
        objectName: 'Employee',
        apiUrl: employeeApiUrl,
        isLoading: true
    }


    loadValues() {
        Axios.get(this.state.apiUrl)
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
        Axios
            .post(this.state.apiUrl, value)
            .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
            .catch(err => console.log(err));
    }
    updateObject = (value) => {
        Axios
            .put(this.state.apiUrl, value)
            .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
            .catch(err => console.log(err));
    }
    handleUpdate = (value) => {
        value = { ...value };
        delete value.UPDATE;
        delete value.DELETE;
        delete value.VIEW;
        this.setState({ initialValue: value })
        this.setState({ initialHandeler: this.updateObject })
        const modalShow = !this.state.modalShow;
        this.setState({ modalShow });
    }
    deleteObject = (value) => {
        Axios.delete(this.state.apiUrl + value.empId)
            .then(res => { this.loadValues() });
    }

    addButtonToValueSet(values, attrName, btnName, handeler, type) {
        //add update btn
        values
            .map(v => { v[attrName] = <button className={'btn btn-sm btn-outline-' + type} onClick={() => handeler(v)}>{btnName}</button > });
    }

    handleView = (value) => {
        this.props.history.push('/view/' + value.empId);
    }

    render() {
        let { values, isLoading } = this.state;
        if (isLoading)
            return <AppLoading />
        if (values == null)
            return <h1>There is no data availabele</h1>
        else {
            this.addButtonToValueSet(values, 'VIEW', "view", this.handleView, 'success');
            this.addButtonToValueSet(values, 'UPDATE', "update", this.handleUpdate, 'success');
            this.addButtonToValueSet(values, 'DELETE', "delete", this.deleteObject, 'danger');

            return (<div className="container">
                <AppModal
                    show={this.state.modalShow}
                    onHide={() => this.setState({ modalShow: false })}
                    form={<EmployeeForm initialHandeler={this.state.initialHandeler} initialValue={this.state.initialValue} />}
                />
                <h1>{this.state.objectName}</h1>
                <button onClick={this.handleAddNew} className="btn btn-sm btn-success">Add {this.state.objectName}</button>
                <Table headers={Object.keys(values[0])} values={values} rowKeyProName={'empId'} />
            </div>)
        }


    }

    componentDidMount() {
        this.loadValues();
    }

}

export default Employee;