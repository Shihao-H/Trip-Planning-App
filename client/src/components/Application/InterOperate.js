import {Col, Input, Card, CardBody} from "reactstrap";
import React, {Component} from 'react'

class InterOperate extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Card>
                <CardBody>
                    <p>Select another team's site.</p>
                        Host name:<br/>
                        <Input
                            style={{width: "100%"}}
                            type="text"
                            placeholder="black-bottle"
                            onChange={this.props.updateHost}
                        />
                        <br/>
                        Port:<br/>
                        <Input
                            style={{width: "100%"}}
                            type="text"
                            placeholder="31403"
                            onChange={this.props.updateOtherTeams}
                        />
                </CardBody>
            </Card>
        )
    };
}

export default InterOperate;