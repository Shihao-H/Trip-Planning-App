import React, { Component } from 'react';
import { Button, Input,Col} from 'reactstrap';

export class Add extends Component {
    constructor(props) {
        super(props);
        this.state = {
            place: {
                id: "",
                name: "",
                latitude: 0.00,
                longitude: 0.00
            }
            }
        this.addPlace = this.addPlace.bind(this);
        this.updatePlaces = this.updatePlaces.bind(this);
    }

    addPlace() {
        this.props.TripPushPlace(this.state.place);
        console.log("LOL",this.props.trip);
    }

    updatePlaces(field,value) {
        let place = this.state.place;
        place[field] = value;
        this.setState(place);
    }

    render() {
        return (
            <div>
                        <Col>
                            <p> Add Your Own </p>
                            <Input type="text" placeholder="Id ex.lol"
                                   onChange={(e) => this.updatePlaces('id', e.target.value)}/>
                            <Input type="text" placeholder="Place ex.mariana trench"
                                   onChange={(e) => this.updatePlaces('name', e.target.value)}/>
                            <Input type="text" placeholder="Latitude ex.39.12"
                                   onChange={(e) => this.updatePlaces('latitude', e.target.value)}/>
                            <Input type="text" placeholder="Longitude  ex.127.23"
                                   onChange={(e) => this.updatePlaces('longitude', e.target.value)}/>
                            <br/>
                            <Button type={"button"} onClick={this.addPlace}>Add Place</Button>
                        </Col>
                        <br/>
            </div>
        )
    }
}

export default Add;