import React, {Component} from 'react';
import {Row, Col, Card, CardBody, Button, ButtonGroup, Collapse} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"
import Optimization from "./Optimization";
import Options from "./Options";
import MapOption from "./MapOption";

/* Renders the Options.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {ifDisplayUserDefinedInputFields: false, collapse: false,};
    }

    generateOptionsButtons(type,optionName){
        const buttons = this.props.config.type.map((optionValue) =>
            <Button key={'options_button_' + type}
                    className='btn-outline-dark options-button'
                    active={this.props.options[optionName] === optionValue}
                    value={optionValue}
                    onClick={this.clickButton(optionName)}>
                {optionValue.charAt(0).toUpperCase() + optionValue.slice(1)}
            </Button>
        );
        return buttons;
    }

    generateButtonGroups(buttons){
        <ButtonGroup vertical>
            {buttons}
            </ButtonGroup>
    }

    clickButton(event,optionName) {
        this.props.updateOptions(optionName, event.target.value);
        if (event.target.value === 'user defined') {
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    render(){
        <div>
            <Card>
                <CardBody>
                    <ButtonGroup vertical> {this.generateOptionsButtons("units","units")} </ButtonGroup>
                    <ButtonGroup vertical> {this.generateOptionsButtons("optimization","optimization")} </ButtonGroup>
                    <ButtonGroup vertical> {this.generateOptionsButtons("map","mapForOption")} </ButtonGroup>
                    <Attributes config={this.props.config} display={this.props.display}
                                updateAttributes={this.props.updateAttributes}/>
                    <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                  updateHost={this.props.updateHost}
                                  updateInterOperate={this.props.updateInterOperate}
                                  updateOtherTeams={this.props.updateOtherTeams}/>
                </CardBody>
            </Card>
        </div>
    }

}

export default OptionPanel;
