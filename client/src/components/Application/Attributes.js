import {Col, Input, Card, CardBody} from "reactstrap";
import React, {Component} from 'react'

class Attributes extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
                <Card>
                    <CardBody>
                            <p>Select attributes to display.</p>
                        <Col>
                            <form>
                                <Input type="checkbox" id={"check_name"} checked={this.props.display.Name} value={'Name'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Name </label><br/>
                                <Input type="checkbox" id={"check_id"} checked={this.props.display.Id} value={'Id'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Id </label><br/>
                                <Input type="checkbox" id={"check_latitude"} checked={this.props.display.Latitude} value={'Latitude'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Latitude </label><br/>
                                <Input type="checkbox" id={"check_longitude"} checked={this.props.display.Longitude} value={'Longitude'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Longitude </label><br/>
                                <Input type="checkbox" id={"check_leg"} checked={this.props.display.Leg} value={'Leg'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Leg distances </label><br/>
                                <Input type="checkbox" id={"check_total"} checked={this.props.display.Total} value={'Total'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <label> Total distances </label><br/>
                                <p>Add an attribute!</p>
                                <Input type="checkbox" id={"check_user"} checked={this.props.display.UserDefinedDisplay} value={'UserDefinedDisplay'}
                                       onChange = {event => {this.props.updateDisplay(event.target.value)}}/>
                                <Input
                                    style={{width: "100%"}}
                                    type="text"
                                    placeholder="municipality"
                                    onChange={event => {this.props.updateDisplayUserDefined(event.target.value)}}
                                />
                            </form>
                        </Col>
                    </CardBody>
                </Card>
        )
    };
}

export default Attributes;