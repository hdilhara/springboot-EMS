import React, { Component } from 'react';

class Table extends Component {
    state = {}

    render() {
        const { headers, values, rowKeyProName } = this.props;


        return (
            <table className="table">
                <thead>
                    <tr>
                        {headers.map(h => <th scope="col">{h}</th>)}
                    </tr>
                </thead>
                <tbody>
                    {values.map(v => {
                        return (
                            <tr>
                                { Object
                                    .values(v)
                                    .map(r => <td key={r[rowKeyProName]} >{r}</td>)}
                            </tr>)
                    })}
                </tbody>
            </table>

        );
    }
}

export default Table;