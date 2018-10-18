import React, { Component } from 'react';
import {Button,Input,Col,Card,CardBody} from 'reactstrap';

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
            <Card>
                <CardBody>
                        <p> Add Your Own </p>
                        <Input type="text" placeholder="Trip Title"
                               onChange={(e) => this.updatePlaces('title', e.target.value)}/>
                        <Input type="text" placeholder="Trip ID"
                               onChange={(e) => this.updatePlaces('id', e.target.value)}/>
                        <Input type="text" placeholder="Place eg:Denver"
                               onChange={(e) => this.updatePlaces('name', e.target.value)}/>
                        <Input type="text" placeholder="Latitude eg:39.12"
                               onChange={(e) => this.updatePlaces('latitude', e.target.value)}/>
                        <Input type="text" placeholder="Longitude eg:127.23"
                               onChange={(e) => this.updatePlaces('longitude', e.target.value)}/>
                        <br/>
                        <Button
                            size='lg'
                            className='btn-dark btn-outline-dark'
                            type={"button"}
                            onClick={this.addPlace}
                        >Add Place</Button>
                    <br/>
                </CardBody>
            </Card>
        )
    }
}

export default Add;
