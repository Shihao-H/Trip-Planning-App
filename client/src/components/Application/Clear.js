import React, {Component} from 'react'
import {Button} from "reactstrap";

class Clear extends Component{
    constructor(props){
        super(props);
        this.clear=this.clear.bind(this);
    }

    clear()
    {
        this.props.updateTrip('title',"");
        this.props.updateTrip('places',[]);
        this.props.updateTrip('distances',[]);
        this.props.updateTrip('map','');
        this.props.updateTrip('title',"");
        this.props.updateOptions('optimization', 'none');
        this.props.updateOptions('unitName', "");
        this.props.updateOptions('unitRadius', 0.00);
        this.props.updateSearch('match',"");
        this.props.updateSearch('places',[]);
        this.props.updateSearch('limit',0);
        this.props.LoadFile(null);
    }


    render() {
        return (<Button className="btn-save" onClick={this.clear} type="button">Clear</Button>)
    };
}

export default Clear;