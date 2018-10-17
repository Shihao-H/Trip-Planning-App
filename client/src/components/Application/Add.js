import React, { Component } from 'react';
import { Button, Input,Col} from 'reactstrap';
import {request} from "../../api/api";

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
        // event.preventDefault();
        console.log("Before " ,this.props.trip);
        let copy={
            title:this.state.place.title,
            unit:this.state.place.unit,
            id:this.state.place.id,
            name:this.state.place.name,
            latitude:this.state.place.latitude,
            longitude:this.state.place.longitude
        }
        let temp=this.props.trip.places;
        temp.push(copy);
        this.props.updateTrip('places',temp);
        // this.props.updateTrip('title', "ABC");
        // this.props.updateOptions('units', "miles");
        // // if(obj.options.units==="user defined")
        // // {
        // //     this.props.updateOptions('unitRadius',obj.options.unitRadius);
        // //     this.props.updateOptions('unitName',obj.options.unitName);
        // // }
        // // this.props.updateTrip('places', obj.places);
        //this.props.updateTrip('map', '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>');
        // request(this.props.trip,'plan').then((Fi)=>
        // {
        //     this.props.updateTrip('distances',Fi.distances);
        // });
        // console.log("LOL " ,this.props.trip);


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
                            {/*<Input type="text" placeholder="title"*/}
                                   {/*onChange={(e) => this.updatePlaces('longitude', e.target.value)}/>*/}
                            {/*<Input type="text" placeholder="unit"*/}
                                   {/*onChange={(e) => this.updatePlaces('longitude', e.target.value)}/>*/}
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