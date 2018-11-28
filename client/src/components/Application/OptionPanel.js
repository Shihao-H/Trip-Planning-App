import React, {Component} from 'react';
import {Row, Col, Card, CardBody, Button, ButtonGroup, FormGroup, Label, Input, Form} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"

/* Renders the Options.
 * Options for units, optimizations, maps, attributes and inter operate.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {ifDisplayUserDefinedInputFields: false, collapse: false,};
    }

    generateOptionsButtons(type,optionName){
        let optionValues = [];
        this.props.config[type].map((optionObject) =>
            {optionValues.push((optionName === "optimization") ? optionObject.label : optionObject)});
        return(
        optionValues.map((optionValue) =>
            <Button key={'options_button_' + optionValue} value={optionValue}
                    className='btn-outline-dark options-button'
                    active={this.props.options[optionName] === optionValue}
                    onClick={(event) => this.clickButton(event, optionName)}>
                {optionValue.charAt(0).toUpperCase() + optionValue.slice(1)}</Button>));
    }

    clickButton(event,optionName) {
        this.props.updateOptions(optionName, event.target.value);
        let value;
        (event.target.value === 'user defined') ? value = true : value = false;
        this.setState({ifDisplayUserDefinedInputFields: value});
    }

    generateUserDefinedForm(placeHolder, forWhat){
        return(
            <Input type="text" placeholder={placeHolder}
                   onChange={event => {this.props.updateOptions(forWhat, event.target.value)}}/>)
    }

    renderUserDefinedForm()
    {
        return(
            <FormGroup>
                {this.generateUserDefinedForm("Enter unit name", "unitName")}
                {this.generateUserDefinedForm("Enter unit radius", "unitRadius")}
            </FormGroup>
        )
    }

    renderGenericOptions(){
        return(
            <Row>
                <Col><ButtonGroup vertical>{this.generateOptionsButtons("units", "units")}</ButtonGroup>
                {this.state.ifDisplayUserDefinedInputFields && (
                    <Form>{this.renderUserDefinedForm()}</Form>)}<br/></Col>
                <Col><ButtonGroup vertical>{this.generateOptionsButtons("optimization", "optimization")}
                </ButtonGroup></Col>
                <Col><ButtonGroup vertical>{this.generateOptionsButtons("maps", "mapForOption")}</ButtonGroup></Col>
            </Row>
        )
    }

    render() {
        return (
            <div><Card><CardBody>
                        {this.renderGenericOptions()}
                        <Attributes config={this.props.config} display={this.props.display}
                                    updateAttributes={this.props.updateAttributes}/>
                        <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                      updateHost={this.props.updateHost}
                                      updateInterOperate={this.props.updateInterOperate}
                                      updateOtherTeams={this.props.updateOtherTeams}/>
                    </CardBody></Card></div>);
    }
}

export default OptionPanel;
