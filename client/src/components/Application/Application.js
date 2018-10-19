import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Upload from './Upload';
import Map from './Map';
import {get_config, request} from '../../api/api';
import Itinerary from './Itinerary'
import defaultSvg from "../../../../images/CObackground.svg";

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props){
        super(props);
        this.state = {
            config:null,
            trip: {
                version: 3,
                type: "trip",
                title: "",
                options : {
                    units: ["miles",],
                    unitName: "",
                    unitRadius: 0.00,
                    optimization:"none"
                },
                places: [],
                distances: [],
                map: ""
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
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateUpload = this.updateUpload.bind(this);
        this.updateMap = this.updateMap.bind(this);
        this.updateSelected = this.updateSelected.bind(this);
        this.updateSelectAll = this.updateSelectAll.bind(this);
        this.updateOtherTeams=this.updateOtherTeams.bind(this);
        this.updateHost=this.updateHost.bind(this);
        this.updateDisplay = this.updateDisplay.bind(this);
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
     
    updateMap(value){
        let trip = this.state.trip;
        trip.map = value;
        this.setState(trip);
    }

    updateOtherTeams(event) {
        this.setState({otherTeams: event.target.value});

    }

    updateHost(event) {
        this.setState({host: event.target.value});

    }

    updateDisplay(value){
        let display = this.state.display;
        display[value] = !display[value];
        this.setState({display: display});
        this.updateSelectAll(false);
    }

    render() {
        if (!this.state.config) {return <div/>}
        return (
            <Container id="Application"><Card><CardBody>
                        <Info/>
                        <Trip config={this.state.config} display={this.state.display}
                              host={this.state.host} otherTeams={this.state.otherTeams}
                              search={this.state.search} selectAll={this.state.selectAll}
                              selected={this.state.selected} trip={this.state.trip}
                              clearConfig={this.state.clearConfig} LoadFile={this.LoadFile}
                              updateHost={this.updateHost} updateTrip={this.updateTrip}
                              updateOptions={this.updateOptions} updateOtherTeams={this.updateOtherTeams}
                              updateSearch={this.updateSearch} updateSelectAll={this.updateSelectAll}
                              updateSelected={this.updateSelected}/>
                        <OptionPanel config={this.state.config} display={this.state.display}
                                     host={this.state.host} options={this.state.trip.options}
                                     otherTeams={this.state.otherTeams} updateDisplay={this.updateDisplay}
                                     updateHost={this.updateHost} updateOptions={this.updateOptions}
                                     updateOtherTeams={this.updateOtherTeams}/>
                        <DistanceCal config={this.state.config} options={this.state.trip.options}
                                     search={this.state.search} trip={this.state.trip}
                                     updateOptions={this.updateOptions} updateSearch={this.updateSearch}
                                     updateTrip={this.updateTrip}/>
                    <Map updateMap={this.updateMap} map={this.state.map}/>
                    </CardBody></Card></Container>
        )
    }
}
export default Application;
