import {Col, Card, CardBody, FormGroup, Input, Label } from "reactstrap";
import React, {Component} from 'react'

class Attributes extends Component{
    constructor(props) {
        super(props);
    }

    render() {

        const checkboxes = this.props.config.attributes.map((attribute) =>
            <div>
            <Label>
                <Input type={"checkbox"} value={attribute.charAt(0).toUpperCase() + attribute.slice(1)} />
                    {attribute.charAt(0).toUpperCase() + attribute.slice(1)}
            </Label>
            </div>
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