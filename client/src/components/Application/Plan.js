import React, {Component} from 'react';
import {CardBody, Card, CardTitle} from 'reactstrap';
import Clear from "./Clear"
import SaveTrip from "./SaveTrip"
import Upload from "./Upload"

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
 */
class Plan extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody id="Plan">
                        <CardTitle>Submit your Trip!</CardTitle>
                        <Upload
                            updateTrip={this.props.updateTrip} updateOptions={this.props.updateOptions}
                            updateSelectAll={this.props.updateSelectAll} trip={this.props.trip}
                            updateSelected={this.props.updateSelected} otherTeams={this.props.otherTeams}
                            host={this.props.host}/><br/>
                        <SaveTrip
                            trip={this.props.trip} title={this.props.title} mapForOption={this.props.mapForOption}
                            map={this.props.map}/><br/>
                        <Clear updateOptions={this.props.updateOptions}
                            updateSearch={this.props.updateSearch} updateTrip={this.props.updateTrip}/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default Plan;