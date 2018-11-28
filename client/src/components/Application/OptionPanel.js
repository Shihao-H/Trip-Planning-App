import React, {Component} from 'react';
import {Row, Col, Card, CardBody, Button, ButtonGroup, Collapse, FormGroup, Label, Input, Form} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"

/* Renders the Options.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {ifDisplayUserDefinedInputFields: false, collapse: false,};
    }
//{let optionValue = ((optionName == "optimization") ? optionObject.label : optionObject);
    generateOptionsButtons(type,optionName){
        return this.props.config[type].map((optionValue) =>

            <Button key={'options_button_' + optionValue}
                    className='btn-outline-dark options-button'
                    active={this.props.options[optionName] === optionValue}
                    value={optionValue}
                    onClick={(event) => this.clickButton(event, optionName)}>
                {optionValue.charAt(0).toUpperCase() + optionValue.slice(1)}
                </Button>
        );
    }

    clickButton(event,optionName) {
        this.props.updateOptions(optionName, event.target.value);
        if (event.target.value === 'user defined') {
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    renderUserDefinedForm()
    {
        return(
            <FormGroup>
                <br/><Label>Unit Name:</Label>
                <Input type="text" placeholder="Enter unit name" onChange={event => {
                    this.props.updateOptions('unitName', event.target.value)
                }}/>
                <Label>Unit Radius: </Label>
                <Input type="text" placeholder="Enter unit radius"
                       onChange={event => {
                           this.props.updateOptions('unitRadius', event.target.value)
                       }}/>
            </FormGroup>
        )
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <ButtonGroup vertical>{this.generateOptionsButtons("units", "units")}</ButtonGroup><br/>
                        {this.state.ifDisplayUserDefinedInputFields && (
                            <Form>
                                {this.renderUserDefinedForm()}
                            </Form>)}<br/>

                        <ButtonGroup vertical>{this.generateOptionsButtons("maps", "mapForOption")}</ButtonGroup><br/>
                        <Attributes config={this.props.config} display={this.props.display}
                                    updateAttributes={this.props.updateAttributes}/>
                        <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                      updateHost={this.props.updateHost}
                                      updateInterOperate={this.props.updateInterOperate}
                                      updateOtherTeams={this.props.updateOtherTeams}/>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

export default OptionPanel;
