import React, {Component} from 'react'
import {Button, ButtonGroup, Card, CardBody, Collapse} from "reactstrap";

class Optimization extends Component{
    constructor(props){
        super(props);
        this.state = {
            collapse: false,
            ifDisplayUserDefinedInputFields: false,
        };
        this.dropdown = this.dropdown.bind(this);
        this.clickButton = this.clickButton.bind(this);
    }

    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }

    clickButton(event){
        this.props.updateOptions('optimization', event.target.value);
    }
    render() {
        const buttons = this.props.config.optimization.map((optimization) =>
            <Button key={'optimization_button_' + optimization.label}
                className='btn-outline-dark optimization-button'
                active={this.props.options.optimization === optimization.label}
                value={optimization.label}
                onClick={this.clickButton}>
                {optimization.label.charAt(0).toUpperCase() + optimization.label.slice(1)}
            </Button>
        );
        return (
            <div className={'text-center'}>
                <Card>
                    <CardBody>
                        <p>Select the optimizations you wish to use.</p>
                        <Button size='lg'
                            onClick={this.dropdown}
                            className = 'btn-outline-dark'>Optimization</Button>
                        <Collapse isOpen = {this.state.collapse}>
                            <ButtonGroup size="lg" vertical>{buttons}</ButtonGroup>
                        </Collapse>
                    </CardBody>
                </Card>
            </div>
        )
    };
}

export default Optimization;
