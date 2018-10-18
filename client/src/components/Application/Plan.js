import React, {Component} from 'react';
import {Container,Card,CardBody} from 'reactstrap';
import Clear from "./Clear"
import SaveTrip from "./SaveTrip"
import Upload from "./Upload"

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
 */
class Plan extends Component {
    constructor(props){
        super(props);
    }

    render()
    {
        return (
            <Card>
                <Container id="Plan">
                    <Upload
                        config={this.props.config}
                        trip={this.props.trip}
                        host={this.props.host}
                        otherTeams={this.props.otherTeams}
                        clearConfig={this.props.clearConfig}
                        TripPushPlace={this.props.TripPushPlace}
                        updateOptions={this.props.updateOptions}
                        updateSearch={this.props.updateSearch}
                        updateSelectAll={this.props.updateSelectAll}
                        updateSelected={this.props.updateSelected}
                        updateTrip={this.props.updateTrip}
                    /><br/>
                    <SaveTrip
                        trip={this.props.trip}
                    /><br/><br/>
                    <Clear
                        config={this.props.config}
                        search={this.props.search}
                        trip={this.props.trip}
                        clearConfig={this.props.clearConfig}
                        LoadFile={this.props.LoadFile}
                        updateOptions={this.props.updateOptions}
                        updateSearch={this.props.updateSearch}
                        updateTrip={this.props.updateTrip}
                    />
                </Container>
            </Card>
        )
    }
}
export default Plan;