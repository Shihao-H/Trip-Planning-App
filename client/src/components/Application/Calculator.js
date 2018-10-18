import React, {Component} from 'react'
import { Card, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'
import { Row, Col } from "reactstrap";
import {request} from "../../api/api";

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Calculator extends Component{
    constructor(props) {
        super(props);

        this.state = {
            unitsConfig: ['miles', 'kilometers', 'nautical miles', 'user defined'],
            ifDisplayUserDefinedInputFields: false,
            distance:{
                type: 'distance',
                version: 3,
                origin: {
                    id: "ori",
                    name: "origin",
                    latitude: 0,
                    longitude: 0
                },
                destination: {
                    id: "des",
                    name: "destination",
                    latitude: 0,
                    longitude: 0
                },
                units: null,
                unitName: null,
                unitRadius: 0,
                distance: 0
            }
        };

        this.clickCalculateButton = this.clickCalculateButton.bind(this);
        this.clickUnitButton = this.clickUnitButton.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.updateOrigin = this.updateOrigin.bind(this);
        this.updateDestination = this.updateDestination.bind(this);

    }

    updateDistance(field, value){
        let distance = this.state.distance;
        distance[field] = value;
        this.setState(distance);
    }

    updateOrigin(field, value){
        let distance = this.state.distance;
        distance.origin[field] = value;
        this.setState(distance);
    }

    updateDestination(field, value){
        let distance = this.state.distance;
        distance.destination[field] = value;
        this.setState(distance);
    }

    clickCalculateButton(event){
        let distance = this.state.distance;
        request(distance,'distance').then((Result)=>
        {
            this.props.updateTrip('distances',Result.distances);
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
                active={this.state.distance.units === unit}
                value={unit}
                onClick={this.clickUnitButton}
            >
                {unit.charAt(0).toUpperCase() + unit.slice(1)}
            </Button>
        );


        return(
            <Card>
                <CardBody>
                    <p>This is a calculator calculates the distance from one place to another place:</p>
                    <Row>
                    <Col>
                    <form>
                        <label>From:</label>
                        <input type="text"
                               placeholder="latitude"
                               style={{width: 100}}
                               onChange={(event) => this.updateOrigin('latitude', event.target.value)}
                        />
                        <input type="text"
                               placeholder="longitude"
                               style={{width: 100}}
                               onChange={(event) => this.updateOrigin('longitude', event.target.value)}
                        />
                        <br/>
                        <label>To:</label>
                        <input type="text"
                               placeholder="latitude"
                               style={{width: 100}}
                               onChange={(event) => this.updateDestination('latitude', event.target.value)}
                        />
                        <input type="text"
                               placeholder="longitude"
                               style={{width: 100}}
                               onChange={(event) => this.updateDestination('longitude', event.target.value)}
                        />
                    </form>
                    </Col>
                    <Col>
                    <ButtonGroup vertical>
                        {buttons}
                    </ButtonGroup>
                    {this.state.ifDisplayUserDefinedInputFields && (
                        <form>
                            <label>Unit name:</label>
                            <input type="text"
                                   placeholder="Unit name"
                                   style={{width: 100}}
                                   onChange={(event) => this.updateDistance("unitName", event.target.value)}
                            />
                            <label>Unit radius:</label>
                            <input type="text"
                                   placeholder="Unit radius"
                                   style={{width: 100}}
                                   onChange={(event) => this.updateDistance("unitRadius", event.target.value)}
                            />
                        </form>)
                    }
                    </Col>
                    </Row>
                    <br/>
                    <Button
                        key={'calculate_button'}
                        className='btn-outline-dark calculate-button'
                        onClick={this.clickCalculateButton}
                    >
                        Calculate!
                    </Button>
                    <input type="text"
                           placeholder="distance"
                           style={{width: 100}}
                           onChange={(event) => this.updateDistance("distance", event.target.value)}
                    />
                </CardBody>
            </Card>
        );
    }
}

export default Calculator;