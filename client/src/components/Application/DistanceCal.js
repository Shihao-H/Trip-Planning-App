import React, { Component } from 'react';
import { Button, ButtonGroup, Input, Col, Row, Card, CardBody, FormGroup, InputGroup, InputGroupAddon} from 'reactstrap';
import {request} from "../../api/api";

export class DistanceCal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            unitsConfig: ['miles', 'kilometers', 'nautical miles', 'user defined'],
            ifDisplayUserDefinedInputFields: false,
            distances: {
                type          : "distance",
                version       : 3,
                origin: {
                    id        : "ori",
                    name      : "origin",
                    latitude  : 0,
                    longitude : 0
                },
                destination: {
                    id        : "des",
                    name      : "destination",
                    latitude  : 0,
                    longitude : 0
                },
                units         : null,
                unitName      : null,
                unitRadius    : 0.0,
                distance      : 0
            }
        };
        this.updateLoc = this.updateLoc.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.Calculate= this.Calculate.bind(this);
        this.clickUnitButton = this.clickUnitButton.bind(this);

    }

    updateLoc(field,value,origin) {
        if(origin===true){
            let distances = this.state.distances.origin;
            distances[field] = value;
            this.setState(distances);
        }
        else
        {
            let distances = this.state.distances.destination;
            distances[field] = value;
            this.setState(distances);
        }
    }

    updateDistance(field,value)
    {
        let obj = this.state.distances;
        obj[field] = value;
        this.setState(obj);
    }

    Calculate()
    {
        request(this.state.distances,'distance').then((response)=>
        {
            this.updateDistance('distance',response.distance);
        });
    }

    clickUnitButton(event){
        this.updateDistance('units', event.target.value);
        if(event.target.value === 'user defined'){
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    render() {

        const buttons = this.state.unitsConfig.map((unit) =>
            <Button
                key={'units_button_' + unit}
                className='btn-outline-dark unit-button'
                active={this.state.distances.units === unit}
                value={unit}
                onClick={this.clickUnitButton}
            >
                {unit.charAt(0).toUpperCase() + unit.slice(1)}
            </Button>
        );


        return (
            <div>
                <Card>
                    <CardBody>
                        <Row>
                            <Col md={2}>
                                <p> Calculate Your Own </p>
                                <Input type="text" placeholder="Origin ex.lat"
                                       onChange={(e) => this.updateLoc('latitude', e.target.value,true)}/>
                                <Input type="text" placeholder="Origin ex.lon"
                                       onChange={(e) => this.updateLoc('longitude', e.target.value, true)}/>
                                <Input type="text" placeholder="Des ex.lat"
                                       onChange={(e) => this.updateLoc('latitude', e.target.value, false)}/>
                                <Input type="text" placeholder="Des ex.lat"
                                       onChange={(e) => this.updateLoc('longitude', e.target.value, false)}/>
                                <Button type={"button"} onClick={this.Calculate}>Calculate</Button>
                                <p>{"Final Distance: " + this.state.distances.distance }</p>
                            </Col>
                        <Col md={2}>
                            <ButtonGroup vertical>
                                {buttons}
                            </ButtonGroup>
                            {this.state.ifDisplayUserDefinedInputFields && (
                                <form>
                                    <FormGroup>
                                        <label>
                                            Unit Name:
                                        </label>
                                        <input type="text" placeholder="Enter unit name" onChange={event =>
                                        {this.props.updateOptions('unitName', event.target.value)}}

                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <label>Unit Radius: </label>
                                        <input type="text" placeholder="Enter unit radius"
                                               onChange={event => {this.props.updateOptions('unitRadius', event.target.value)}}
                                        />
                                    </FormGroup>
                                </form>)
                            }
                        </Col>
                        </Row>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default DistanceCal;