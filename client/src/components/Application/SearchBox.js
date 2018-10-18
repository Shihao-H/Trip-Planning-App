import React, {Component} from 'react'
import {Button, Card, CardBody, Container, Table} from "reactstrap";
import {request} from '../../api/api';
import Optimization from "./Optimization";


export class SearchBox extends Component {
    constructor(props)
    {
        super(props);
        this.state = {
            place: {
                id: "",
                name: "",
                latitude: 0.00,
                longitude: 0.00
            }
        };
        this.handleSearch = this.handleSearch.bind(this);
    }

    handleSearch(event)
    {
        event.preventDefault();
        this.props.updateSearch('places',[]);
        let obj=this.props.search;
        if(obj.match!=="")
        {
            request(obj,'search').then((Fi)=>
            {
                this.props.updateSearch('places',Fi.places);
            });
        }
    }

    addPlace(i)
    {
        // let place = this.state.place;
        // place.id = this.props.search.places[i].id;
        // place.name = this.props.search.places[i].name;
        // place.latitude=this.props.search.places[i].latitude;
        // place.longitude=this.props.search.places[i].longitude;
        // this.setState(place);
        // let copy={
        //     id:this.state.place.id,
        //     name:this.state.place.name,
        //     latitude:this.state.place.latitude,
        //     longitude:this.state.place.longitude
        // }
        // let temp=this.props.trip.places;
        // temp.push(copy);
        // this.props.updateTrip('places',temp);

    }

    createTable()
    {
        if(this.props.search.places.length !== 0){
            let table = [];
            for (let i = 0; i < this.props.search.places.length; i++) {
                table.push(<tr key={'LOL' + i}><button type={"button"} onClick={this.addPlace()}>+</button>{this.props.search.places[i].name}</tr>);
            }
            return table;
        }
        else
        {
            let empty = [];
            empty.push(<tr>{' '}</tr>);
            return empty;
        }

    }

    render() {
        return (
            <div>
                <CardBody>
                    <form>
                        <br/>
                            <input type="text"
                            placeholder="place name"

                            style={{width: 300}}
                            onChange={event => {this.props.updateSearch('match', event.target.value)}}
                             />
                            <button onClick={this.handleSearch} className = 'btn-dark' type="button">Search</button>
                        <br/>
                            <Table className="Table" responsive>
                                <tbody className="Body">
                                {this.createTable()}
                                </tbody>
                            </Table>
                        <Container id="SearchBox"/>
                        <Optimization trip={this.props.trip} search={this.props.search} config={this.props.config}
                                      updateSearch={this.props.updateSearch} updateOptions={this.props.updateOptions}/>
                        <Container/>
                    </form>
                </CardBody>
            </div>);
    }
}