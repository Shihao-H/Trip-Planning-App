import {Table, Button, Card, CardBody, Collapse, Input, InputGroupAddon, InputGroup} from "reactstrap";
import React, {Component} from 'react'

class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: true
        };

        this.dropdown = this.dropdown.bind(this);
        this.toggle = this.toggle.bind(this);
        this.toggleSelectAll = this.toggleSelectAll.bind(this);
        this.clickDeleteButton = this.clickDeleteButton.bind(this);
        this.clickDeleteButtonHelper = this.clickDeleteButtonHelper.bind(this);
        this.clickReverseButton = this.clickReverseButton.bind(this);
        this.clickChangeStartButton = this.clickChangeStartButton.bind(this);
        this.clear = this.clear.bind(this);
        this.tableToggle = this.tableToggle.bind(this);
        this.tableEach = this.tableEach.bind(this);
        this.tableRow = this.tableRow.bind(this);
        this.tableDistance = this.tableDistance.bind(this);
        this.tableTotal = this.tableTotal.bind(this);
        this.createTable = this.createTable.bind(this);
        this.getUnit = this.getUnit.bind(this);
    }

    dropdown() {
        this.setState({collapse: !this.state.collapse})
    }

    toggle(value) {
        const newSelected = Object.assign({}, this.props.selected);
        newSelected[value] = !this.props.selected[value];
        this.props.updateSelected(newSelected);
    }

    toggleSelectAll() {
        let newSelected = {};

        if (this.props.selectAll === false) {
            this.props.places.forEach(place => {
                newSelected[place.id] = true;
            });
        }

        this.props.updateSelectAll(this.props.selectAll === false);
        this.props.updateSelected(newSelected);
    }


    clickDeleteButtonHelper(){
        let temp = this.props.places;
        let i = temp.length;
        while (i--) {
            if (this.props.selected[temp[i].id] === true) {
                temp.splice(i, 1);
            }
        }
        return temp;
    }

    clickDeleteButton() {
        if (this.props.selectAll === true) {
            this.clear();
        } else {
            this.props.updateTrip('places', this.clickDeleteButtonHelper());
        }
    }

    clickReverseButton() {
        let reverse = this.props.places.reverse();
        this.props.updateTrip('places', reverse);
    }

    clickChangeStartButton() {
        let temp = this.props.places;
        let i = temp.length;
        while (i--) {
            if (this.props.selected[temp[i].id] === true) {
                temp.splice(0, 0, temp[i]);
                temp.splice(i + 1, 1);
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

    clear() {
        this.props.updateTrip('places', []);
    }

    tableToggle(){
       return <th key='checkAll' className="checkPlace">
           <InputGroup>
               <InputGroupAddon addonType="prepend">
                   <Input addon
                          type="checkbox"
                          aria-label="Checkbox for following text input"
                          value={this.props.selectAll}
                          key={"checkAll"}
                          checked={this.props.selectAll}
                          onChange={this.toggleSelectAll}/>
               </InputGroupAddon>
           </InputGroup>
       </th>;
    }

    tableEach(){
        let i = 0;
       return this.props.places.map((place) =>
           <td key={'check' + place.id + i++} className="checkPlace">
               <InputGroupAddon addonType="prepend">
                   <Input addon
                          type="checkbox"
                          key={"check" + place.id}
                          checked={this.props.selected[place.id]}
                          value={place.id}
                          onChange={(event) => {
                              this.toggle(event.target.value)
                          }}/>
               </InputGroupAddon>
           </td>);
    }

    tableRow(){
        let i = 0;
       return this.props.attributes.map((attribute) =>
           <tr key={"row_" + attribute}>
               <th scope={"row"} key={"header_" + attribute}>
                   {attribute.charAt(0).toUpperCase() + attribute.slice(1)}</th>
               {
                   this.props.places.map((place) => <td
                       key={"place_" + i++}>{place[attribute]}</td>)
               }
           </tr>
       );
    }

    getUnit(){
        return (this.props.units === "user defined") ? this.props.unitName : this.props.units;
    }

    tableDistance() {
        let i = 0;
        return <tr key={"row_leg"}>
            <th scope={"row"} key={"header_leg"}>{"Leg Distances (" + this.getUnit() + ")"}</th>
            {this.props.distances.map((distance) => <td
                key={"distance_" + i++}>{distance}</td>)}
        </tr>;
    }

    tableTotal(){
        let i = 0;
        let totalDistance = [];
        for(let i = 0; i < this.props.distances.length; i ++){
            if(i === 0) totalDistance[0] = this.props.distances[0];
            else totalDistance[i] = totalDistance[i-1] + this.props.distances[i];
        }
        return <tr key={"row_total"}>
            <th scope={"row"} key={"header_total"}>{"Total Distances (" + this.getUnit() + ")"}</th>
            {totalDistance.map((total) => <td
                key={"total_" + i++}>{total}</td>)}
        </tr>;
    }

    createTable(){
        let table = [];
        const checkboxes = <tr key={"checkboxes"}>
            {this.tableToggle()}
            {this.tableEach()}
            </tr>;
        table.push(checkboxes);
        table.push(this.tableRow());
        table.push(this.tableDistance());
        table.push(this.tableTotal());
        return table;
    }

    render() {

        return (
            <div className={'text-center'}>
                <Button onClick={this.dropdown} size='lg'>Itinerary</Button>
                <Collapse isOpen={this.state.collapse}>
                    <Card>
                        <CardBody>
                            <p>{this.props.title}</p>
                            <Table className="Table" responsive hover>
                                <tbody className="Body">{this.createTable()}</tbody>
                            </Table>
                            <br/>
                            <Button key={'delete_button'} className='btn-outline-dark delete-button'
                                    onClick={this.clickDeleteButton}>Delete selected location
                            </Button><br/><br/>
                            <Button key={'reverse_button'} className='btn-outline-dark reverse-button'
                                    onClick={this.clickReverseButton}>Reverse trip
                            </Button><br/><br/>
                            <Button key={'changeStart_button'} className='btn-outline-dark changeStart-button'
                                    onClick={this.clickChangeStartButton}>Change selected to<br/>the starting location!
                            </Button>
                        </CardBody>
                    </Card>
                </Collapse>
            </div>
        );
    }
}

export default Itinerary;
