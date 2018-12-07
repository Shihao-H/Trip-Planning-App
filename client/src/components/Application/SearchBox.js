import React, {Component} from 'react'
import {Button, Card, CardBody, Table, Input, Form, Row, Col, Label} from "reactstrap";
import {request} from '../../api/api';
import AddButton from "./AddButton";
import AddAllButton from "./AddAllButton"

export class SearchBox extends Component {
    constructor(props) {
        super(props);
        this.state = {
            temp: [],
            returnNumber: 0
        };
        for (let filter of this.props.config.filters) {
            let values = {};
            for (let valueName of filter.values) {
                values[valueName] = false;
            }
            this.state.temp[filter.name] = {'values': values};
        }

        this.handleSearch = this.handleSearch.bind(this);
        this.addButtons = this.addButtons.bind(this);
        this.addPlaces = this.addPlaces.bind(this);
        this.createTable = this.createTable.bind(this);
        this.getTempValues = this.getTempValues.bind(this);
        this.getTempValuesHelper = this.getTempValuesHelper.bind(this);
        this.mapFilters = this.mapFilters.bind(this);
        this.check = this.check.bind(this);
    }

    getTempValuesHelper(filter){
        let values = [];
        for (let valueName in filter.values) {
            if (filter.values[valueName]) {
                values.push(valueName);
            }
        }
        return values;
    }

    getTempValues() {
        let filters = [];
        for (let filterName in this.state.temp) {
            let values = this.getTempValuesHelper(this.state.temp[filterName]);
            if (values.length > 0) {
                filters.push({'name': filterName, 'values': values});
            }
        }
        return filters;
    }

    check(event) {
        let filterName = event.target.value;
        let checked = event.target.checked;
        let valueName = event.target.name;
        this.state.temp[filterName].values[valueName] = checked;
    }

    mapFilters() {
        return this.props.config.filters.map((filter) =>
            <Col xs={"6"} key={filter.name} className={'text-left'}>
                        {filter.values.map((ConcreteValue) =>
                            <div key={ConcreteValue}>
                                <Label check key={ConcreteValue}>
                                    <Input name={ConcreteValue} type="checkbox" value={filter.name}
                                           onChange={this.check}/>
                                    {ConcreteValue.charAt(0).toUpperCase() + ConcreteValue.slice(1)}
                                </Label>
                            </div>
                        )}
            </Col>);
    }

    handleSearch() {
        this.props.updateSearch('places', []);
        let obj = Object.assign({}, this.props.search);
        obj.filters = this.getTempValues();
        if (obj.match !== "") {
            request(obj, 'search').then((Fi) => {
                let response = [];
                response = Fi.places;
                this.props.updateSearch('places', response);
                this.props.updateSearch('found', Fi.found);
                this.setState({returnNumber: response.length});
            });
        }
    }

    addPlaces() {
        let i = 0;
        return <tr key={"row_places"}>
            <th scope={"row"} key={"header_places"}>
                {"Results"}
            </th>
            {
                this.props.search.places.map((place) =>
                    <td key={"place_" + place.name + i++}>{place.name}</td>)
            }
        </tr>;
    }

    addButtons() {
        let i = 0;
        return <tr key={"row_buttons"}>
            <th scope={"row"} key={"header_buttons"}>
                <AddAllButton updateTrip={this.props.updateTrip}
                              trip={this.props.trip} search={this.props.search}
                              config={this.props.config}/>
            </th>
            {
                this.props.search.places.map((place) =>
                    <td key={"but_" + place.name + i++}>
                        <AddButton newPlace={place} updateTrip={this.props.updateTrip}
                                   trip={this.props.trip} search={this.props.search}
                                   config={this.props.config}/>
                    </td>)
            }
        </tr>;
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
                        <Label>Search for a new location</Label><br/>
                        <Form>
                            <Input type="text" placeholder="" style={{width: "100%"}}
                                   onChange={event => {
                                       this.props.updateSearch('match', event.target.value)}}/>
                        </Form>
                        <CardBody>
                            <Row>{this.mapFilters()}</Row>
                        </CardBody>
                        <Button onClick={this.handleSearch} className='btn-dark btn-outline-dark'
                                type="button" size='lg'>Search</Button><br/><br/>
                        {<p>{"Showing " + this.state.returnNumber + " of " +
                        this.props.search.found + " results."}</p>}
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
