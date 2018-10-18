import React, { Component } from 'react';
import { Button, Input,Col} from 'reactstrap';

export class Add extends Component {
    constructor(props) {
        super(props);
        this.state = {
            place: {
                title:"",
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
        let copy={
            title:this.state.place.title,
            id:this.state.place.id,
            name:this.state.place.name,
            latitude:this.state.place.latitude,
            longitude:this.state.place.longitude
        }
        let temp=this.props.trip.places;
        temp.push(copy);
        this.props.updateTrip('places',temp);
    }

    updatePlaces(field,value) {
        let place = this.state.place;
        if(field==='latitude'|field==='longitude'){
            value=parseFloat(value);
            place[field] = value;
        }
        else {
            place[field] = value;
        }
        this.setState(place);

    }

    render() {
        return (
            <div>
                        <Col>
                            <p> Add Your Own </p>
                            <Input type="text" placeholder="title ex.lol"
                                   onChange={(e) => this.updatePlaces('title', e.target.value)}/>
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
