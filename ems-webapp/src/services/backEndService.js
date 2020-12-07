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
export function getProjectsByIds(ids, callBack) {
    console.log(ids)
    ids = ids.join(',');
    Axios.get('http://localhost:9000/project/ids/' + ids)//(projectAPIurl + 'ids / ' + ids)
        .then(res => callBack(res.data))//callBack(res.data))
        .catch(e => console.log(e));
}
export function getTasks(callBack) {
    Axios.get(taskAPIurl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getTasksByIds(ids, callBack) {
    console.log("sdsdsds>>>>" + ids)
    ids = ids.join(',');
    console.log('dsfsdgfdgfhfghfghfghfghfgh')
    console.log('http://localhost:9000/task/ids/' + ids)
    Axios.get('http://localhost:9000/task/ids/' + ids)//(projectAPIurl + 'ids / ' + ids)
        .then(res => callBack(res.data))//callBack(res.data))
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
export function getEmpProTaskByEID(empId, callBack) {
    console.log(empProTaskAPIurl + empId)
    Axios.get(empProTaskAPIurl + empId)
        .then(res => { callBack(res.data) })
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