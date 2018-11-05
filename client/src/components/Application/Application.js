import React, {Component} from 'react';
import Map from './Map';
import defaultSvg from "../../../../images/CObackground.svg";
import {Card, CardBody, Container} from 'reactstrap';
import { get_config } from '../../api/api';
import DistanceCal from "./DistanceCal";
import Info from './Info';
import Trip from "./Trip"
import OptionPanel from "./OptionPanel";


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
                    units: "miles",
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
            selectAll: false,
            otherTeams: null,
            host: null,
            display: {
                Name: true,
                Id: true,
                Latitude: true,
                Longitude: true,
                Leg: true,
                Total: true,
                UserDefined: null,
                UserDefinedDisplay: false
            },
            attributes:[
                "id","name","latitude","longitude"
            ]
        };
        this.updateMap = this.updateMap.bind(this);
        this.updateTrip = this.updateTrip.bind(this);
        this.clearConfig = this.clearConfig.bind(this);
        this.updateSearch = this.updateSearch.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateUpload = this.updateUpload.bind(this);
        this.updateSelected = this.updateSelected.bind(this);
        this.updateSelectAll = this.updateSelectAll.bind(this);
        this.updateOtherTeams=this.updateOtherTeams.bind(this);
        this.updateHost=this.updateHost.bind(this);
        this.updateDisplay = this.updateDisplay.bind(this);
        this.updateDisplayUserDefined = this.updateDisplayUserDefined.bind(this);
        this.updateAttributes = this.updateAttributes.bind(this);
        this.checkAttributes = this.checkAttributes.bind(this);
    }

    updateAttributes(value){
        let attributes = this.state.attributes;
        var index = attributes.indexOf(value);
        if(index > -1){
            attributes.splice(index, 1);
        } else {
            if(value === "name") attributes.unshift(value);
            else attributes.push(value);
        }
       this.setState(attributes);
    }

    checkAttributes(value){
        if(this.state.attributes.includes(value)){
            return true;
        } else {
            return false;
        }
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

    updateDisplayUserDefined(value){
        let display = this.state.display;
        display.UserDefined = value;
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
                              attributes={this.state.attributes}
                              clearConfig={this.state.clearConfig} LoadFile={this.LoadFile}
                              updateAttributes={this.updateAttributes}
                              checkAttributes={this.checkAttributes}
                              updateHost={this.updateHost} updateTrip={this.updateTrip}
                              updateOptions={this.updateOptions} updateOtherTeams={this.updateOtherTeams}
                              updateSearch={this.updateSearch} updateSelectAll={this.updateSelectAll}
                              updateSelected={this.updateSelected} updateMap={this.updateMap}
                              map={this.state.map} updateDisplay={this.updateDisplay}
                              updateDisplayUserDefined={this.updateDisplayUserDefined}
                        />
                        <DistanceCal config={this.state.config} options={this.state.trip.options}
                                     search={this.state.search} trip={this.state.trip}
                                     updateOptions={this.updateOptions} updateSearch={this.updateSearch}
                                     updateTrip={this.updateTrip}/>
                    </CardBody></Card></Container>
        )
    }
}

export default Application;

