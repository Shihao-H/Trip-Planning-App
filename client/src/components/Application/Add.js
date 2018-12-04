import React, {Component} from 'react';
import {ButtonGroup, Button, Input, Card, CardBody, FormGroup, Label} from 'reactstrap';

export class Add extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            advanced: false,
            place: {
                id: "", name: "", latitude: 0.00, longitude: 0.00,
                type: "", elevation: "", continent: "", country: "",
                region: "", municipality: ""
            }
        };
        this.addPlace = this.addPlace.bind(this);
        this.updatePlaces = this.updatePlaces.bind(this);
        this.generateInput = this.generateInput.bind(this);
        this.advancedOptions = this.advancedOptions.bind(this);
        this.click = this.click.bind(this);
    }

    addPlace() {
        let copy = {
            id: this.state.place.id,
            name: this.state.place.name,
            latitude: this.state.place.latitude,
            longitude: this.state.place.longitude,
            type: this.state.place.type,
            elevation: this.state.place.elevation,
            continent: this.state.place.continent,
            country: this.state.place.country,
            region: this.state.place.region,
            municipality: this.state.place.municipality
        };
        let temp = this.props.places;
        temp.push(copy);
        this.props.updateTrip('places', temp);
    }

    updatePlaces(field, value) {
        let place = this.state.place;
        if (field === 'latitude' || field === 'longitude') {
            value = parseFloat(value);
            place[field] = value;
        }
        else {
            place[field] = value;
        }
        this.setState(place);
    }

    generateInput(option) {
        return (
            <Input type="text" placeholder={"Place " + option}
                   onChange={(e) => this.updatePlaces(option, e.target.value)}/>
        )
    }

    advancedOptions() {
        return (
            <FormGroup>
                {this.generateInput('type')}
                {this.generateInput('elevation')}
                {this.generateInput('continent')}
                {this.generateInput('country')}
                {this.generateInput('region')}
                {this.generateInput('municipality')}
            </FormGroup>
        )
    }

    click(event, bool) {
        bool ? this.setState({advanced: true}) : this.setState({advanced: false});
    }

    generateBasicButtons(bool) {
        //const style = {backgroundColor: "034f03"};
        return (
            <Button key={'add_button_' + bool}
                    className='btn-outline-dark add-button'
                    active={this.state.advanced === bool}
                    //style={style}
                    onClick={(event) => this.click(event, bool)}>
                {bool ? "Advanced" : "Basic"}
            </Button>
        )
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <Label>Create your own trip</Label>
                        <Input type="text" placeholder="Trip Title"
                               onChange={(e) => this.props.updateTrip('title', e.target.value)}/><br/>
                        <ButtonGroup>
                            {this.generateBasicButtons(false)}
                            {this.generateBasicButtons(true)}
                        </ButtonGroup><br/><br/>
                        {this.generateInput('id')}
                        {this.generateInput('name')}
                        {this.generateInput('latitude')}
                        {this.generateInput('longitude')}
                        <br/>
                        {this.state.advanced && this.advancedOptions()}
                        <Button className='btn-dark btn-outline-dark'
                                onClick={this.addPlace}>Add Place</Button><br/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default Add;
