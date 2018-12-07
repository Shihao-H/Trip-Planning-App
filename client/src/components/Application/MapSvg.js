import React, {Component} from 'react';
import {Card, CardBody,  Button, Collapse} from 'reactstrap';
import {Map, Polyline, GoogleApiWrapper} from 'google-maps-react';

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
        for (let i = 0; i < this.props.places.length; i++) {
            coordinates.push({lat: this.props.places[i].latitude,
                              lng: this.props.places[i].longitude});
        }
        if (this.props.places.length > 0) {
        coordinates.push({lat: this.props.places[0].latitude,
                          lng: this.props.places[0].longitude});}

        return (
            <div style={{width: "10", height: "512"}}>
                <Map
                    style={{width: "95%", height: "515", display:'flex', flexFlow: 'row nowrap'}}
                    google={this.props.google} zoom={3}
                    initialCenter={{
                        lat: 40.5853, lng: -105.0844
                    }}>
                    <Polyline
                        path={coordinates} strokeColor="#203060"
                        strokeOpacity={0.8} strokeWeight={1.25}/>
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
                            {this.props.mapForOption === "svg" ? this.svg() : this.kml()}
                        </CardBody>
                    </Card>
                </Collapse>
            </div>);
    }
}


export default GoogleApiWrapper({
    apiKey: ("AIzaSyCrmZDM84mTRUSC8H_x2mKGteziwEmiMrA")
})(MapSvg)