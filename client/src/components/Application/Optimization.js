import React, {Component} from 'react'
import {Button, ButtonGroup, CardBody, Collapse} from "reactstrap";

class Optimization extends Component{
    constructor(props){
        super(props);
        this.state = {
            collapse: true,
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
            <Button
                key={'optimization_button_' + optimization.label}
                className='btn-outline-dark optimization-button'
                active={this.props.options.optimization === optimization.label}
                value={optimization.label}
                onClick={this.clickButton}
            >
                {optimization.label.charAt(0).toUpperCase() + optimization.label.slice(1)}
            </Button>
        );


        return (
                <CardBody>
                    <Button onClick={this.dropdown} >Optimization</Button>
                    <Collapse isOpen = {this.state.collapse}>
                        <ButtonGroup size="lg" vertical>
                            {buttons}
                        </ButtonGroup>
                    </Collapse>
                </CardBody>
        )
    };
}

export default Optimization;


{/*<ButtonGroup vertical>*/}
    {/*<Button*/}
        {/*className = 'btn-outline-dark'*/}
        {/*active*/}
        {/*value={"none"}*/}
        {/*onClick={this.clickButton}*/}
    {/*>None</Button>*/}
    {/*<Button*/}
        {/*className = 'btn-outline-dark'*/}
        {/*active*/}
        {/*value={"short"}*/}
        {/*onClick={this.clickButton}*/}
    {/*>Short</Button>*/}
    {/*<Button*/}
        {/*className = 'btn-outline-dark'*/}
        {/*active*/}
        {/*value={"shorter"}*/}
        {/*onClick={this.clickButton}*/}
    {/*>Shorter</Button>*/}
    {/*<Button*/}
        {/*className = 'btn-outline-dark'*/}
        {/*active*/}
        {/*value={"shortest"}*/}
        {/*onClick={this.clickButton}*/}
    {/*>Shortest</Button>*/}
{/*</ButtonGroup>*/}