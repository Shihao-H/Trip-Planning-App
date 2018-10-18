import React, {Component} from 'react'
import {Button} from "reactstrap";
import {request} from "../../api/api";


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

        places.push(newPlace);
        this.props.updateTrip('places', places);

        request(this.props.trip,'plan').then((Fi)=>
        {
            this.props.updateTrip('distances',Fi.distances);
        });
    }

    render()
    {
        return (
            <Button
                onClick={this.addNewPlace}
                className="btn-outline-dark"
                size="sm"
            >
                Add to trip
            </Button>
        )
    }
}
export default AddButton;
