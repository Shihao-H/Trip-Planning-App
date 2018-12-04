import {Input, Card, CardBody, Button} from "reactstrap";
import React, {Component} from 'react'

class InterOperate extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Card>
                <CardBody>
                    <p>{"Select another team's site."}</p>
                    <p>{"Host name:"}</p>
                    <p>{"black-bottle.cs.colostate.edu"}</p>
                    <Input style={{width: "100%"}} type="text"
                           placeholder="black-bottle.cs.colostate.edu"
                           onChange={this.props.updateHost}/>
                    <br/>
                    <p>{"Port:"}</p>
                    <Input style={{width: "100%"}} type="text"
                           placeholder="31403" onChange={this.props.updateOtherTeams}/>
                    <br/>
                    <Button className="btn-save btn-dark btn-outline-dark"
                            onClick={this.props.updateInterOperate}
                            type="button">{"Begin inter operate!"}</Button>
                </CardBody>
            </Card>
        )
    };
}

export default InterOperate;