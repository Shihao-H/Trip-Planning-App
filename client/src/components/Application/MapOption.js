import React, {Component} from 'react'
import {Button, ButtonGroup, Card, CardBody, Collapse} from "reactstrap";

class MapOption extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: false
        };
        this.dropdown = this.dropdown.bind(this);
        this.clickButton = this.clickButton.bind(this);
    }

    dropdown() {
        this.setState({collapse: !this.state.collapse})
    }

    clickButton(event) {
        this.props.updateOptions('map', event.target.value);
    }

    render() {
        const buttons = this.props.config.maps.map((map) =>
            <Button key={'optimization_button_' + map}
                    className='btn-outline-dark optimization-button'
                    active={this.props.options.map === map}
                    value={map}
                    onClick={this.clickButton}>
                {map}
            </Button>
        );
        return (
            <div className={'text-center'}>
                <Card>
                    <CardBody>
                        <p>Select the map you wish to use.</p>
                        <Button size='lg'
                                onClick={this.dropdown}
                                className='btn-outline-dark'>Map</Button>
                        <Collapse isOpen={this.state.collapse}>
                            <br/>
                            <ButtonGroup size="lg" vertical>{buttons}</ButtonGroup>
                        </Collapse>
                    </CardBody>
                </Card>
            </div>
        )
    };
}

export default MapOption;