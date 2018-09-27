
import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import {request} from '../../api/api';
import { get_config } from '../../api/api';
import Upload from './Upload';

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props) {
        super(props);
        this.state = {
            config: null,
            trip: {
                type: "trip",
                title: "",
                options: {
                    units: ["miles", 'kilometers', 'nautical miles']
                },
                places: [],
                distances: [],
                map: '<svg width="1920" height="20" x mlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
            }
        };
        this.updateTrip = this.updateTrip.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateUpload = this.updateUpload.bind(this);
        this.handleSubmitHelper = this.handleSubmitHelper.bind(this);

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

        handleSubmitHelper()
        {
            let trip = this.state.trip;
            return trip;
        }

        render()
        {
            if (!this.state.config) {
                return <div/>
            }
            return (
                <Container id="Application">
                    <Info/>
                    <Upload trip={this.state.trip} config={this.state.config} updateTrip={this.updateTrip}  updateOptions={this.updateOptions}/>
                    <Options options={this.state.trip.options} config={this.state.config}
                             updateOptions={this.updateOptions}/>
                </Container>
            )
        }

}
    export default Application;