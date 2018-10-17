import {Button, Card, CardBody, Col, Collapse, Input} from "reactstrap";
import React, {Component} from 'react'
import { Table } from 'reactstrap';

class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: true,
            displayAttributes: false,
            display: {
                Name: true,
                Id: true,
                Latitude: true,
                Longitude: true,
                Leg: true,
                Total: true
            }
        };

        this.dropdown = this.dropdown.bind(this);
        this.createTable = this.createTable.bind(this);
        this.toggle = this.toggle.bind(this);
        this.toggleSelectAll = this.toggleSelectAll.bind(this);
        this.clickDeleteButton = this.clickDeleteButton.bind(this);
        this.clickReverseButton = this.clickReverseButton.bind(this);
        this.clickAttributesButton = this.clickAttributesButton.bind(this);
        this.clear = this.clear.bind(this);
        this.uncheck = this.uncheck.bind(this);
    }

    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }

    toggle(value){
        const newSelected = Object.assign({}, this.props.selected);
        newSelected[value] = !this.props.selected[value];
        this.props.updateSelected(newSelected);
    }

    toggleSelectAll(){
        let newSelected = {};

        if (this.props.selectAll === false) {
            this.props.trip.places.forEach(place => {
                newSelected[place.id] = true;
            });
        }

        this.props.updateSelectAll(this.props.selectAll === false);
        this.props.updateSelected(newSelected);
    }

    clickDeleteButton(){
        if(this.props.selectAll === true){
            this.clear();
        } else {
            let temp = this.props.trip.places;
            var i = temp.length;
            while(i--){
                if(this.props.selected[temp[i].id] === true){
                    temp.splice(i,1);
                }
            }
            this.props.updateTrip('places', temp);
        }
    }

    clickReverseButton() {
        var reverse = this.props.trip.places.reverse();
        this.props.updateTrip('places',reverse);
    }

    clickAttributesButton() {
        this.setState({displayAttributes: !this.state.displayAttributes});
    }

    clear()
    {
        this.props.updateTrip('places',[]);
    }

    uncheck(value){
        let display = this.state.display;
        display[value] = !display[value];
        this.setState({display: display});
        this.props.updateSelectAll(false);
    }

    createTable(){
        let table = [];
        let children = [];
        let total_distance = 0;
        if(this.props.trip.places.length === 0){
            if(this.state.display.Name === true)
                children.push(<th key='default_destination'>{"Place"}</th>);
            if(this.state.display.Id === true)
                children.push(<th key='default_id'>{"ID"}</th>);
            if(this.state.display.Latitude === true)
                children.push(<th key='default_latitude'>{"Latitude"}</th>);
            if(this.state.display.Longitude === true)
                children.push(<th key='default_longitude'>{"Longitude"}</th>);
            if(this.state.display.Leg === true)
                children.push(<th key='default_leg distance'>{"Leg distance"}</th>);
            if(this.state.display.Total === true)
                children.push(<th key='default_total distance'>{"Total distance"}</th>);
            table.push(<tr key='default header'>{children}</tr>);
            return table
        } else {
            if(this.state.display.Name === true || this.state.display.Id === true
                || this.state.display.Latitude === true || this.state.display.Longitude === true
                || this.state.display.Leg === true || this.state.display.Total === true)
            {
                children.push(<th key='checkAll' className="checkPlace">
                    <form>
                        <input type="checkbox" name="checkAll" id={"checkAll"} checked={this.props.selectAll} value={this.props.selectAll}
                               onChange={this.toggleSelectAll}/>
                    </form></th>);
            }
            if(this.state.display.Name === true)
                children.push(<th key='destination'>{"Place"}</th>);
            if(this.state.display.Id === true)
                children.push(<th key='id'>{"ID"}</th>);
            if(this.state.display.Latitude === true)
                children.push(<th key='latitude'>{"Latitude"}</th>);
            if(this.state.display.Longitude === true)
                children.push(<th key='longitude'>{"Longitude"}</th>);
            if(this.state.display.Leg === true)
                children.push(<th key='leg distance'>{"Leg distance"}</th>);
            if(this.state.display.Total === true)
                children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='first row'>{children}</tr>);

            let cell = [];
            for (let i = 0; i < this.props.trip.places.length; i++) {
                cell = [];
                if(this.state.display.Name === true || this.state.display.Id === true
                    || this.state.display.Latitude === true || this.state.display.Longitude === true
                    || this.state.display.Leg === true || this.state.display.Total === true)
                {
                    cell.push(<th key={'check'+i} className="checkPlace">
                        <form>
                            <input type="checkbox" name="checkOne" id={"checkOne" + i} checked={this.props.selected[this.props.trip.places[i].id]}
                                   value={this.props.trip.places[i].id}
                                   onChange={(event) => {this.toggle(event.target.value)}}/>
                        </form></th>);
                }
                if(this.state.display.Name === true)
                    cell.push(<th key={'destination' + i}>{this.props.trip.places[i].name}</th>);
                if(this.state.display.Id === true)
                    cell.push(<th key={'id' + i}>{this.props.trip.places[i].id}</th>);
                if(this.state.display.Latitude === true)
                    cell.push(<th key={'latitude' + i}>{this.props.trip.places[i].latitude}</th>);
                if(this.state.display.Longitude === true)
                    cell.push(<th key={'longitude' + i}>{this.props.trip.places[i].longitude}</th>);

                if (this.props.trip.distances.length === 0) {
                    if(this.state.display.Leg === true)
                        cell.push(<th key={'leg distance' + i}>{'0'}</th>);
                    if(this.state.display.Total === true)
                        cell.push(<th key={'total distance' + i}>{'0'}</th>);
                } else {
                    if(this.state.display.Leg === true)
                        cell.push(<th key={'leg distance' + i}>{this.props.trip.distances[i]}</th>);
                    total_distance = total_distance + this.props.trip.distances[i];
                    if(this.state.display.Total === true)
                        cell.push(<th key={'total distance' + i}>{total_distance}</th>);
                }

                table.push(<tr key={'row' + i}>{cell}</tr>);
            }

            return table;
        }
    }

    render()
    {
        return (
            <div>
                <Button onClick={this.dropdown} className = 'btn-dark' size='lg'>Itinerary</Button>
                <Collapse isOpen = {this.state.collapse}>
                    <Card>
                        <CardBody>
                            <Table className="Table" responsive>
                                <tbody className="Body">
                                {this.createTable()}
                                </tbody>
                            </Table>
                            <Button
                                key={'delete_button'}
                                className='btn-outline-dark delete-button'
                                onClick={this.clickDeleteButton}
                            >
                                Delete
                            </Button>
                            <Button
                                key={'reverse_button'}
                                className='btn-outline-dark reverse-button'
                                onClick={this.clickReverseButton}
                            >
                                Reverse
                            </Button>
                            <Button
                                key={'attributes_button'}
                                className='btn-outline-dark attributes-button'
                                onClick={this.clickAttributesButton}
                            >
                                Attributes
                            </Button>
                            {this.state.displayAttributes && (
                                <div>
                                    <Col>
                                        <br/>
                                        <p> Please choose attributes to display: </p>
                                        <form>
                                            <Input type="checkbox" id={"check_name"} checked={this.state.display.Name} value={'Name'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Name </label><br/>
                                            <Input type="checkbox" id={"check_id"} checked={this.state.display.Id} value={'Id'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Id </label><br/>
                                            <Input type="checkbox" id={"check_latitude"} checked={this.state.display.Latitude} value={'Latitude'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Latitude </label><br/>
                                            <Input type="checkbox" id={"check_longitude"} checked={this.state.display.Longitude} value={'Longitude'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Longitude </label><br/>
                                            <Input type="checkbox" id={"check_leg"} checked={this.state.display.Leg} value={'Leg'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Leg distances </label><br/>
                                            <Input type="checkbox" id={"check_total"} checked={this.state.display.Total} value={'Total'}
                                                   onChange = {event => {this.uncheck(event.target.value)}}/>
                                            <label> Total distances </label><br/>
                                        </form>
                                    </Col>
                                    <br/>
                                </div>)
                            }
                        </CardBody>
                    </Card>
                </Collapse>
            </div>
        );

    }

}

export default Itinerary;
