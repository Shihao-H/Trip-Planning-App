import React, {Component} from 'react'
import {Button, ButtonGroup, Card, CardBody, Collapse} from "reactstrap";
import { Table } from 'reactstrap';


class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.state = {
          collapse: false,
        };
        this.dropdown = this.dropdown.bind(this);
        this.createTable = this.createTable.bind(this);
        // this.handleDelete = this.handleDelete.bind(this);
    }
    dropdown()
    {
        this.setState({collapse: !this.state.collapse})
    }
  
    createTable(){
        let table = [];
        let children = [];
        let total_distance = 0;
        if(this.props.trip.places.length === 0){
            children.push(<th key='origin'>{"Place"}</th>);
            children.push(<th key='destination'>{"Latitude"}</th>);
            children.push(<th key='leg distance'>{"Longitude"}</th>);
            children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='default header'>{children}</tr>);
            return table
        } else {
            children.push(<th key='origin'>{"Place"}</th>);
            children.push(<th key='destination'>{"Latitude"}</th>);
            children.push(<th key='leg distance'>{"Longitude"}</th>);
            children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='first row'>{children}</tr>);
            let cell = [];
            for (let i = 0; i < this.props.trip.places.length - 1; i++) {
                cell = [];
                cell.push(<th key={'origin' + i}>{this.props.trip.places[i].name}</th>);
                cell.push(<th key={'latitude' + i}>{this.props.trip.places[i].latitude}</th>);
                cell.push(<th key={'longitude' + i}>{this.props.trip.places[i].longitude}</th>);

                if (this.props.trip.distances.length === 0) {
                    cell.push(<th key={'total distance' + i}>{'0'}</th>);
                } else {
                    total_distance = total_distance + this.props.trip.distances[i];
                    cell.push(<th key={'total distance' + i}>{total_distance}</th>);
                }
                table.push(<tr key={'row' + i}>{cell}</tr>);
            }

            cell=[];
            cell.push(<th key="origin last">{this.props.trip.places[this.props.trip.places.length - 1].name}</th>);
            cell.push(<th key="latitude last">{this.props.trip.places[this.props.trip.places.length - 1].latitude}</th>);
            cell.push(<th key="longitude last">{this.props.trip.places[this.props.trip.places.length - 1].longitude}</th>);

            if (this.props.trip.distances.length === 0) {
                cell.push(<th key={'total distance'}>{'0'}</th>);
            } else {
                total_distance = total_distance + this.props.trip.distances[this.props.trip.places.length - 1];
                cell.push(<th key={'total distance'}>{total_distance}</th>);
            }
            table.push(<tr key={'last row'}>{cell}</tr>);
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
                        </CardBody>
                    </Card>
                </Collapse>
            </div>
    );

    }

}
export default Itinerary;
