import React, {Component} from 'react'
import {Button, ButtonGroup, Card, CardBody, Collapse} from "reactstrap";

class Optimization extends Component{
    constructor(props){
        super(props);
        this.state = {
            collapse: true,
        };
        this.dropdown = this.dropdown.bind(this);
        this.clickButton = this.clickButton.bind(this);
    }

    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }

    clickButton(event){
        console.log("LLL", this.props);
        this.props.updateOptions('optimization', event.target.value);
    }
    render() {
        return (
            <div className="card">
                <CardBody>
                    <Button onClick={this.dropdown} className = 'btn-dark' size='lg'>Optimization</Button>
                    <Collapse isOpen = {this.state.collapse}>
                        <Card>
                            <ButtonGroup vertical>
                                <Button
                                    value={"none"}
                                    onClick={this.clickButton}
                                    >None</Button>
                                <Button
                                    value={"short"}
                                    onClick={this.clickButton}
                                    >Short</Button>
                            </ButtonGroup>
                        </Card>
                    </Collapse>
                </CardBody>
            </div>
        )
    };
}

export default Optimization;