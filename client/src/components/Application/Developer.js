import React, {Component} from 'react'
import {Card, CardBody, Row, Col, CardImg, CardTitle, CardText} from 'reactstrap'
import Team from '../../../../server/src/main/resources/Team.jpg';

export default class Developer extends Component {
    constructor(props) {
        super(props);
        this.teamImage = this.teamImage.bind(this);
        this.forEachTeamMember = this.forEachTeamMember.bind(this);
        this.state = {
            Josh: "Josh is currently a senior in CS, and finished his math minor last semester. He has been going to school "
                + "for a while now, as it took him a long time to figure out what he wanted to do with his life. After working some..."
                + " not super exciting jobs for a while, he decided to moved to China for 6 months in 2013. The trip changed his "
                + "life, and helped him decide to come back to the states and try to get a job in the gaming industry. This major "
                + "has not always been the easiest for Josh, but it has undoubtedly been a rewarding and fun experience for him. In "
                + "other walks of life, he loves running, music, board games, making people laugh, and (it should go without saying) "
                + "video games. Also, if there is some piece of random knowledge or pop culture reference, there is a high chance that "
                + "he will know it. The amount of utterly pointless information in his head is impressive, if not a little sad.",
            Lacey: "Lacey is a senior in the Computer Science program. She is returning to school after a disability "
                + "forced her to retire. She was previously employed by the federal government and worked as as supervisor "
                + "for large teams. Before that, she worked in the veterinary medicine field as a supervisory veterinary "
                + "technician. Animals medicine and behavior is her passion. She has 15 plus years working with animal rescue "
                + "groups in the southeast. She served as chief medical, behavioral, and sanitation officer for several small "
                + "rescue organizations. She managed an animal triage center for animals coming out of Mississippi and Louisiana "
                + "following hurrican Katrina. She has been trained in disaster response and incident command. She has a love "
                + "for science and that is why she chose computer science as her second career choice. Lacey loves spending time "
                + "outdoors when she is not busy with school. She runs away to the mountains every chance she gets. She is also "
                + "an avid cyclist and hopes to complete her first century ride in the next year. Despite having a physical "
                + "disability, Lacey is always up for a physical challenge. Her biggest accomplishment since being diagnosed "
                + "with a degenerative disease of the spine is that she completed the Manitou Incline. She wants to do it again. "
                + "Lacey also tutors CS students in her free time.",
            Minjie: "Minjie is a senior Computer Science student, minoring in Math. She is a transfer student, "
                + "this is her third year in Colorado State University. She has studied in East China Normal University, "
                + "China for two years and then transferred to CSU. She was an Electrical Engineering student and "
                + "she has studied EE for 4 years. She tried to study both EE and CS at a time, but she just failed "
                + "because it was so challenging to her. She realized that she preferred CS to EE, so she kept her "
                + "CS major and got rid of the EE major. She is so glad to work with all of the other WOPRers. She hopes "
                + "WOPR could get everything done perfectly.",
            Shihao: "He is a senior in both CS and Stat. Don't worry about prouncing his name wrong, He is really fine with that. "
                + "His English is good most of time but when it comes to some new concepts, local adage or sayings he doesn't know, "
                + "he still needs to use his phone dictionary. If some day you find him suddenly being silent when communicating with you, "
                + "he may just need to organize his language. He considers himself an independent and self-displined person, "
                + "he has procrastination but usually he will set himself a deadline before the true deadline. He has two siblings, "
                + "they are the most invaluable gifts that he received from his parents. Travelling and communicating with people "
                + "from different background fasinate him. It is too young for him to write a bio(LOL), but as a materilist, he hopes "
                + "he can earn it every single day of his life."
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