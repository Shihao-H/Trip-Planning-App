import React, {Component} from 'react'
import {Card, CardBody, FormGroup, ButtonGroup, Input, Label, Form, Collapse, Button} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */

class Options extends Component {
    constructor(props) {
        super(props);
        this.state = {ifDisplayUserDefinedInputFields: false, collapse: false,};
        this.clickUserDefinedButton = this.clickUserDefinedButton.bind(this);
        this.dropdown = this.dropdown.bind(this);
    }

    dropdown() {
        this.setState({collapse: !this.state.collapse})
    }

    clickUserDefinedButton(event) {
        this.props.updateOptions('units', event.target.value);
        if (event.target.value === 'user defined') {
            this.setState({ifDisplayUserDefinedInputFields: true});
        } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
        }
    }

    render() {
        const buttons = this.props.config.units.map((unit) =>
            <Button key={'distance_button_' + unit} className='btn-outline-dark unit-button'
                    active={this.props.options.units === unit} value={unit} onClick={this.clickUserDefinedButton}>
                {unit.charAt(0).toUpperCase() + unit.slice(1)} </Button>);
        return (
            <div className={'text-center'}>
                <Card>
                    <CardBody>
                        <p>Select the units you wish to use.</p>
                        <Button onClick={this.dropdown} className='btn-outline-dark' size='lg'>Units</Button>
                        <Collapse isOpen={this.state.collapse}>
                            <br/>
                            <ButtonGroup vertical> {buttons} </ButtonGroup> <br/>
                            {this.state.ifDisplayUserDefinedInputFields && (
                                <Form>
                                    <FormGroup>
                                        <br/>
                                        <Label>Unit Name:</Label>
                                        <Input type="text" placeholder="Enter unit name" onChange={event => {
                                            this.props.updateOptions('unitName', event.target.value)
                                        }}/>
                                        <Label>Unit Radius: </Label>
                                        <Input type="text" placeholder="Enter unit radius"
                                               onChange={event => {
                                                   this.props.updateOptions('unitRadius', event.target.value)
                                               }}/>
                                    </FormGroup>
                                </Form>)}
                        </Collapse>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

export default Options;
