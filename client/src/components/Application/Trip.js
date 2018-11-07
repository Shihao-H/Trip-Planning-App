import React, {Component} from 'react';
import {Row, Col, Card, CardBody} from 'reactstrap';
import Add from "./Add"
import Itinerary from "./Itinerary"
import MapSvg from "./MapSvg"
import Plan from "./Plan";
import SearchBox from "./SearchBox";
import OptionPanel from "./OptionPanel";

/* Renders the Trip.
 * Holds Plan, Search, Add, Map and Itinerary.
 */
class Trip extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Card>
                <CardBody id="Trip">
                    <Row>
                        <Col xs={"6"}>
                            <Plan
                                config={this.props.config}
                                host={this.props.host}
                                otherTeams={this.props.otherTeams}
                                search={this.props.search}
                                trip={this.props.trip}
                                clearConfig={this.props.clearConfig}
                                LoadFile={this.props.LoadFile}
                                map={this.props.map}
                                updateHost={this.props.updateHost}
                                updateOptions={this.props.updateOptions}
                                updateOtherTeams={this.props.updateOtherTeams}
                                updateSearch={this.props.updateSearch}
                                updateSelectAll={this.props.updateSelectAll}
                                updateSelected={this.props.updateSelected}
                                updateTrip={this.props.updateTrip}
                            />
                        </Col>
                        <Col xs={"6"}>
                            <Add
                                config={this.props.config}
                                search={this.props.search}
                                trip={this.props.trip}
                                updateOptions={this.props.updateOptions}
                                updateTrip={this.props.updateTrip}
                                updateSearch={this.props.updateSearch}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <SearchBox
                                config={this.props.config}
                                search={this.props.search}
                                trip={this.props.trip}
                                updateOptions={this.props.updateOptions}
                                updateSearch={this.props.updateSearch}
                                updateTrip={this.props.updateTrip}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <OptionPanel config={this.props.config} display={this.props.display}
                                         attributes={this.props.attributes}
                                         host={this.props.host} options={this.props.trip.options}
                                         otherTeams={this.props.otherTeams} updateDisplay={this.props.updateDisplay}
                                         updateDisplayUserDefined={this.props.updateDisplayUserDefined}
                                         updateHost={this.props.updateHost} updateOptions={this.props.updateOptions}
                                         updateAttributes={this.props.updateAttributes}
                                         checkAttributes={this.props.checkAttributes}
                                         updateOtherTeams={this.props.updateOtherTeams}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <MapSvg map={this.props.map} options={this.props.options}
                                 trip={this.props.trip}/>
                        </Col>
                        <Col>
                            <Itinerary
                                config={this.props.config}
                                attributes={this.props.attributes}
                                distances={this.props.distances}
                                selectAll={this.props.selectAll}
                                updateAttributes={this.props.updateAttributes}
                                selected={this.props.selected}
                                trip={this.props.trip}
                                places={this.props.trip.places}
                                updateOptions={this.props.updateOptions}
                                updateSelectAll={this.props.updateSelectAll}
                                updateSelected={this.props.updateSelected}
                                updateTrip={this.props.updateTrip}/>
                        </Col>
                    </Row>
                </CardBody>
            </Card>
        )
    }
}

export default Trip;
