import {Col, Card, CardBody, FormGroup, Input, Label, Form} from "reactstrap";
import React, {Component} from 'react'

class Attributes extends Component {
    constructor(props) {
        super(props);
    }

    render() {

        const checkboxes = this.props.config.attributes.map((attribute) =>
            <Form key={"check_" + attribute}>
                <Input type={"checkbox"}
                       key={"checkbox_" + attribute}
                       value={attribute}
                       onChange={event => {
                           this.props.updateAttributes(event.target.value)
                       }}/>
                {attribute.charAt(0).toUpperCase() + attribute.slice(1)}
            </Form>
        );

        return (
            <Card>
                <CardBody>
                    <p>Select attributes to display.</p>
                    <Col>
                        <FormGroup>
                            {checkboxes}
                        </FormGroup>
                    </Col>
                </CardBody>
            </Card>
        )
    };
}

export default Attributes;