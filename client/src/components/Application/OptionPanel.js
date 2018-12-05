import React, {Component} from 'react';
import {Row, Col, Card, CardBody, Button, ButtonGroup, FormGroup, Input, Form} from 'reactstrap';
import InterOperate from "./InterOperate"

/* Renders the Options.
 * Options for units, optimizations, maps, attributes and inter operate.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {ifDisplayUserDefinedInputFields: false};
        this.generateOptionsButtons = this.generateOptionsButtons.bind(this);
        this.clickButton = this.clickButton.bind(this);
        this.generateUserDefinedForm = this.generateUserDefinedForm.bind(this);
        this.renderUserDefinedForm = this.renderUserDefinedForm.bind(this);
        this.renderGenericOptions = this.renderGenericOptions.bind(this);
    }

    generateOptionsButtons(type, optionName) {
        let optionValues = [];
        this.props.config[type].map((optionObject) =>
            {optionValues.push((optionName === "optimization") ? optionObject.label : optionObject)});
        return(
        optionValues.map((optionValue) =>
            <Button key={'options_button_' + optionValue} value={optionValue}
                    className='btn-outline-dark options-button'
                    active={this.props.options[optionName] === optionValue}
                    onClick={(event) => this.clickButton(event, optionName)}>
                {(optionValue === "svg") ? optionValue = "Static" :
                    (optionValue === "kml" ? optionValue = "Interactive" :
                        optionValue.charAt(0).toUpperCase() + optionValue.slice(1))}
                </Button>));
    }

    clickButton(event, optionName) {
        this.props.updateOptions(optionName, event.target.value);
        let value;
        (event.target.value === 'user defined') ? value = true : value = false;
        this.setState({ifDisplayUserDefinedInputFields: value});
    }

    generateUserDefinedForm(placeHolder, forWhat) {
        return (
            <Input type="text" placeholder={placeHolder}
                   onChange={event => {
                       this.props.updateOptions(forWhat, event.target.value)
                   }}/>)
    }

    renderUserDefinedForm() {
        return (
            <FormGroup>
                {this.generateUserDefinedForm("Enter unit name", "unitName")}
                {this.generateUserDefinedForm("Enter unit radius", "unitRadius")}
            </FormGroup>
        )
    }

    renderGenericOptions(suppose, actual) {
        return (
            <Col><Card><CardBody>
                <p>{"Select the " + suppose + " you wish to use."}</p>
                <ButtonGroup vertical>{this.generateOptionsButtons(suppose, actual)}</ButtonGroup>
                {(suppose === "units") && this.state.ifDisplayUserDefinedInputFields && (
                    <Form>{this.renderUserDefinedForm()}</Form>)}<br/>
            </CardBody></Card></Col>
        )
    }

    render() {
        return (
            <div><Card><CardBody><Row>
                {this.renderGenericOptions("units", "units")}
                {this.renderGenericOptions("optimization", "optimization")}
                {this.renderGenericOptions("maps", "mapForOption")}
                <Col><InterOperate updateHost={this.props.updateHost}
                                   updateInterOperate={this.props.updateInterOperate}
                                   updateOtherTeams={this.props.updateOtherTeams}/></Col>
            </Row></CardBody></Card></div>);
    }
}

export default OptionPanel;
