import React from 'react';

const AppInput = (props) => {
    const { label, name, placeholder, type, value, onChange } = props;
    return (
        <div class="form-group">
            <label for={name}>{label}</label>
            <input name={name} value={value} onChange={onChange} type={type} className="form-control" id={name} aria-describedby="emailHelp" placeholder={placeholder} />
            {/* <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small> */}
        </div>
    );
}

export default AppInput;