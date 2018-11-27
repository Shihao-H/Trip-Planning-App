import React, { Component } from 'react';
import {Button, Input, Col, Card, CardBody, ButtonGroup, Collapse, Row, FormGroup} from 'reactstrap';
import {request} from "../../api/api";

export class DistanceCal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            distances: {
                type          : "distance",
                version       : 5,
                unitName      :"",
                unitRadius    :0.0,
                origin        : {latitude:"",
                    longitude:"",
                },
                destination   : {latitude:"",
                    longitude:"",
                },
                units         : "miles",
                distance      : 0
            },
            collapse: false,
            ifDisplayUserDefinedInputFields: false,
        };
        this.updateLoc = this.updateLoc.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.Calculate= this.Calculate.bind(this);
        this.clickUnit= this.clickUnit.bind(this);
        this.dropdown = this.dropdown.bind(this);
        this.Display = this.Display.bind(this);
        this.unitButtGroup = this.unitButtGroup.bind(this);
        this.inputGroup = this.inputGroup.bind(this);
        this.displayUserOptions = this.displayUserOptions.bind(this);
    }

    updateLoc(field,value,origin) {
        if(origin===false){
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

    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }

    clickUnit(event){
        this.updateDistance('units', event.target.value);
        if(event.target.value === 'user defined'){
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    Display()
    {
        if(this.state.distances.unitName!=="")
            return this.state.distances.unitName;
        else
            return this.state.distances.units;
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
            <Input type="text" placeholder="Origin ex.lat"
                   onChange={(e) => this.updateLoc('latitude', e.target.value,false)}/>
            <Input type="text" placeholder="Origin ex.lon"
                   onChange={(e) => this.updateLoc('longitude', e.target.value, false)}/>
            <Input type="text" placeholder="Des ex.lat"
                   onChange={(e) => this.updateLoc('latitude', e.target.value,true)}/>
            <Input type="text" placeholder="Des ex.lat"
                   onChange={(e) => this.updateLoc('longitude', e.target.value,true)}/>
            <Button type={"button"} onClick={this.Calculate}>Calculate</Button>
            <p>{"Final distance " + this.state.distances.distance + " "}{this.Display()}</p>
        </Col>
    }

    displayUserOptions() {
        return <form>
            <FormGroup>
                <label>
                    Unit Name:
                </label>
                <Input type="text" placeholder="Enter unit name" onChange={event =>
                {this.updateDistance('unitName', event.target.value)}}
                />
            </FormGroup>
            <FormGroup>
                <label>Unit Radius: </label>
                <Input type="text" placeholder="Enter unit radius"
                       onChange={event => {this.updateDistance('unitRadius', event.target.value)}}
                />
            </FormGroup>
        </form>
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <Button onClick={this.dropdown}>Calculate Your Own</Button>
                        <Collapse isOpen = {this.state.collapse}>
                            <Row>
                                {this.inputGroup()}
                                <Col md={6}>
                                    <ButtonGroup size="lg" vertical>
                                        {this.unitButtGroup()}
                                    </ButtonGroup>
                                    <p>{' '}</p>
                                    {this.state.ifDisplayUserDefinedInputFields && (this.displayUserOptions()) }
                                </Col>
                            </Row>
                        </Collapse>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default DistanceCal;
