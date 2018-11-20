import React, { Component } from 'react';
import {Button,Input,Card,CardBody} from 'reactstrap';

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
            };
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
        };
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
            <div className={'text-center'}>
                <Card>
                    <CardBody>
                        <p>Add your own location</p>
                        <Input type="text" placeholder="Trip Title"
                               onChange={(e) => this.updatePlaces('title', e.target.value)}/>
                        <Input type="text" placeholder="Place ID"
                               onChange={(e) => this.updatePlaces('id', e.target.value)}/>
                        <Input type="text" placeholder="Place name: Denver"
                               onChange={(e) => this.updatePlaces('name', e.target.value)}/>
                        <Input type="text" placeholder="Latitude: 39.12"
                               onChange={(e) => this.updatePlaces('latitude', e.target.value)}/>
                        <Input type="text" placeholder="Longitude: 127.23"
                               onChange={(e) => this.updatePlaces('longitude', e.target.value)}/>
                        <br/>
                        <Button size='lg' className='btn-dark btn-outline-dark'
                            type={"button"} onClick={this.addPlace}>Add Place</Button><br/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default Add;
