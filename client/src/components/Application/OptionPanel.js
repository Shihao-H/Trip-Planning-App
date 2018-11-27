import React, {Component} from 'react';
import {Row, Col, Card, CardBody, Button} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"
import Optimization from "./Optimization";
import Options from "./Options";
import MapOption from "./MapOption";

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
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

    clickButton(event,optionName) {
        this.props.updateOptions(optionName, event.target.value);
        if (event.target.value === 'user defined') {
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }









    render2()
    {
        return(
        <Row>
            <Col xs={"4"}>
                <Options config={this.props.config} options={this.props.options}
                         updateOptions={this.props.updateOptions}/>
            </Col>
            <Col xs={"4"}>
                <Optimization config={this.props.config} options={this.props.options}
                              trip={this.props.trip} updateOptions={this.props.updateOptions}/>
            </Col>
            <Col xs={"4"}>
                <MapOption config={this.props.config} options={this.props.options}
                           updateOptions={this.props.updateOptions}/>
            </Col>
        </Row>
        )
    }


    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <p>Options</p>{this.render2()}
                    </CardBody>
                </Card>
                <Card>
                    <CardBody>
                        <Row>
                            <Col xs={"6"}>
                                <Attributes config={this.props.config} display={this.props.display}
                                            updateAttributes={this.props.updateAttributes}/>
                            </Col>
                            <Col xs={"6"}>
                                <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                              updateHost={this.props.updateHost}
                                              updateInterOperate={this.props.updateInterOperate}
                                              updateOtherTeams={this.props.updateOtherTeams}/>
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default OptionPanel;
