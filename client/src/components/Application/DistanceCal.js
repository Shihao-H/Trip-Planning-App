import React, {Component} from 'react';
import {Button, Input, Col, Card, CardBody, ButtonGroup, CardTitle, Row, FormGroup} from 'reactstrap';
import {request} from "../../api/api";

export class DistanceCal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            distances: {
                type: "distance",
                version: 5,
                unitName: "",
                unitRadius: 0.0,
                origin: {latitude: "", longitude: "",},
                destination: {latitude: "", longitude: "",},
                units: "miles",
                distance: 0
            },
            collapse: false,
            ifDisplayUserDefinedInputFields: false,
        };

        this.updateLoc = this.updateLoc.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.Calculate = this.Calculate.bind(this);
        this.clickUnit = this.clickUnit.bind(this);
        this.Display = this.Display.bind(this);
        this.unitButtGroup = this.unitButtGroup.bind(this);
        this.inputGroup = this.inputGroup.bind(this);
        this.displayUserOptions = this.displayUserOptions.bind(this);
    }

    updateLoc(field, value, origin) {
        if (origin === false) {
            let distances = this.state.distances.origin;
            distances[field] = value;
            this.setState(distances);
        }
        else {
            let distances = this.state.distances.destination;
            distances[field] = value;
            this.setState(distances);
        }
    }

    clickUnit(event) {
        this.updateDistance('units', event.target.value);
        if (event.target.value === 'user defined') {
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    Display() {
        if (this.state.distances.unitName !== "")
            return this.state.distances.unitName;
        else
            return this.state.distances.units;
    }

    updateDistance(field, value) {
        let obj = this.state.distances;
        obj[field] = value;
        this.setState(obj);
    }

    Calculate() {
        request(this.state.distances, 'distance').then((response) => {
            this.updateDistance('distance', response.distance);
        });
    }

    unitButtGroup() {
        return this.props.config.units.map((unit) =>
            <Button
                key={'DistanceCal_button_' + unit}
                className='btn-outline-dark optimization-button'
                active={this.state.distances.units === unit}
                value={unit}
                onClick={this.clickUnit}
            >
                {unit.charAt(0).toUpperCase() + unit.slice(1)}
            </Button>
        );
    }

    inputGroup() {
        return <Col md={6}>
            {this.generateSomeInputForm("Origin ex.latitude", 'latitude', false)}
            {this.generateSomeInputForm("Origin ex.longitude", 'longitude', false)}
            {this.generateSomeInputForm("Destination ex.latitude", 'latitude', true)}
            {this.generateSomeInputForm("Destination ex.longitude", 'longitude', true)}<br/>
            <Button type={"button"} onClick={this.Calculate}
                    color={'dark'}>Calculate</Button><br/><br/>
            <p><b>{"Final distance is " + this.state.distances.distance + " "}{this.Display()}</b></p>
        </Col>
    }

    generateUserDefinedForm(placeHolder, forWhat) {
        return (
            <Input type="text" placeholder={placeHolder}
                   onChange={event => {
                       this.updateDistance(forWhat, event.target.value)
                   }}/>)
    }

    generateSomeInputForm(placeHolder, forWhat, boolean) {
        return (
            <Input type="text" placeholder={placeHolder}
                   onChange={(e) => this.updateLoc(forWhat, e.target.value, boolean)}/>)
    }

    displayUserOptions() {
        return (
            <FormGroup>
                {this.generateUserDefinedForm("Enter unit name", "unitName")}
                {this.generateUserDefinedForm("Enter unit radius", "unitRadius")}
            </FormGroup>
        )
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <CardTitle>Calculate the distance between two locations</CardTitle>
                        <br/>
                        <Row>
                            {this.inputGroup()}
                            <Col md={6}>
                                <ButtonGroup vertical>{this.unitButtGroup()}</ButtonGroup>
                                <p>{' '}</p>
                                {this.state.ifDisplayUserDefinedInputFields && (this.displayUserOptions())}
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default DistanceCal;
