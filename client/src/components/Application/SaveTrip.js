import React, {Component} from 'react'
import {Button} from "reactstrap";

class SaveTrip extends Component {
    constructor(props) {
        super(props);
        this.SaveTFFI = this.SaveTFFI.bind(this);
        this.SaveMap = this.SaveMap.bind(this);
    }

    SaveTFFI() {
        let title = this.props.title;
        let TFFI = JSON.stringify(this.props.trip);
        if (!title.endsWith(".json")) {
            title += ".json";
        }
        let ele = document.createElement('a');
        ele.setAttribute('href', 'data:text/json;charset=utf-8,' + encodeURIComponent(TFFI));
        ele.setAttribute('download', title);
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }

    SaveMap() {
        let title = this.props.title;
        let ele = document.createElement('a');
        ele.setAttribute('href', 'data:text/plain;charset=ytf-8,' + this.props.map);
        if (this.props.mapForOption === "svg") {
            ele.setAttribute('download', title + '.svg');
        } else {
            ele.setAttribute('download', title + '.kml');
        }
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }

    render() {
        return (
            <div>
                <Button
                    size='lg' className="btn-save btn-dark btn-outline-dark"
                    onClick={this.SaveTFFI} type="button"
                >SaveTrip</Button>
                <br/><br/>
                <Button
                    size='lg'
                    className="btn-save btn-dark btn-outline-dark"
                    onClick={this.SaveMap}
                    type="button">SaveMap</Button>
            </div>
        )
    }
}

export default SaveTrip;
