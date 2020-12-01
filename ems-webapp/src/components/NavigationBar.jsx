import React, { Component } from 'react';
import { Navbar, NavDropdown, Item, Nav, Brand } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { getAuthorities } from '../services/loginService';

import axios from 'axios';



class NavigationBar extends Component {
    state = {
        isLogged: false,
        user: 'Login',
        authorities: null
    }

    handleLogout = () => {
        let token = localStorage.getItem('token')
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        localStorage.removeItem('userDetails');
        this.deleteAllCookies();
        this.setState({ isLogged: false });
        this.setState({ user: 'Login' });
        // window.location.replace('/');
        window.location.replace("http://localhost:9006/logout/me");

    }

    render() {

        const { isLogged, authorities } = this.state;

        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Brand as={Link} to='/'>EMS</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link as={Link} to='/employee'>Employees</Nav.Link>
                        <Nav.Link as={Link} to="/project">Projects</Nav.Link>
                        <Nav.Link as={Link} to="/task">Tasks</Nav.Link>
                    </Nav>
                    <Nav>

                        {isLogged ? (
                            < NavDropdown title={this.state.user} id="collasible-nav-dropdown">
                                {(authorities && authorities.includes('ROLE_ADMIN')) ?
                                    <NavDropdown.Item as={Link} to="/operations/employee">Employee</NavDropdown.Item>
                                    : null
                                }
                                {(authorities && authorities.includes('ROLE_ADMIN')) ?
                                    <NavDropdown.Item as={Link} to="/operations/project">Project</NavDropdown.Item>
                                    : null
                                }
                                {(authorities && authorities.includes('ROLE_ADMIN')) ?
                                    <NavDropdown.Item as={Link} to="/operations/task">Task</NavDropdown.Item>
                                    : null
                                }
                                {(authorities && authorities.includes('ROLE_ADMIN')) ?
                                    <NavDropdown.Item as={Link} to="/operations/assign-task">Assign Task</NavDropdown.Item>
                                    : null
                                }
                                {(authorities && authorities.includes('ROLE_ADMIN')) ?
                                    <NavDropdown.Item as={Link} to="/operations/unassign-task">Unassign Task</NavDropdown.Item>
                                    : null
                                }
                                {/* <NavDropdown.Item as={Link} to="#action/3.2">Another action</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="#action/3.3">Something</NavDropdown.Item> */}
                                <NavDropdown.Divider />
                                {!isLogged ? null : (
                                    <NavDropdown.Item onClick={this.handleLogout}>Logout</NavDropdown.Item>
                                )}
                            </NavDropdown>
                        ) : null
                        }
                        {isLogged ? null : (
                            <Nav.Link eventKey={2} href="http://localhost:9006/oauth/authorize?client_id=client&redirect_uri=http://localhost:9000/login&response_type=code&state=hi4VfM">
                                Login
                            </Nav.Link>
                        )}
                        <Nav.Link eventKey={2} as={Link} to='/about'>
                            About
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar >
        );
    }

    deleteAllCookies() {
        var cookies = document.cookie.split(";");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        }
    }

    getToken() {
        //get token
        var token;
        var arr = document.cookie.split(';');
        for (let s of arr) {
            if (s.includes('token')) {
                token = s.split("=")[1].trim();
            }
        }
        if (token)
            localStorage.setItem('token', token);
        return token;
    }

    getUser() {

        axios.get('http://localhost:9000/user?token=' + this.getToken())
            .then(res => {
                let user = res.data.principal;
                localStorage.setItem("userDetails", JSON.stringify(user));
                this.setState({ user: user.username });
                this.setState({ isLogged: true });
                // localStorage.setItem('userDetails', JSON.stringify(res.data));
                localStorage.setItem('user', user.username);
                this.setState({ authorities: getAuthorities() });
            });
    }

    componentDidMount() {
        if (this.getToken) {
            this.getUser();
        }
    }
}

export default NavigationBar;