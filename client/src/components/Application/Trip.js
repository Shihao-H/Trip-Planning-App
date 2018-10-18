import React, {Component} from 'react';
import { Card, CardBody, Container } from 'reactstrap';
import Add from "./Add"
import Itinerary from "./Itinerary"
import Map from "./Map"
import Plan from "./Plan";
import SearchBox from "./SearchBox";

/* Renders the Trip.
 * Holds Plan, Search, Add, Map and Itinerary.
 */
class Trip extends Component {
    constructor(props){
        super(props);
    }

    render()
    {
        return (
            <Container id="Trip">
                <Card>
                    <CardBody>
                <Plan
                    config={this.props.config}
                    search={this.props.search}
                    trip={this.props.trip}
                    clearConfig={this.props.clearConfig}
                    LoadFile={this.LoadFile}
                    updateOptions={this.props.updateOptions}
                    updateSearch={this.props.updateSearch}
                    updateTrip={this.props.updateTrip}
                />
                <SearchBox
                    config={this.props.config}
                    search={this.props.search}
                    trip={this.props.trip}
                    updateOptions={this.updateOptions}
                    updateSearch={this.updateSearch}/>
                <Add
                    config={this.props.config}
                    search={this.props.search}
                    trip={this.props.trip}
                    TripPushPlace={this.props.TripPushPlace}
                    updateOptions={this.props.updateOptions}
                    updateSearch={this.props.updateSearch}/>
                <Map/>
                <Itinerary
                    selectAll={this.props.selectAll}
                    selected={this.props.selected}
                    trip={this.props.trip}
                    updateOptions={this.updateOptions}
                    updatePlaces={this.updatePlaces}
                    updateSelectAll={this.updateSelectAll}
                    updateSelected={this.updateSelected}
                    updateTrip={this.updateTrip}/>
                </CardBody>
            </Card>
        </Container>
        )
    }
}
export default Trip;