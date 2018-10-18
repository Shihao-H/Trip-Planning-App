import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Upload from './Upload';
import { Map } from './Map';
import { get_config } from '../../api/api';
import Itinerary from './Itinerary'
import {SearchBox} from "./SearchBox";
import DistanceCal from "./DistanceCal";

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props){
        super(props);
        this.state = {
            config:null,
            trip: {
                type: "trip",
                title: "",
                options : {
                    units: ["miles", 'kilometers', 'nautical miles', 'user defined'],
                    unitName: "",
                    unitRadius: 0.00,
                    optimization:"none"
                },
                places: [],
                distances: [],
                map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
            },
            search: {
                version   : 3,
                type      : "search",
                match     : "",
                limit     : 0,
                places    : []
            },
            selected: new Map(),
            selectAll: false
        };
        this.updateTrip = this.updateTrip.bind(this);
        this.clearConfig = this.clearConfig.bind(this);
        this.updateSearch = this.updateSearch.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateUpload = this.updateUpload.bind(this);
        this.TripPushPlace = this.TripPushPlace.bind(this);
        this.updateSelected = this.updateSelected.bind(this);
        this.updateSelectAll = this.updateSelectAll.bind(this);
    }
    componentWillMount()
    {
        get_config().then(
            config => {
                this.setState({
                    config: config
                })
            }
        );
    }

    updateTrip(field, value)
    {
        let trip = this.state.trip;
        trip[field] = value;
        this.setState(trip);
    }

    TripPushPlace(value)
    {
        let trip = this.state.trip;
        trip.places.push(value);
        this.setState(trip);
    }

    clearConfig()
    {
        this.setState({'config': null});
    }

    updateBasedOnResponse(value)
    {
        this.setState({'trip': value});
    }

    updateOptions(option, value)
    {
        let trip = this.state.trip;
        trip.options[option] = value;
        this.setState(trip);
    }

    updateUpload(option, value)
    {
        let trip = this.state.trip;
        trip.upload[option] = value;
        this.setState(trip);
    }

    updateSearch(field, value)
    {
        let search = this.state.search;
        search[field] = value;
        this.setState(search);
    }

    updateSelected(value){
        this.setState({selected: value});
    }

    updateSelectAll(value){
        this.setState({selectAll: value});
    }

    render()
    {
        if (!this.state.config) {
            return <div/>
        }
        return (
            <Container id="Application">
                <Info/>
                <DistanceCal options={this.state.trip.options} config={this.state.config} trip={this.state.trip} search={this.state.search} config={this.state.config} updateSearch={this.updateSearch} updateTrip={this.updateTrip} updateOptions={this.updateOptions}/>
                <Options options={this.state.trip.options} config={this.state.config}
                         updateOptions={this.updateOptions}/>
                <Upload trip={this.state.trip} config={this.state.config} TripPushPlace={this.TripPushPlace}
                        updateSelected={this.updateSelected} updateSelectAll={this.updateSelectAll}
                        updateTrip={this.updateTrip}  updateOptions={this.updateOptions} clearConfig={this.clearConfig} updateSearch={this.updateSearch}/>
                <Map/>
                <Itinerary trip={this.state.trip} updateTrip={this.updateTrip} updateOptions={this.updateOptions} updatePlaces={this.updatePlaces}
                           selected={this.state.selected} selectAll={this.state.selectAll} updateSelected={this.updateSelected} updateSelectAll={this.updateSelectAll}/>
                <SearchBox trip={this.state.trip} search={this.state.search} config={this.state.config} options={this.state.trip.options} updateSearch={this.updateSearch} updateTrip={this.updateTrip} updateOptions={this.updateOptions}/>
            </Container>
        )
    }
}
export default Application;