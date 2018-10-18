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
            optimization:["none","short"]
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
      }
    };
    this.updateTrip = this.updateTrip.bind(this);
    this.clearConfig = this.clearConfig.bind(this);
    this.updateSearch = this.updateSearch.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
    this.updateUpload = this.updateUpload.bind(this);

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

        render()
        {
            if (!this.state.config) {
                return <div/>
            }
            return (
                <Container id="Application">
                    <Info/>
                    <Options options={this.state.trip.options} config={this.state.config}
                             updateOptions={this.updateOptions}/>
                        <Upload trip={this.state.trip} config={this.state.config} updateOptions={this.updateOptions} clearConfig={this.clearConfig}
                                updateSearch={this.updateSearch} updateTrip={this.updateTrip}/>
                    <Map/>
                    <Itinerary trip={this.state.trip}/>
                    <SearchBox trip={this.state.trip} search={this.state.search} config={this.state.config} options={this.state.trip.options} updateSearch={this.updateSearch} updateTrip={this.updateTrip} updateOptions={this.updateOptions}/>
                    <DistanceCal options={this.state.trip.options} config={this.state.config} trip={this.state.trip} search={this.state.search} config={this.state.config} updateSearch={this.updateSearch} updateTrip={this.updateTrip} updateOptions={this.updateOptions}/>
                </Container>
            )
        }
}
    export default Application;
