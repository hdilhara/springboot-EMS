// import Axios from 'axios';
import { authAxios } from '../services/backEndService';
import React, { Component } from 'react';
import Table from './common/Table';
import { employeeApiUrl } from '../config/config';
import AppModal from './common/AppModal';
import EmployeeForm from './common/EmployeeForm';
import AppSelectOption from './common/AppSelectOption';


class EmployeeOperations extends Component {
    state = {
        values: null,
        modalShow: false,
        initialValue: null,
        initialHandeler: null,
        objectName: 'Employee',
        apiUrl: employeeApiUrl,
        selectedVal: ''
    }


    loadValues() {
        authAxios.get(this.state.apiUrl)
            .then(res => this.setState({ values: res.data }))
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
    handleUpdate = () => {
        let value = this.state.values.filter(a => a.empId == this.state.selectedVal);
        value = { ...value[0] }
        this.setState({ initialValue: value })
        this.setState({ initialHandeler: this.updateObject })
        const modalShow = !this.state.modalShow;
        this.setState({ modalShow });
    }
    deleteObject = () => {
        authAxios.delete(this.state.apiUrl + this.state.selectedVal)
            .then(res => { this.loadValues() });
    }

    addButtonToValueSet(values, attrName, btnName, handeler, type) {
        //add update btn
        values
            .map(v => { v[attrName] = <button className={'btn btn-sm btn-' + type} onClick={() => handeler(v)}>{btnName}</button > });
    }
    handleDropDownOnChange = (e) => {
        const selectedVal = e.currentTarget.value;
        this.setState({ selectedVal });
        console.log(selectedVal);
    }

    render() {
        let { values } = this.state;
        if (values == null)
            return null//<h1>There is no data availabele</h1>
        else {
            return (<div className="container app-center-container  ">
                <h1>{this.state.objectName}</h1>
                <div>
                    <AppModal
                        show={this.state.modalShow}
                        onHide={() => this.setState({ modalShow: false })}
                        form={<EmployeeForm initialHandeler={this.state.initialHandeler} initialValue={this.state.initialValue} />}
                    />
                    <button style={{ margin: '20px 20px 20px 0' }} onClick={this.handleAddNew} className="btn btn-sm btn-success">Add {this.state.objectName}</button>
                    <br />
                    <AppSelectOption
                        name="empId"
                        title="Employee"
                        values={this.state.values}
                        disVal1="empId"
                        disVal2="firstName"
                        disVal3="lastName"
                        onChanegeHandeler={this.handleDropDownOnChange}
                    />
                    <br />
                    <div >
                        <button style={{ margin: '20px 20px 20px 0' }} className="btn btn-success" onClick={this.handleUpdate} >Update</button>
                        <button style={{ margin: '20px 20px 20px 0' }} className="btn btn-danger" onClick={this.deleteObject} >Delete</button>
                    </div>
                </div>

            </div>)
        }


    }

    componentDidMount() {
        this.loadValues();
    }

}

export default EmployeeOperations;