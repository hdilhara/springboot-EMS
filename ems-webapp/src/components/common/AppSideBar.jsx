import React, { useState } from 'react';
import { propTypes } from 'react-bootstrap/esm/Image';

const AppSideBar = (props) => {

    const [active, setActive] = useState('first');

    const { values, handleClick } = props;

    return (
        <ul className="list-group">
            {values.map(val => <li style={styles.select} key={val} onClick={() => { handleClick(val); setActive(val) }} className={(active === val) ? 'list-group-item active' : 'list-group-item'}>{val}</li>)}
        </ul>
    );
}
const styles = {
    select: {
        cursor: 'pointer'
    }
}
export default AppSideBar;