import React, {Component} from 'react';
import {CardBody, Card} from 'reactstrap';
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
                         <Upload
                            updateTrip={this.props.updateTrip} updateOptions={this.props.updateOptions}
                            updateSelectAll={this.props.updateSelectAll} trip={this.props.trip}
                            updateSelected={this.props.updateSelected} otherTeams={this.props.otherTeams}
                            host={this.props.host}/><br/>
                        <SaveTrip
                            title={this.props.title} map={this.props.map}/><br/>
                        <Clear updateOptions={this.props.updateOptions}
                            updateSearch={this.props.updateSearch} updateTrip={this.props.updateTrip}/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default Plan;
