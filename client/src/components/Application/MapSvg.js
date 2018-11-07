import React, {Component} from 'react';
import {Card, Container, CardBody,  Button, Collapse} from 'reactstrap';
import {Map, Polyline, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';
import {renderToStaticMarkup} from 'react-dom/server';

export class MapSvg extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: true,
        };
        this.dropdown = this.dropdown.bind(this);
        this.svg = this.svg.bind(this);
        this.kml = this.kml.bind(this);
    }

    dropdown() {
        this.setState({collapse: !this.state.collapse})
    }

    svg() {
        return <img src={"data:image/svg+xml," + this.props.map} className={'Map'} alt={"Map"}/>;
    }

    kml() {
        const coordinates = [];
        for (let i = 0; i < this.props.trip.places.length; i++) {
            coordinates.push({
                lat: this.props.trip.places[i].latitude,
                lng: this.props.trip.places[i].longitude
            })
        }

        return (
           <div style={{width: "1024", height: "512"}}>
            <Map
                style={{width: "1000", height: "500"}}
                google={this.props.google} zoom={3}
                 initialCenter={{
                     lat: 40.5853, lng: -105.0844
                 }}>
                <Polyline
                    path={coordinates}
                    strokeColor="#ff1e19"
                    strokeOpacity={0.8}
                    strokeWeight={2}/>
            </Map>
           </div>
        );
    }


    render() {
        return (
            <div className={'text-center'}>
                <Button onClick={this.dropdown} size='lg'>Map</Button>
                <Collapse isOpen={this.state.collapse}>
                    <Card>
                        <CardBody>

                            {this.props.options.map === "svg" ? this.svg() : this.kml()}

                        </CardBody>
                    </Card>
                </Collapse>
            </div>);
    }
}


export default GoogleApiWrapper({
    apiKey: ("AIzaSyCrmZDM84mTRUSC8H_x2mKGteziwEmiMrA")
})(MapSvg)