import React, {Component} from 'react';
import {Container, Card, CardBody} from 'reactstrap';
import {get} from '../../api/api';
import HomePage from "./HomePage";

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props) {
        super(props);
        this.state = {
            config: null,
            trip: {version: 4, type: "trip", title: "", options: {units: "miles", unitName: "", unitRadius: 0.00,
                    optimization: "none", mapForOption: "kml"}, places: [], distances: [], map: ''},
            search: {version: 3, type: "search", match: "", found: 0, limit: 20, places: [], filters: []},
            selected: new Map(), selectAll: false, otherTeams: null,
            host: null, attributes: ["id", "name", "latitude", "longitude"],
            display: [true, true, true, true, false, false, false, false, false, false]
        };
        this.updateMap = this.updateMap.bind(this);this.updateTrip = this.updateTrip.bind(this);
        this.clearConfig = this.clearConfig.bind(this);this.updateSearch = this.updateSearch.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);this.updateUpload = this.updateUpload.bind(this);
        this.updateSelected = this.updateSelected.bind(this);this.updateSelectAll = this.updateSelectAll.bind(this);
        this.updateInterOperate = this.updateInterOperate.bind(this);this.updateAttributes = this.updateAttributes.bind(this);
        this.checkAttributes = this.checkAttributes.bind(this);this.updateHost = this.updateHost.bind(this);
        this.updateOtherTeams = this.updateOtherTeams.bind(this);this.updateConfig = this.updateConfig.bind(this);
    }

    componentWillMount() {
        get('config').then(
            config => {
                this.setState({
                    config: config
                })
            }
        );
    }

    updateConfig(option, value) {
        let config = this.state.config;
        config[option] = value;
        this.setState(config);
        let newDisplay = [];
        let newAttributes = [];
        config.attributes.map((attribute) =>{
            newDisplay.push(true);
            newAttributes.push(attribute);}
        );
        this.setState({display: newDisplay});
        this.setState({attributes: newAttributes});
    }

    updateInterOperate() {
        get('config', this.state.otherTeams, this.state.host).then(
            newConfig => {
                this.updateConfig('optimization', newConfig.optimization);
                this.updateConfig('attributes', newConfig.attributes);
                if (newConfig.filters) {
                    this.updateConfig('filters', newConfig.filters);
                } else {
                    this.updateConfig('filters', [{"name": "none", "values": ["This team does not support filters."]}]);
                }
                if (newConfig.maps) {
                    this.updateConfig('maps', newConfig.maps);
                } else {
                    this.updateConfig('maps', ["svg"]);
                }

            }
        );
    }

    updateAttributes(value) {
        let attributes = this.state.attributes;
        let config = this.state.config;
        let index = attributes.indexOf(value);
        if (index > -1) {
            attributes.splice(index, 1);
        } else {
            attributes.push(value);
            attributes.sort(function (a, b) {
                let indexA = config.attributes.indexOf(a);
                let indexB = config.attributes.indexOf(b);
                return (indexA < indexB) ? -1 : 1;
            });
        }
        this.setState(attributes);
        this.checkAttributes(value);
    }

    checkAttributes(value) {
        let config = this.state.config;
        let index = config.attributes.indexOf(value);
        let display = this.state.display;
        if (this.state.attributes.includes(value)) {
            display[index] = true;
            this.setState(display);
        } else {
            display[index] = false;
            this.setState(display);
        }
    }


    updateTrip(field, value) {
        let trip = this.state.trip;
        trip[field] = value;
        this.setState(trip);
    }

    clearConfig() {
        this.setState({'config': null});
    }

    updateBasedOnResponse(value) {
        this.setState({'trip': value});
    }

    updateOptions(option, value) {
        let trip = this.state.trip;
        trip.options[option] = value;
        this.setState(trip);
    }

    updateUpload(option, value) {
        let trip = this.state.trip;
        trip.upload[option] = value;
        this.setState(trip);
    }

    updateSearch(field, value) {
        let search = this.state.search;
        search[field] = value;
        this.setState(search);
    }

    updateSelected(value) {
        this.setState({selected: value});
    }

    updateSelectAll(value) {
        this.setState({selectAll: value});
    }

    updateMap(value) {
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

    render() {
        if (!this.state.config) {
            return <div/>
        }
        return (
            <Container id="Application">
                <HomePage config={this.state.config} display={this.state.display} options={this.state.trip.options}
                          updateAttributes={this.updateAttributes} updateOptions={this.updateOptions}
                          updateHost={this.updateHost} updateOtherTeams={this.updateOtherTeams}
                          updateInterOperate={this.updateInterOperate}
                          map={this.state.trip.map} mapForOption={this.state.trip.options.mapForOption}
                          places={this.state.trip.places} selected={this.state.selected}
                          updateSelected={this.updateSelected} attributes={this.state.attributes}
                          selectAll={this.state.selectAll} updateSelectAll={this.updateSelectAll}
                          distances={this.state.trip.distances} title={this.state.trip.title}
                          updateTrip={this.updateTrip} updateSearch={this.updateSearch} search={this.state.search}
                          otherTeams={this.state.otherTeams} trip={this.state.trip} units={this.state.trip.options.units}
                          unitName={this.state.trip.options.unitName} host={this.state.host}/>
            </Container>
        )
    }

}

export default Application;

