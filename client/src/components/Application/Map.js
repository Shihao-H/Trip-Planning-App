import React, { Component } from 'react';
import {Card, CardBody} from 'reactstrap';
import { renderToStaticMarkup } from 'react-dom/server';
import defaultSvg from '../../../../images/CObackground.svg';


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
    console.log(this.props.map);

        return (
            <div>
                <Card>
                    <CardBody>
                        <img src={this.props.map} className={'Map'} alt={"Map of Colorado"}/>
                    </CardBody>
                </Card>
            </div>);
    }
}
export default Map;