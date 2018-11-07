import React, {Component} from 'react'
import {Button, Card, CardBody, Table, Input, Form, Label} from "reactstrap";
import {request} from '../../api/api';
import AddButton from "./AddButton";


export class SearchBox extends Component {
    constructor(props) {
        super(props);
        this.handleSearch = this.handleSearch.bind(this);
        this.addButtons = this.addButtons.bind(this);
        this.addPlaces = this.addPlaces.bind(this);
        this.createTable = this.createTable.bind(this);
    }

    handleSearch(event) {
        event.preventDefault();
        this.props.updateSearch('places', []);
        let obj = this.props.search;
        if (obj.match !== "") {
            request(obj, 'search').then((Fi) => {
                this.props.updateSearch('places', Fi.places);
                this.props.updateSearch('found', Fi.found);
            });
        }
    }

    addPlaces() {
        const places =
            <tr key={"row_places"}>
                <th scope={"row"} key={"header_places"}>
                    {"Results"}
                </th>
                {
                    this.props.search.places.map((place) => <td>{place.name}</td>)
                }
            </tr>;
        return places;
    }

    addButtons() {
        const buttons =
            <tr key={"row_buttons"}>
                <th scope={"row"} key={"header_buttons"}>
                    {"Add to trip"}
                </th>
                {
                    this.props.search.places.map((place) =>
                        <td><AddButton newPlace={place} updateTrip={this.props.updateTrip}
                                       trip={this.props.trip} search={this.props.search}
                                       config={this.props.config}/>
                        </td>)
                }
            </tr>;
        return buttons;
    }

    createTable() {
        let table = [];
        table.push(this.addPlaces());
        table.push(this.addButtons());
        return table;
    }

    render() {
        return (
            <div className={'text-center'}>
                <Card>
                    <CardBody>
                        <Label>
                            Search for a new location
                        </Label>
                        <Form>
                            <Input type="text"
                                   placeholder=""
                                   style={{width: "100%"}}
                                   onChange={event => {
                                       this.props.updateSearch('match', event.target.value)
                                   }}/>
                        </Form>
                        <Button onClick={this.handleSearch} className='btn-dark btn-outline-dark'
                                type="button" size='lg'>Search</Button><br/><br/>
                        {<p>{this.props.search.found + " results found."}</p>}
                        <Table responsive hover>
                            <tbody className="searchTable">
                            {this.createTable()}
                            </tbody>
                        </Table>

                    </CardBody>
                </Card>
            </div>);
    }
}

export default SearchBox;
