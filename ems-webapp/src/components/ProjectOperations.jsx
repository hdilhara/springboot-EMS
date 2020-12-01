import Axios from 'axios';
import React, { Component } from 'react';
import Table from './common/Table';
import { projectAPIurl } from '../config/config';
import AppModal from './common/AppModal';
import ProjectForm from './common/ProjectForm';
import AppSelectOption from './common/AppSelectOption';
import AppLoading from './common/AppLoading';

class ProjectOperations extends Component {
    state = {
        values: null,
        modalShow: false,
        initialValue: null,
        initialHandeler: null,
        objectName: 'Project',
        apiUrl: projectAPIurl,
        selectedVal: '',
        isLoading: true
    }


    loadValues() {
        Axios.get(this.state.apiUrl)
            .then(res => { this.setState({ values: res.data }); this.setState({ isLoading: false }) })
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
    handleUpdate = () => {
        let value = this.state.values.filter(a => a.proId == this.state.selectedVal);
        value = { ...value[0] }
        this.setState({ initialValue: value })
        this.setState({ initialHandeler: this.updateObject })
        const modalShow = !this.state.modalShow;
        this.setState({ modalShow });
    }
    deleteObject = () => {
        Axios.delete(this.state.apiUrl + this.state.selectedVal)
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
    }

    render() {
        let { values, isLoading } = this.state;

        // if (isLoading)
        //     <AppLoading />
        if (values == null)
            return null;//<h1>There is no data availabele</h1>
        else {
            return (<div className="container app-center-container">
                <h1>{this.state.objectName}</h1>
                <div>
                    <AppModal
                        show={this.state.modalShow}
                        onHide={() => this.setState({ modalShow: false })}
                        form={<ProjectForm initialHandeler={this.state.initialHandeler} initialValue={this.state.initialValue} />}
                    />


                    <button style={{ margin: '20px 20px 20px 0' }} onClick={this.handleAddNew} className="btn btn-sm btn-success">Add {this.state.objectName}</button>
                    <br />
                    <AppSelectOption
                        name="proId"
                        title="Project"
                        values={this.state.values}
                        disVal1="proId"
                        disVal2="proName"
                        disVal3="companyName"
                        onChanegeHandeler={this.handleDropDownOnChange}
                    />
                    <br />
                    <button style={{ margin: '20px 20px 20px 0' }} className="btn btn-success" onClick={this.handleUpdate} >Update</button>
                    <button style={{ margin: '20px 20px 20px 0' }} className="btn btn-danger" onClick={this.deleteObject} >Delete</button>
                </div>

            </div>)
        }


    }

    componentDidMount() {
        this.loadValues();
    }

}

export default ProjectOperations;