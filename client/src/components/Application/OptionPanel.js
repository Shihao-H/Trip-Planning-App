import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"
import Optimization from "./Optimization";
import Options from "./Options";

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
 */
class OptionPanel extends Component {
    constructor(props){
        super(props);
    }

    render()
    {
        return (
            <Container id="Plan">
                <Options
                    config={this.props.config}
                    options={this.props.options}
                    updateOptions={this.props.updateOptions}/>
                <Optimization
                    config={this.props.config}
                    options={this.props.options}
                    search={this.props.search}
                    trip={this.props.trip}
                    updateOptions={this.props.updateOptions}
                    updateSearch={this.props.updateSearch}/>
                <Attributes
                    display={this.props.display}
                    updateDisplay={this.props.updateDisplay}
                />
                <InterOperate
                    host={this.props.host}
                    otherTeams={this.props.otherTeams}
                    updateHost={this.props.updateHost}
                    updateOtherTeams={this.props.updateOtherTeams}/>
            </Container>
        )
    }
}
export default OptionPanel;