import React, { Component } from 'react';
import AppInput from './AppInput';

class EmployeeForm extends Component {
    state = {
        employee: {
            empId: '',
            firstName: '',
            lastName: '',
            birthday: '',
            contactNo: '',
            address: ''

        }
    }

    handleOnInputChange = e => {
        const employee = { ...this.state.employee };
        employee[e.currentTarget.name] = e.currentTarget.value;
        this.setState({ employee });
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.initialHandeler(this.state.employee)
    }

    render() {
        const { employee } = this.state;

        return (
            <form onSubmit={this.handleSubmit} >
                <AppInput
                    label='First Name'
                    name='firstName'
                    placeholder='Enter first name'
                    type='text'
                    value={employee.firstName}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Last Name'
                    name='lastName'
                    placeholder='Enter last name'
                    type='text'
                    value={employee.lastName}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Birth date'
                    name='birthday'
                    placeholder='Enter birth date'
                    type='date'
                    value={employee.birthday}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Contact number'
                    name='contactNo'
                    placeholder='Enter contact no'
                    type='number'
                    value={employee.contactNo}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Address'
                    name='address'
                    placeholder='Enter address'
                    type='text'
                    value={employee.address}
                    onChange={this.handleOnInputChange}
                />
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        );
    }
    componentDidMount() {
        const employee = { ...this.props.initialValue };
        this.setState({ employee })
    }
}

export default EmployeeForm;