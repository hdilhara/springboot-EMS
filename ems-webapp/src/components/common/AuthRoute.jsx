import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';
import { isLogedIn } from '../../services/loginService';
import Forbidden from './Forbidden';

const AuthRoute = (props) => {

    let isLogged = isLogedIn();

    if (isLogged)
        return <Route {...props} path={props.path} render={props => <component {...props} />} />
    else
        return <Redirect to={'/403/Looks Like You are not Loggedin'} />
    // return <Route {...props} path={props.path} component={() => <Forbidden msg="Looks Like You're Not Loggedin..." />} />

    // return (
    //     <Route {...props} path={props.path} { isLogedIn()? component=props.component: <h1>Please Login First</h1>} /> 
    //     // <React.Fragment>
    //     //     { isLogedIn() ? props.component : <h1>Please Login first</h1>}
    //     // </React.Fragment>
    // );
}

export default AuthRoute;