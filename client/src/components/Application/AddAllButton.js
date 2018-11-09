import React, {Component} from 'react'
import {Button} from "reactstrap";


export class AddAllButton extends Component {
    constructor(props)
    {
        super(props);
        this.addAllPlaces = this.addAllPlaces.bind(this);
    }

    addAllPlaces()
    {
        let places = this.props.trip.places;
        let dummy = this.props.trip.distances;

        for (let i = 0; i < this.props.search.places.length; i++) {
            places.push(this.props.search.places[i]);
            dummy.push(0);
        }

        this.props.updateTrip('places', places);
    }

    render()
    {
        return (
            <Button
                onClick={this.addAllPlaces}
                className="btn-outline-dark"
                size="sm">
                Add all to trip
            </Button>
        )
    }
}
export default AddAllButton;
