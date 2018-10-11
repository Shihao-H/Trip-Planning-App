import React, {Component} from 'react'
import {Button, Card, CardBody, CardImg, Collapse, Table} from "reactstrap";
import {request} from '../../api/api';


export class SearchBox extends Component {
    constructor(props)
    {
        super(props);
        this.handleSearch = this.handleSearch.bind(this);
    }

    handleSearch(event)
    {
        event.preventDefault();
        let obj=this.props.search;
        request(obj,'search').then((Fi)=>
        {
            this.props.updateSearch('places',Fi.places);
        });
    }

    createTable()
    {
        if(this.props.search.places.length !== 0){
            let table = [];
            for (let i = 0; i < this.props.search.places.length; i++) {
                table.push(<tr>{this.props.search.places[i].name}</tr>);
            }
            return table;
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
                        <br/>
                        <Button onClick={this.handleSearch} className = 'btn-dark' size='lg'>Search</Button>
                            <Table className="Table" responsive>
                                <tbody className="Body">
                                {this.createTable()}
                                </tbody>
                            </Table>
                    </form>
                </CardBody>
            </div>);
    }
}