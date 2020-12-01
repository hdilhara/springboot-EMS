import React, { Component } from 'react';
import AppInput from './AppInput';

class TaskForm extends Component {
    state = {
        task: {
            taskId: '',
            taskTitle: '',
            description: '',
            asignDate: '',
            dueDate: '',
        }
    }

    handleOnInputChange = e => {
        const task = { ...this.state.task };
        task[e.currentTarget.name] = e.currentTarget.value;
        this.setState({ task });
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.initialHandeler(this.state.task)
    }

    render() {
        const { task } = this.state;

        return (
            <form onSubmit={this.handleSubmit} >
                <AppInput
                    label='Task title'
                    name='taskTitle'
                    placeholder='Enter title'
                    type='text'
                    value={task.taskTitle}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Description'
                    name='description'
                    placeholder='Enter description'
                    type='text'
                    value={task.description}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Assign date'
                    name='asignDate'
                    placeholder='Enter assign date'
                    type='date'
                    value={task.asignDate}
                    onChange={this.handleOnInputChange}
                />
                <AppInput
                    label='Due date'
                    name='dueDate'
                    placeholder='Enter due date'
                    type='date'
                    value={task.dueDate}
                    onChange={this.handleOnInputChange}
                />
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        );
    }
    componentDidMount() {
        const task = { ...this.props.initialValue };
        this.setState({ task })
    }
}

export default TaskForm;