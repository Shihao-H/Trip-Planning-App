import React, { Component } from 'react';
import { Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle, Button } from 'reactstrap';
import colorado from '../../../../images/CObackground.svg';



export class Map extends Component {
    render() {
        return (
            <div>
                <Card>
                    <CardImg  width="75%" src={colorado} alt={"Map of Colorado"}/>
                </Card>
            </div>)
    }
}
