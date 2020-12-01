import Axios from 'axios';
import { employeeApiUrl, projectAPIurl, taskAPIurl, empProTaskAPIurl } from '../config/config';

export function getEmployees(callBack) {
    Axios.get(employeeApiUrl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getProjects(callBack) {
    Axios.get(projectAPIurl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getTasks(callBack) {
    Axios.get(taskAPIurl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function addEmpProTask(value, callBack) {
    Axios.post(empProTaskAPIurl, value)
        .then(res => callBack('success'))
        .catch(e => console.log(e));
}
export function removeEmpProTask(value, callBack) {
    Axios.post(empProTaskAPIurl + "remove", value)
        .then(res => callBack('success'))
        .catch(e => console.log(e));
}
export function getEmpProTaskByEID(value, callBack) {
    console.log(empProTaskAPIurl + value)
    Axios.get(empProTaskAPIurl + value)
        .then(res => { console.log(value); callBack(res.data) })
        .catch(e => console.log(e));
}
// class EmployeeService {

//     constructor(setState) {
//         setState = setState;
//     }

//     static saveEmployee = (value) => {
//         Axios
//             .post(updateEmployee, value)
//             .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
//             .catch(err => console.log(err));
//     }
//     static updateEmployee = (value) => {
//         Axios
//             .put(updateEmployee, value)
//             .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
//             .catch(err => console.log(err));
//     }
//     static deleteEmployee = (value) => {
//         Axios.delete(deleteEmployee + value.empId)
//             .then(res => { this.loadValues() });
//     }

//     static loadValues() {
//         Axios.get(getEmployee)
//             .then(res => this.setState({ values: res.data }))
//             .catch(e => console.log(e));
//     }

// }

// export default EmployeeService;

// export function saveEmployee(value) {
//     Axios
//         .post(updateEmployee, value)
//         .then(res => { this.setState({ modalShow: false }); this.loadValues(); console.log(res); })
//         .catch(err => console.log(err));
// }