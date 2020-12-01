import React, { useEffect, useState } from 'react';
import backgroundImg from '../assets/home.jpg';
import { isLogedIn, getUser } from '../services/loginService';

const Home = () => {
    const [isLoged, setIsLoged] = useState(false);

    useEffect(() => {
        console.log('loged?' + isLogedIn())
        setIsLoged(isLogedIn());
    });

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6">
                    <img className="home-background-img" src={backgroundImg} />
                </div>
                <div className="col-md-6 ">
                    <div className="home-content">
                        <h1>EMS</h1>
                        <h3>Employee Mangement System</h3>
                        <div>
                            <button className="btn btn-outline-dark home-content-btn">
                                Get Started
                            </button>
                        </div>
                        <p>
                            This is dummy content.This is dummy content.This is dummy content.This is dummy content.
                            This is dummy content.This is dummy content.This is dummy content.This is dummy content.
                            This is dummy content.This is dummy content.This is dummy content.This is dummy content.
                            This is dummy content.This is dummy content.This is dummy content.This is dummy content.
                            This is dummy content.This is dummy content.This is dummy content.This is dummy content.
                        </p>

                    </div>
                </div>
            </div>
        </div>
    );
}


const styles = {
    homeBackground: {
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center center',
        backgroundColor: '#a4ede66d',
        backgroundImage: 'url(' + backgroundImg + ')',
        backgroundBlendMode: 'multiply',
        color: 'black',
        fontFamily: 'Arial, Helvetica, sans-serif',
        height: '100vh',

        flexDirection: 'column',
        alignItems: 'center',
        display: 'flex',
        alignItems: 'center'
    },
    overlay: {
        width: '100%',
        height: '100%',
        backgroundColor: 'rgba(0, 0, 0, 0.4)'
    },
    mainDiv: {
        marginTop: '17%',
        textAlign: 'center',
        color: 'white'
    },
    fontSizePrimary: {
        fontSize: '4.5rem'
    },
    fontSizeSecondary: {
        fontSize: '3rem'
    },
    loginButton: {
        marginTop: '40px',
        fontSize: '25px'
    }
    // background: {
    //     // backgroundImage: "linear-gradient(rgba(0, 0, 0, 0.45), rgba(0, 0, 0, 0.45)),url(" + backgroundImg + ")",

    //     display: 'flex',
    //     justifyContent: 'center',
    //     alignItems: 'center',
    //     backgroundRepeat: 'no-repeat',
    //     backgroundSize: 'cover',
    //     backgroundAttachment: 'fixed',
    //     height: '95vh'
    // }
}

export default Home;