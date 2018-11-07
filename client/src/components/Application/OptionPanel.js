import React, {Component} from 'react';
import {Container, Row, Col, Card, CardBody} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"
import Optimization from "./Optimization";
import Options from "./Options";
import Trip from "./Trip";

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Card>
                <CardBody><p className="lead">Options</p></CardBody>
                <Container id="Plan">
                    <Row><Col>
                        <Options config={this.props.config} options={this.props.options}
                                 updateOptions={this.props.updateOptions}/>
                    </Col>
                        <Col>
                            <Optimization config={this.props.config} options={this.props.options}
                                          search={this.props.search} trip={this.props.trip}
                                          updateOptions={this.props.updateOptions}
                                          updateSearch={this.props.updateSearch}/>
                        </Col>
                        <Col>
                            <Attributes config={this.props.config} display={this.props.display}
                                        updateAttributes={this.props.updateAttributes}/>
                        </Col>
                        <Col>
                            <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                          updateHost={this.props.updateHost}
                                          updateOtherTeams={this.props.updateOtherTeams}/>
                        </Col></Row>
                </Container>
            </Card>
        )
    }
}

export default OptionPanel;
