import React, { Component } from 'react';
import AppInput from './AppInput';

class ProjectForm extends Component {
    state = {
        project: {
            proId: '',
            proName: '',
            country: '',
            companyName: '',
            startDate: '',
            endDate: ''

        }
    }

    handleOnInputChange = e => {
        const project = { ...this.state.project };
        project[e.currentTarget.name] = e.currentTarget.value;
        this.setState({ project });
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.initialHandeler(this.state.project)
    }

    render() {
        const { project } = this.state;

        return (
            <form onSubmit={this.handleSubmit} >
                <AppInput
                    label='Project Name'
                    name='proName'
                    placeholder='Enter project name'
                    type='text'
                    value={project.proName}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Country'
                    name='country'
                    placeholder='Enter country'
                    type='text'
                    value={project.country}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Company Name'
                    name='companyName'
                    placeholder='Enter company name'
                    type='text'
                    value={project.companyName}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Start Date'
                    name='startDate'
                    placeholder='Enter start date'
                    type='date'
                    value={project.startDate}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='End Date'
                    name='endDate'
                    placeholder='Enter end date'
                    type='date'
                    value={project.endDate}
                    onChange={this.handleOnInputChange}
                />
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        );
    }
    componentDidMount() {
        const project = { ...this.props.initialValue };
        this.setState({ project })
    }
}

export default ProjectForm;