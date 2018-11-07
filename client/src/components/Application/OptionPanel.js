import React, {Component} from 'react';
import { Row, Col, Card, CardBody} from 'reactstrap';
import Attributes from "./Attributes"
import InterOperate from "./InterOperate"
import Optimization from "./Optimization";
import Options from "./Options";
import MapOption from "./MapOption";

/* Renders the Plan.
 * Holds Clear, Load, Plan, Save.
 */
class OptionPanel extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <p>Options</p>
                        <Row>
                            <Col xs={"4"}>
                                <Options config={this.props.config} options={this.props.options}
                                         updateOptions={this.props.updateOptions}/>
                            </Col>
                            <Col xs={"4"}>
                                <Optimization config={this.props.config} options={this.props.options}
                                              search={this.props.search} trip={this.props.trip}
                                              updateOptions={this.props.updateOptions}
                                              updateSearch={this.props.updateSearch}/>
                            </Col>
                            <Col xs={"4"}>
                                <MapOption config={this.props.config} options={this.props.options}
                                           trip={this.props.trip}
                                           updateOptions={this.props.updateOptions}/>
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
                <Card>
                    <CardBody>
                        <Row>
                            <Col xs={"6"}>
                                <Attributes config={this.props.config} display={this.props.display}
                                            updateAttributes={this.props.updateAttributes}/>
                            </Col>
                            <Col xs={"6"}>
                                <InterOperate host={this.props.host} otherTeams={this.props.otherTeams}
                                              updateHost={this.props.updateHost}
                                              updateOtherTeams={this.props.updateOtherTeams}/>
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
            </div>
        )
    }
}

export default OptionPanel;
