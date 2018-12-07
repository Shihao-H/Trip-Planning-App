import React, {Component} from 'react'
import {Alert, CardBody, Row, Col, CardImg, CardTitle, CardText} from 'reactstrap'
import Team from '../../../../server/src/main/resources/Team.jpg';

export default class Developer extends Component {
    constructor(props) {
        super(props);
        this.teamImage = this.teamImage.bind(this);
        this.forEachTeamMember = this.forEachTeamMember.bind(this);
        this.state = {
            Josh: "Proud WOPR member! Glad to have been in this group of lovely, hardworking people. " +
            "I hope you enjoy your time here on our website!",
            Lacey: "Lacey's bio (picture above)",
            Minjie: "LOVE WOPR!",
            Shihao: "Bazinga!"
        };
    }

    teamImage() {
        return (
            <CardBody>
                <CardImg top width="10%" src={Team} alt="Team image"/>
            </CardBody>
        )
    }

    forEachTeamMember(name, text) {
        return (
            <Col>
                <CardBody>
                    <CardTitle>{name}</CardTitle>
                    <CardText>{this.state[text]}</CardText>
                </CardBody>
            </Col>
        )
    }

    render() {
        const csu = {
            color: '#086421',
            fontFamily: 'Open Sans'
        };

        return (
            <div className={'text-center'}>
                <Alert color={'success'}>
                    <CardBody>
                        <h1 style={csu}>Team WOPR</h1>
                        {this.teamImage()}
                        <Row>
                            {this.forEachTeamMember("Josh Keahey", "Josh")}
                            {this.forEachTeamMember("Minjie Shen", "Minjie")}
                            {this.forEachTeamMember("Shihao Huang", "Shihao")}
                            {this.forEachTeamMember("Lacey Willmann", "Lacey")}
                        </Row>
                    </CardBody>
                </Alert>
            </div>
        )
    }
}
