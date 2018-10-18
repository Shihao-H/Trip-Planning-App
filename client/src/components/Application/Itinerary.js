import {Button, Card, CardBody, Collapse} from "reactstrap";
import React, {Component} from 'react'
import { Table } from 'reactstrap';

class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: true
        };

        this.dropdown = this.dropdown.bind(this);
        this.createTable = this.createTable.bind(this);
        this.toggle = this.toggle.bind(this);
        this.toggleSelectAll = this.toggleSelectAll.bind(this);
        this.clickDeleteButton = this.clickDeleteButton.bind(this);
        this.clickReverseButton = this.clickReverseButton.bind(this);
        this.clickChangeStartButton = this.clickChangeStartButton.bind(this);
        this.clear = this.clear.bind(this);
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

    clickChangeStartButton(){
        let temp = this.props.trip.places;
        var i = temp.length;
        while(i--){
            if(this.props.selected[temp[i].id] === true){
                temp.splice(0,0,temp[i]);
                temp.splice(i+1,1);
                const newSelected = Object.assign({}, this.props.selected);
                newSelected[temp[0].id] = !this.props.selected[temp[0].id];
                this.props.updateSelected(newSelected);
                break;
            }
        }
        let dummy = this.props.trip.distances;
        dummy.push(0);
        this.props.updateTrip('places', temp);
    }

    clear()
    {
        this.props.updateTrip('places',[]);
    }

    createTable(){
        let table = [];
        let children = [];
        let total_distance = 0;
        if(this.props.trip.places.length === 0){
            if(this.props.display.Name === true)
                children.push(<th key='default_destination'>{"Place"}</th>);
            if(this.props.display.Id === true)
                children.push(<th key='default_id'>{"ID"}</th>);
            if(this.props.display.Latitude === true)
                children.push(<th key='default_latitude'>{"Latitude"}</th>);
            if(this.props.display.Longitude === true)
                children.push(<th key='default_longitude'>{"Longitude"}</th>);
            if(this.props.display.Leg === true)
                children.push(<th key='default_leg distance'>{"Leg distance"}</th>);
            if(this.props.display.Total === true)
                children.push(<th key='default_total distance'>{"Total distance"}</th>);
            table.push(<tr key='default header'>{children}</tr>);
            return table
        } else {
            if(this.props.display.Name === true || this.props.display.Id === true
                || this.props.display.Latitude === true || this.props.display.Longitude === true
                || this.props.display.Leg === true || this.props.display.Total === true)
            {
                children.push(<th key='checkAll' className="checkPlace">
                    <form>
                        <input type="checkbox" name="checkAll" id={"checkAll"} checked={this.props.selectAll} value={this.props.selectAll}
                               onChange={this.toggleSelectAll}/>
                    </form></th>);
            }
            if(this.props.display.Name === true)
                children.push(<th key='destination'>{"Place"}</th>);
            if(this.props.display.Id === true)
                children.push(<th key='id'>{"ID"}</th>);
            if(this.props.display.Latitude === true)
                children.push(<th key='latitude'>{"Latitude"}</th>);
            if(this.props.display.Longitude === true)
                children.push(<th key='longitude'>{"Longitude"}</th>);
            if(this.props.display.Leg === true)
                children.push(<th key='leg distance'>{"Leg distance"}</th>);
            if(this.props.display.Total === true)
                children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='first row'>{children}</tr>);

            let cell = [];
            for (let i = 0; i < this.props.trip.places.length; i++) {
                cell = [];
                if(this.props.display.Name === true || this.props.display.Id === true
                    || this.props.display.Latitude === true || this.props.display.Longitude === true
                    || this.props.display.Leg === true || this.props.display.Total === true)
                {
                    cell.push(<th key={'check'+i} className="checkPlace">
                        <form>
                            <input type="checkbox" name="checkOne" id={"checkOne" + i} checked={this.props.selected[this.props.trip.places[i].id]}
                                   value={this.props.trip.places[i].id}
                                   onChange={(event) => {this.toggle(event.target.value)}}/>
                        </form></th>);
                }
                if(this.props.display.Name === true)
                    cell.push(<th key={'destination' + i}>{this.props.trip.places[i].name}</th>);
                if(this.props.display.Id === true)
                    cell.push(<th key={'id' + i}>{this.props.trip.places[i].id}</th>);
                if(this.props.display.Latitude === true)
                    cell.push(<th key={'latitude' + i}>{this.props.trip.places[i].latitude}</th>);
                if(this.props.display.Longitude === true)
                    cell.push(<th key={'longitude' + i}>{this.props.trip.places[i].longitude}</th>);

                if (this.props.trip.distances.length === 0) {
                    if(this.props.display.Leg === true)
                        cell.push(<th key={'leg distance' + i}>{'0'}</th>);
                    if(this.props.display.Total === true)
                        cell.push(<th key={'total distance' + i}>{'0'}</th>);
                } else {
                    if(this.props.display.Leg === true)
                        cell.push(<th key={'leg distance' + i}>{this.props.trip.distances[i]}</th>);
                    total_distance = total_distance + this.props.trip.distances[i];
                    if(this.props.display.Total === true)
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
                <Button onClick={this.dropdown} size='lg'>Itinerary</Button>
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
                                key={'changeStart_button'}
                                className='btn-outline-dark changeStart-button'
                                onClick={this.clickChangeStartButton}
                            >
                                Change it to the starting location!
                            </Button>
                        </CardBody>
                    </Card>
                </Collapse>
            </div>
        );

    }

}

export default Itinerary;
