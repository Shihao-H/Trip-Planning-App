import React, {Component} from 'react';
import {Card, CardBody, Container} from 'reactstrap';
import {get} from '../../api/api';
import DistanceCal from "./DistanceCal";
import Info from './Info';
import Trip from "./Trip"

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props) {
        super(props);
        this.state = {
            config: null,
            trip: {
                version: 4,
                type: "trip",
                title: "",
                options: {
                    units: "miles",
                    unitName: "",
                    unitRadius: 0.00,
                    optimization: "none",
                    mapForOption: "svg"
                },
                places: [],
                distances: [],
                map: '<svg width="1920" height="960" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">\n' +
                    '<g>\n' +
                    '<g id="s4">\n' +
                    '<svg id="s1" height="960" width="1920"\n' +
                    'xmlns:svg="http://www.w3.org/2000/svg" xmlns="http://www.w3.org/2000/svg">\n' +
                    '<title>Layer 1</title>\n' +
                    '<rect fill="rgb(119, 204, 119)" stroke="black" x="0" y="0" width="1920" height="960" id="s3"/>\n' +
                    '</svg>\n' +
                    '</g>\n' +
                    '<g id="s9">\n' +
                    '<svg id="s5" height="480" width="960" y="240" x="480"\n' +
                    'xmlns:svg="http://www.w3.org/2000/svg" xmlns="http://www.w3.org/2000/svg">\n' +
                    '<title>Layer 2</title>\n' +
                    '<polygon points="4,4 956,4 956,476 4,476"\n' +
                    'fill="none" stroke-width="8" stroke="brown" id="s8"/>\n' +
                    '<polyline points="0,0 960,480 480,0 0,480 960,0 480,480 0,0"\n' +
                    'fill="none" stroke-width="4" stroke="blue" id="s7"/>\n' +
                    '</svg>\n' +
                    '</g>\n' +
                    '</g>\n' +
                    '</svg>'
            },
            search: {
                version: 3,
                type: "search",
                match: "",
                found: 0,
                places: [],
                filters: []
            },
            selected: new Map(),
            selectAll: false,
            otherTeams: null,
            host: null,
            attributes: [
                "id", "name", "latitude", "longitude"
            ],
            display: [true, true, true, true,
                false, false, false, false, false, false]
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
        this.updateInterOperate = this.updateInterOperate.bind(this);
        this.updateAttributes = this.updateAttributes.bind(this);
        this.checkAttributes = this.checkAttributes.bind(this);
        this.updateHost = this.updateHost.bind(this);
        this.updateOtherTeams = this.updateOtherTeams.bind(this);
        this.updateConfig = this.updateConfig.bind(this);
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
                      options={this.state.trip.options}
                      updateInterOperate={this.updateInterOperate}
                      updateHost={this.updateHost} updateOtherTeams={this.updateOtherTeams}
                      updateTrip={this.updateTrip}
                      updateOptions={this.updateOptions}
                      updateSearch={this.updateSearch} updateSelectAll={this.updateSelectAll}
                      updateSelected={this.updateSelected} updateMap={this.updateMap}
                      map={this.state.trip.map}
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

