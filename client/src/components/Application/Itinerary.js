import React, {Component} from 'react'
import {Card, CardBody} from "reactstrap";


class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.createTable = this.createTable.bind(this);
    }
    createTable(){
        let table = [];
        let children = [];
        let total_distance = 0;
        if(this.props.trip.places.length === 0){
            children.push(<th key='origin'>{"Origin city"}</th>);
            children.push(<th key='destination'>{"Destination city"}</th>);
            children.push(<th key='leg distance'>{"Leg distance"}</th>);
            children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='default header'>{children}</tr>);
            return table
        } else {
            children.push(<th key='origin'>{"Origin city"}</th>);
            children.push(<th key='destination'>{"Destination city"}</th>);
            children.push(<th key='leg distance'>{"Leg distance"}</th>);
            children.push(<th key='total distance'>{"Total distance"}</th>);
            table.push(<tr key='first row'>{children}</tr>);
            let cell = [];
            for (let i = 0; i < this.props.trip.places.length - 1; i++) {
                cell = [];
                cell.push(<th key={'origin' + i}>{this.props.trip.places[i].name}</th>);
                cell.push(<th key={'destination' + i}>{this.props.trip.places[i + 1].name}</th>);

                if (this.props.trip.distances.length === 0) {
                    cell.push(<th key={'leg distance' + i}>{'0'}</th>);
                    cell.push(<th key={'total distance' + i}>{'0'}</th>);
                } else {
                    cell.push(<th key={'leg distance' + i}>{this.props.trip.distances[i]}</th>);
                    total_distance = total_distance + this.props.trip.distances[i];
                    cell.push(<th key={'total distance' + i}>{total_distance}</th>);
                }
                table.push(<tr key={'row' + i}>{cell}</tr>)
            }

            cell=[];
            cell.push(<th key="origin last">{this.props.trip.places[this.props.trip.places.length - 1].name}</th>);
            cell.push(<th key="destination last">{this.props.trip.places[0].name}</th>);

            if (this.props.trip.distances.length === 0) {
                cell.push(<th key={'last leg distance'}>{'0'}</th>);
                cell.push(<th key={'total distance'}>{'0'}</th>);
            } else {
                cell.push(<th key="last leg distance">{this.props.trip.distances[this.props.trip.places.length - 1]}</th>);
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
            <Card>
                <CardBody>
                    <form className="Form">
                        <table className="Table">
                            <tbody className="Body">
                                {this.createTable()}
                            </tbody>
                        </table>
                    </form>
                </CardBody>
            </Card>);
    }

}
export default Itinerary;
