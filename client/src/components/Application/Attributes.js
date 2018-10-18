import {Col, Input} from "reactstrap";
import React, {Component} from 'react'

class Attributes extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
                <div>
                    <Col>
                        <br/>
                        <p> Please choose attributes to display: </p>
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
                        </form>
                    </Col>
                    <br/>
                </div>
        )
    };
}

export default Attributes;