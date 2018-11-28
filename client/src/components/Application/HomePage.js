import React, {Component} from 'react';
import {TabContent, TabPane, Nav, NavItem, NavLink, Card, CardBody, Row, Col} from 'reactstrap';
import classnames from 'classnames';
import Info from "./Info";
import OptionPanel from "./OptionPanel";
import DistanceCal from "./DistanceCal";
import Developer from "./Developer"
import MapSvg from "./MapSvg";
import Itinerary from "./Itinerary";
import Attributes from "./Attributes";

class HomePage extends Component {
    constructor(props) {
        super(props);

        this.toggle = this.toggle.bind(this);
        this.generateNavItem = this.generateNavItem.bind(this);
        this.generateNavContentForPlan = this.generateNavContentForPlan.bind(this);
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
            <TabPane tabId="3">
                <OptionPanel config={this.props.config} display={this.props.display} options={this.props.options}
                             updateAttributes={this.props.updateAttributes} updateOptions={this.props.updateOptions}
                             updateHost={this.props.updateHost} updateOtherTeams={this.props.updateOtherTeams}
                             updateInterOperate={this.props.updateInterOperate}/>
            </TabPane>
        )
    }

    generateNavContentForPlan() {
        return (
            <TabPane tabId="2">
                <MapSvg map={this.props.map} mapForOption={this.props.mapForOption}
                        places={this.props.places}/>
                <Itinerary selected={this.props.selected} updateSelected={this.props.updateSelected}
                           selectAll={this.props.selectAll} updateSelectAll={this.props.updateSelectAll}
                           places={this.props.places} attributes={this.props.attributes}
                           distances={this.props.distances} title={this.props.title}
                           updateTrip={this.props.updateTrip}/>
                <Attributes config={this.props.config} display={this.props.display}
                            updateAttributes={this.props.updateAttributes}/>
            </TabPane>
        )
    }

    render() {
        return (
            <div>
                <Nav tabs>
                    {this.generateNavItem('1', 'Info')}
                    {this.generateNavItem('2', 'Plan')}
                    {this.generateNavItem('3', 'Options')}
                    {this.generateNavItem('4', 'Calculator')}
                    {this.generateNavItem('5', 'Developer')}
                </Nav>
                <TabContent activeTab={this.state.activeTab}>
                    <TabPane tabId="1">
                        <Info/>
                    </TabPane>
                    {this.generateNavContentForPlan()}
                    {this.generateNavContentForOptions()}
                    <TabPane tabId="4">
                        <DistanceCal config={this.props.config}/>
                    </TabPane>
                    <TabPane tabId="5">
                        <Developer/>
                    </TabPane>
                </TabContent>
            </div>
        );
    }
}

export default HomePage;
