import React, { Component } from 'react';
import {Card, CardBody, Button, Input} from 'reactstrap';

export class Add extends Component {
    constructor(props)
    {
        super(props);
        this.addPlace = this.addPlace.bind(this);
    }
    addPlace()
    {
        console.log("LOL");
    }
render() {
    return (
        <div>
                <Card>
                    <CardBody>
                            <Col>
                                <p> Add Your Own </p>
                                <Input type="text" placeholder="Id lol" onChange={(e)=>this.updatePlaces('id', e.target.value)}/>
                                <Input type="text" placeholder="Place mariana trench" onChange={(e)=>this.updatePlaces('name', e.target.value)}/>
                                <Input type="text" placeholder="Latitude ex.39.12" onChange={(e)=>this.updatePlaces('latitude', e.target.value)}/>
                                <Input type="text" placeholder="Longitude  ex.127.23" onChange={(e)=>this.updatePlaces('longitude', e.target.value)}/>
                                <br/>
                                <Button type={"button"} onClick={this.addPlace}>Add Place</Button>
                            </Col>
                        <br/>
                    </CardBody>
                </Card>
        </div>
    )
}