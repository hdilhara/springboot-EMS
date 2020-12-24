import Axios from 'axios';
import { employeeApiUrl, projectAPIurl, taskAPIurl, empProTaskAPIurl } from '../config/config';



function getToken() {
    console.log("xxxxxxxx")
    let token = localStorage.getItem('token');
    if (token) {
        return 'Bearer ' + token;
    }
    return null;
}

export const authAxios = Axios.create({
});


authAxios.interceptors.request.use(
    (config) => {
        config.headers.Authorization = getToken();
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
authAxios.interceptors.response.use(
    (response) =>
        new Promise(
            (resolve, reject) => {
                resolve(response);
            }
        )
    ,
    (error) => {
        if (error.response.status === 403) {
            window.location = "/403";
        }
    }
);

export function getEmployees(callBack) {
    authAxios.get(employeeApiUrl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getProjects(callBack) {
    authAxios.get(projectAPIurl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getProjectsByIds(ids, callBack) {
    console.log(">>>>.///j")
    console.log(ids)
    ids = ids.join(',');
    authAxios.get(projectAPIurl+'ids/' + ids)//(projectAPIurl + 'ids / ' + ids)
        .then(res => callBack(res.data))//callBack(res.data))
        .catch(e => console.log(e));
}
export function getTasks(callBack) {
    authAxios.get(taskAPIurl)
        .then(res => callBack(res.data))
        .catch(e => console.log(e));
}
export function getTasksByIds(ids, callBack) {
    console.log("sdsdsds>>>>" + ids)
    ids = ids.join(',');
    console.log('dsfsdgfdgfhfghfghfghfghfgh')
    console.log(taskAPIurl+'ids/' + ids)
    authAxios.get(taskAPIurl+'ids/' + ids)//(projectAPIurl + 'ids / ' + ids)
        .then(res => callBack(res.data))//callBack(res.data))
        .catch(e => console.log(e));
}
export function addEmpProTask(value, callBack) {
    authAxios.post(empProTaskAPIurl, value)
        .then(res => callBack('success'))
        .catch(e => console.log(e));
}
export function removeEmpProTask(value, callBack) {
    authAxios.post(empProTaskAPIurl + "remove", value)
        .then(res => callBack('success'))
        .catch(e => console.log(e));
}
export function getEmpProTaskByEID(empId, callBack) {
    console.log(empProTaskAPIurl + empId)
    authAxios.get(empProTaskAPIurl + empId)
        .then(res => { callBack(res.data) })
        .catch(e => console.log(e));
}
export function getEmpProTaskByEIDPID(empId, proId, callBack) {
    authAxios.get(empProTaskAPIurl + 'eid/pid/' + empId + '/' + proId)
        .then(res => {
            let taskIds = [];
            for (let x of res.data) {
                taskIds.push(x.id.taskId)
            }
            callBack(taskIds);
        })
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