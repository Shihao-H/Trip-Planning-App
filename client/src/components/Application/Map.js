import React, { Component } from 'react';
import {Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button, Collapse} from 'reactstrap';
import colorado from '../../../../images/CObackground.svg';




export class Map extends Component {
    constructor(props)
    {
        super(props);
        this.state = {
            collapse: false,
        };
        this.dropdown = this.dropdown.bind(this);
    }
    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }

    render() {
        return (
            <div>
                <Button onClick={this.dropdown} size='lg'>Map</Button>
                    <Collapse isOpen = {this.state.collapse}>
                        <Card>
                            <CardImg  width="75%" src={colorado} alt={"Map of Colorado"}/>
                        </Card>
                    </Collapse>
            </div>);
    }
}

export default Map;