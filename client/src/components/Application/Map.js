import React, { Component } from 'react';
import {Card, CardBody, Button, Collapse} from 'reactstrap';
import { renderToStaticMarkup } from 'react-dom/server';


export class Map extends Component {
    constructor(props)
    {
        super(props);
        this.state = {
            collapse: true,
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
                                <img src={this.props.map} className={'Map'} alt={"Map of Colorado"}/>
                            </CardBody>
                        </Card>
                    </Collapse>
                </div>
            </div>);
    }
}
export default Map;