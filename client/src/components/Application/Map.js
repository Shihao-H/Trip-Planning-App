import React, { Component } from 'react';
import {Card, CardBody, Button, Collapse} from 'reactstrap';
import { renderToStaticMarkup } from 'react-dom/server';

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

    render(){
        return (
            <div>
                <div className={'text-center'}>
                    <Button onClick={this.dropdown} size='lg'>Map</Button>
                            <Collapse isOpen = {this.state.collapse}>
                            <Card>
                            <CardBody>
                                <img src={"data:image/svg+xml," + this.props.map} className={'Map'} alt={"Map"}/>
                            </CardBody>
                        </Card>
                    </Collapse>
                </div>
            </div>);
    }
}

export default Map;

