import {Col} from "reactstrap";
import React, {Component} from 'react'

class InterOperate extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Col md={6}>
                Host name:<br/>
                <input type="text"
                       placeholder="black-bottle.cs.colostate.edu"
                       onChange={this.props.updateHost}
                />
                <br/>
                Port:<br/>
                <input type="text"
                       placeholder="31403"
                       onChange={this.props.updateOtherTeams}
                />
            </Col>
        )
    };
}

export default InterOperate;