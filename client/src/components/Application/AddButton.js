import React, {Component} from 'react'
import {Button} from "reactstrap";


export class AddButton extends Component {
    constructor(props)
    {
        super(props);
        this.addNewPlace = this.addNewPlace.bind(this);
    }

    addNewPlace()
    {
        let newPlace = this.props.newPlace;
        let places = this.props.trip.places;

        let dummy = this.props.trip.distances;
        dummy.push(0);
        
        places.push(newPlace);
        this.props.updateTrip('places', places);

    }

    render()
    {
        return (
            <Button
                onClick={this.addNewPlace}
                className="btn-outline-dark"
                size="sm"
            >
                Add
            </Button>
        )
    }
}
export default AddButton;
