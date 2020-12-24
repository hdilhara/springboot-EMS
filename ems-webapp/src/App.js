import logo from './logo.svg';
import './App.css';
import NavigationBar from './components/NavigationBar';

import { Switch, Route, Redirect } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import './components/common/css/AppStyles.css';
import './components/common/css/ErrorPage.css';

import Employee from './components/Employee';
import Project from './components/Project';
import Task from './components/Task';
import Home from './components/Home';
import Operations from './components/Operations';
import EmployeeOperations from './components/EmployeeOperations';
import ProjectOperations from './components/ProjectOperations';
import TaskOperations from './components/TaskOperations';
import AssignTasks from './components/AssignTasks';
import UnAssignTask from './components/UnAssignTask';
import PageNotFound from './components/common/PageNotFound';
import ViewProjectsTasks from './components/ViewProjectsTasks';
import AppFooter from './components/common/AppFooter';

import { isLogedIn } from './services/loginService';
import AuthRoute from './components/common/AuthRoute';
import Forbidden from './components/common/Forbidden';
import About from './components/About';


function App() {
  return (
    <div >
      <div className="app-body">
      <NavigationBar></NavigationBar>

      <Switch>
        {/* <Route path="/employee" component={Employee} /> */}
        <AuthRoute path="/employee" component={Employee} />
        <AuthRoute path="/project" component={Project} />
        <AuthRoute path="/task" component={Task} />
        <AuthRoute path="/view/:empId" component={ViewProjectsTasks} />
        <Route path="/about" component={About} />
        <AuthRoute path="/operations/employee" component={EmployeeOperations} />
        <AuthRoute path="/operations/project" component={ProjectOperations} />
        <AuthRoute path="/operations/task" component={TaskOperations} />
        <AuthRoute path="/operations/assign-task" component={AssignTasks} />
        <AuthRoute path="/operations/unassign-task" component={UnAssignTask} />
        <AuthRoute path="/operations" component={Operations} />
        //error page routes
        <Route path="/404" component={PageNotFound} />
        <Route path="/403/:msg" component={Forbidden} />
        <Route path="/403" component={Forbidden} />
        // home route
        <Route path="/" exact component={Home} />
        //redirect
        <Redirect to="/404" />
      </Switch>
      </div>

      <AppFooter></AppFooter>
    </div>
  );
}


export default App;
