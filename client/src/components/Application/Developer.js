import React, {Component} from 'react'
import {Card, CardBody, Row, Col, CardImg, CardTitle, CardText} from 'reactstrap'
import Team from '../../../../server/src/main/resources/Team.jpg';

export default class Developer extends Component {
    constructor(props) {
        super(props);
        this.teamImage = this.teamImage.bind(this);
        this.forEachTeamMember = this.forEachTeamMember.bind(this);
        this.state = {
            Josh: "Josh's bio (picture above)",
            Lacey: "Lacey's bio (picture above)",
            Minjie: "Minjie's bio (picture above)",
            Shihao: "Shihao's bio (picture above) "
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
        return (
            <div><Card><CardBody>
                {this.teamImage()}
                <Row>
                    {this.forEachTeamMember("Josh Keahey", "Josh")}
                    {this.forEachTeamMember("Lacey Willmann", "Lacey")}
                    {this.forEachTeamMember("Minjie Shen", "Minjie")}
                    {this.forEachTeamMember("Shihao Huang", "Shihao")}
                </Row>
            </CardBody></Card></div>
        )
    }
}