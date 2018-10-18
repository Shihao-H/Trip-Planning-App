import React, {Component} from 'react'
import {Button} from "reactstrap";

class SaveTrip extends Component{
    constructor(props){
        super(props);
        this.SaveTFFI=this.SaveTFFI.bind(this);
    }

    SaveTFFI()
    {
        let title = this.props.trip.title;
        let TFFI = JSON.stringify(this.props.trip);
        if(!title.endsWith(".json"))
        {
            title += ".json";
        }

        let ele = document.createElement('a');
        ele.setAttribute('href','data:text/plain;charset=ytf-8,' + encodeURIComponent(TFFI));
        ele.setAttribute('download',title);
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }


    render() {
        return (<Button className="btn-save" onClick={this.SaveTFFI} type="button">Save</Button>)
    };
}

export default SaveTrip;