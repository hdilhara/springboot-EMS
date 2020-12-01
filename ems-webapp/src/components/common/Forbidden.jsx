import React, { useEffect } from 'react';
import img from '../../assets/403.png';



const Forbidden = (props) => {
    let msg = null;
    if (props.match.params.msg) {
        msg = props.match.params.msg;
    }
    useEffect(
        () => {
            setTimeout(() => {
                props.history.push('/');
            }, 1000);
        }
        , []);

    return (
        <div>
            {msg ? <div className="alert alert-danger app-alert"> {msg} </div> : null}
            <div className="align-center">
                <img className="back-img1" src={img} />
            </div>
        </div>
    );
}

export default Forbidden;