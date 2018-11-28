import React, {Component} from 'react';
import {TabContent, TabPane, Nav, NavItem, NavLink, Card, Button, CardTitle, CardText, Row, Col} from 'reactstrap';
import classnames from 'classnames';
import Info from "./Info";
import OptionPanel from "./OptionPanel";
import DistanceCal from "./DistanceCal";
import Developer from "./Developer"

class HomePage extends Component {
    constructor(props) {
        super(props);

        this.toggle = this.toggle.bind(this);
        this.generateNavItem = this.generateNavItem.bind(this);
        this.state = {
            activeTab: '1'
        };
    }

    toggle(tab) {
        if (this.state.activeTab !== tab) {
            this.setState({
                activeTab: tab
            });
        }
    }

    generateNavItem(index, tab) {
        return (
            <NavItem>
                <NavLink
                    className={classnames({active: this.state.activeTab === index})}
                    onClick={() => {
                        this.toggle(index);
                    }}>
                    {tab}
                </NavLink>
            </NavItem>
        )
    }

    generateNavContentForOptions() {
        return (
            <TabPane tabId="2">
                <OptionPanel config={this.props.config} display={this.props.display} options={this.props.options}
                             updateAttributes={this.props.updateAttributes} updateOptions={this.props.updateOptions}
                             updateHost={this.props.updateHost} updateOtherTeams={this.props.updateOtherTeams}
                             updateInterOperate={this.props.updateInterOperate}/>
            </TabPane>
        )
    }

    render() {
        return (
            <div>
                <Nav tabs>
                    {this.generateNavItem('1', 'Info')}
                    {this.generateNavItem('2', 'Options')}
                    {this.generateNavItem('3', 'Calculator')}
                    {this.generateNavItem('4', 'Developer')}
                </Nav>
                <TabContent activeTab={this.state.activeTab}>
                    <TabPane tabId="1">
                        <Info/>
                    </TabPane>
                    {this.generateNavContentForOptions()}
                    <TabPane tabId="3">
                        <DistanceCal config={this.props.config}/>
                    </TabPane>
                    <TabPane tabId="4">
                        <Developer/>
                    </TabPane>
                </TabContent>
            </div>
        );
    }
}

export default HomePage;
