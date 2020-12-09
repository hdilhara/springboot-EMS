import React from 'react';

const AppSelectOption = (props) => {

    const { name, title, values, disVal1, disVal2, disVal3, onChanegeHandeler } = props;

    return (
        <select onChange={onChanegeHandeler} style={styles.appDropdown} name={name} className="custom-select" id="inputGroupSelect01">
            <option selected>Choose {title}...</option>
            { values ? values.map(e => <option value={e[name]}>{e[disVal1] + ' ' + e[disVal2] + ' ' + e[disVal3]}</option>) : null}
        </select>
    );
}

const styles = {
    appDropdown: {
        width: '50%',
        minWidth: '350px',
        maxWidth: '350px'
    }
}
export default AppSelectOption;