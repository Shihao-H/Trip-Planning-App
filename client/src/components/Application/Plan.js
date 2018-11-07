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
                            config={this.props.config} trip={this.props.trip}
                            host={this.props.host} otherTeams={this.props.otherTeams}
                            clearConfig={this.props.clearConfig} updateTrip={this.props.updateTrip}
                            updateOptions={this.props.updateOptions} updateSearch={this.props.updateSearch}
                            updateSelectAll={this.props.updateSelectAll}
                            updateSelected={this.props.updateSelected}/><br/>
                        <SaveTrip
                            trip={this.props.trip} map={this.props.map}/><br/>
                        <Clear
                            config={this.props.config} search={this.props.search}
                            trip={this.props.trip} clearConfig={this.props.clearConfig}
                            LoadFile={this.props.LoadFile} updateOptions={this.props.updateOptions}
                            updateSearch={this.props.updateSearch} updateTrip={this.props.updateTrip}/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default Plan;