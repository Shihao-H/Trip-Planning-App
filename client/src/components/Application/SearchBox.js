import React, {Component} from 'react'
import {Button, Card, CardBody, Container, Table, Input} from "reactstrap";
import {request} from '../../api/api';
import Optimization from "./Optimization";
import AddButton from "./AddButton";


export class SearchBox extends Component {
    constructor(props)
    {
        super(props);
        this.handleSearch = this.handleSearch.bind(this);
        this.createTable = this.createTable.bind(this);
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

    createTable()
    {
        if(this.props.search.places.length !== 0){
            let table = [];
            for (let i = 0; i < this.props.search.places.length; i++) {
                table.push(<tr key={'search_place' + i}><td>{this.props.search.places[i].name}</td>
                            <td><AddButton
                                newPlace={this.props.search.places[i]}
                                updateTrip={this.props.updateTrip}
                                trip={this.props.trip}
                                search={this.props.search}
                                config={this.props.config}
                            />
                            </td></tr>);
            }
            return table;
        }
        else
        {
            let empty = [];
            empty.push(<tr key={'empty_string'}><th>{' '}</th></tr>);
            return empty;
        }

    }

    render() {
        return (
            <Card>
                <CardBody>
                    <form>
                        <label>
                            Search for a new location
                            <br/>
                            <br/>
                            <Input
                                type="text"
                                placeholder="Place name"
                                style={{width: "75%"}}
                                onChange={event => {this.props.updateSearch('match', event.target.value)}}
                            />
                        <br/>
                            <Button onClick={this.handleSearch} className = 'btn-dark btn-outline-dark' type="button" size='lg'>Search</Button>
                        <br/>
                            <Table className="Table" responsive>
                                <tbody className="Body">
                                {this.createTable()}
                                </tbody>
                            </Table>
                        </label>
                    </form>
                </CardBody>
            </Card>);
    }
}
export default SearchBox;