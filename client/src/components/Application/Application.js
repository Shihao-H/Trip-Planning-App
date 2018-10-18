import React, {Component} from 'react';
import { Container } from 'reactstrap';
import { get_config } from '../../api/api';
import DistanceCal from "./DistanceCal";
import Info from './Info';
import Options from './Options';
import Trip from "./Trip"

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
                <Trip
                    config={this.state.config}
                    search={this.state.search}
                    trip={this.state.trip}
                    clearConfig={this.state.clearConfig}
                    LoadFile={this.LoadFile}
                    TripPushPlace={this.TripPushPlace}
                    updateOptions={this.updateOptions}
                    updateSearch={this.updateSearch}
                    updateSelectAll={this.updateSelectAll}
                    updateSelected={this.updateSelected}
                    updateTrip={this.updateTrip}/>
                <DistanceCal
                    config={this.state.config}
                    search={this.state.search}
                    trip={this.state.trip}
                    updateOptions={this.updateOptions}
                    updateSearch={this.updateSearch}
                    updateTrip={this.updateTrip}/>
                <Options
                    config={this.state.config}
                    options={this.state.trip.options}
                    updateOptions={this.updateOptions}/>
            </Container>
        )
    }
}
export default Application;