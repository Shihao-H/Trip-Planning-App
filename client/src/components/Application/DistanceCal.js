import React, { Component } from 'react';
import { Button, Input,Col,Card,CardBody} from 'reactstrap';
import {request} from "../../api/api";

export class DistanceCal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            // collapse:false,
            distances: {
                type          : "distance",
                version       : 3,
                unitName      :"",
                unitRadius    :0.0,
                origin        : {latitude:"",
                                longitude:"",
                                },
                destination   : {latitude:"",
                                longitude:"",
                                },
                units         : "miles",
                distance      : 0
            }
        }
        this.updateLoc = this.updateLoc.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.Calculate= this.Calculate.bind(this);
        // this.updateUnit = this.updateUnit.bind(this);

    }
    // updateUnit()
    // {
    //     let distances=this.state.distances;
    //     let option=this.props.options
    // }

    updateLoc(field,value,origin) {
        if(origin===false){
            let distances = this.state.distances.origin;
            distances[field] = value;
            this.setState(distances);
        }
        else
        {
            let distances = this.state.distances.destination;
            distances[field] = value;
            this.setState(distances);
        }
    }

    updateDistance(field,value)
    {
        let obj = this.state.distances;
        obj[field] = value;
        this.setState(obj);
    }

    Calculate()
    {
        this.updateUnit();
        // let obj=this.state.distances;
        console.log("Origin ",this.state.distances);
        // console.log("Destination ",this.state.distances.destination);
        request(this.state.distances,'distance').then((response)=>
        {
            console.log("LOL ",response);
            this.updateDistance('distance',response.distance);
        });
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                <Col>
                    <p> Calculate Your Own </p>
                    <Input type="text" placeholder="Origin ex.lat"
                           onChange={(e) => this.updateLoc('latitude', e.target.value,false)}/>
                    <Input type="text" placeholder="Origin ex.lon"
                           onChange={(e) => this.updateLoc('longitude', e.target.value, false)}/>
                    <Input type="text" placeholder="Des ex.lat"
                           onChange={(e) => this.updateLoc('latitude', e.target.value,true)}/>
                    <Input type="text" placeholder="Des ex.lat"
                           onChange={(e) => this.updateLoc('longitude', e.target.value,true)}/>
                    <Button type={"button"} onClick={this.Calculate}>Calculate</Button>
                    <p>{"Final Distance " + this.state.distances.distance }</p>
                </Col>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default DistanceCal;