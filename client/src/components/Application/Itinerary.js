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
        this.clickReverseButton = this.clickReverseButton.bind(this);
        this.clickChangeStartButton = this.clickChangeStartButton.bind(this);
        this.clear = this.clear.bind(this);
        this.table = this.table.bind(this);
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

    clickDeleteButton() {
        if (this.props.selectAll === true) {
            this.clear();
        } else {
            let temp = this.props.places;
            var i = temp.length;
            while (i--) {
                if (this.props.selected[temp[i].id] === true) {
                    temp.splice(i, 1);
                }
            }
            this.props.updateTrip('places', temp);
        }
    }

    clickReverseButton() {
        var reverse = this.props.places.reverse();
        this.props.updateTrip('places', reverse);
    }

    clickChangeStartButton() {
        let temp = this.props.places;
        var i = temp.length;
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


    table() {
        let table = [];

        const toggle =
            <th key='checkAll' className="checkPlace">
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
        const each =
            this.props.places.map((place) =>
                <td key={'check' + place.id} className="checkPlace">
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
        table.push(toggle);
        table.push(each);

        const rows =
            this.props.attributes.map((attribute) =>
                <tr key={"row_" + attribute}>
                    <th scope={"row"} key={"header_" + attribute}>
                        {attribute.charAt(0).toUpperCase() + attribute.slice(1)}</th>
                    {
                        this.props.places.map((place) => <td>{place[attribute]}</td>)
                    }
                </tr>
            );
        table.push(rows);

        const distance =
            <tr key={"row_leg"}>
                <th scope={"row"} key={"header_leg"}>Leg Distances</th>
                {this.props.distances.map((distance) => <td>{distance}</td>)}
            </tr>;
        table.push(distance);

        let totalDistance = [];
        for(let i = 0; i < this.props.distances.length; i ++){
            if(i == 0) totalDistance[0] = this.props.distances[0];
            else totalDistance[i] = totalDistance[i-1] + this.props.distances[i];
        }
        const total =
            <tr key={"row_total"}>
                <th scope={"row"} key={"header_total"}>Total Distances</th>
                {totalDistance.map((total) => <td>{total}</td>)}
            </tr>;
        table.push(total);

        return table;
    }

    render() {

        return (
            <div className={'text-center'}>
                <Button onClick={this.dropdown} size='lg'>Itinerary</Button>
                <Collapse isOpen={this.state.collapse}>
                    <Card>
                        <CardBody>
                            <p>{this.props.trip.title}</p>
                            <Table className="Table" responsive hover>
                                <tbody className="Body">{this.table()}</tbody>
                            </Table>
                            <Button size='lg' key={'delete_button'} className='btn-outline-dark delete-button'
                                    onClick={this.clickDeleteButton}>Delete selected location
                            </Button><br/><br/>
                            <Button size='lg' key={'reverse_button'} className='btn-outline-dark reverse-button'
                                    onClick={this.clickReverseButton}>Reverse trip
                            </Button><br/><br/>
                            <Button size='lg' key={'changeStart_button'} className='btn-outline-dark changeStart-button'
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
