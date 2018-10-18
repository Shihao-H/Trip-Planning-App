import React, {Component} from 'react';
import { Card, CardBody, Container,Row,Col} from 'reactstrap';
import Add from "./Add"
import Itinerary from "./Itinerary"
import Map from "./Map"
import Plan from "./Plan";
import SearchBox from "./SearchBox";
import Upload from "./Upload";
import Options from "./Options";

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
                        <Row>
                            <Col>
                                <Plan
                                config={this.props.config}
                                host={this.props.host}
                                otherTeams={this.props.otherTeams}
                                search={this.props.search}
                                trip={this.props.trip}
                                clearConfig={this.props.clearConfig}
                                LoadFile={this.props.LoadFile}
                                updateHost={this.props.updateHost}
                                updateOptions={this.props.updateOptions}
                                updateOtherTeams={this.props.updateOtherTeams}
                                updateSearch={this.props.updateSearch}
                                updateSelectAll={this.props.updateSelectAll}
                                updateSelected={this.props.updateSelected}
                                updateTrip={this.props.updateTrip}
                            />
                            </Col>
                            <Col>
                                <SearchBox
                                    config={this.props.config}
                                    search={this.props.search}
                                    trip={this.props.trip}
                                    updateOptions={this.props.updateOptions}
                                    updateSearch={this.props.updateSearch}/>
                            </Col>
                            <Col>
                                <Add
                                    config={this.props.config}
                                    search={this.props.search}
                                    trip={this.props.trip}
                                    TripPushPlace={this.props.TripPushPlace}
                                    updateOptions={this.props.updateOptions}
                                    updateSearch={this.props.updateSearch}/>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Map/>
                            </Col>
                            <Col>
                                <Itinerary
                                    display={this.props.display}
                                    selectAll={this.props.selectAll}
                                    selected={this.props.selected}
                                    trip={this.props.trip}
                                    updateOptions={this.props.updateOptions}
                                    updatePlaces={this.props.updatePlaces}
                                    updateSelectAll={this.props.updateSelectAll}
                                    updateSelected={this.props.updateSelected}
                                    updateTrip={this.props.updateTrip}/>
                            </Col>
                        </Row>
                </CardBody>
            </Card>
        </Container>
        )
    }
}
export default Trip;