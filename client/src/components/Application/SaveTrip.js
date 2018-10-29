import React, {Component} from 'react'
import {Button, Card, CardBody,DownloadButton} from "reactstrap";

class SaveTrip extends Component{
    constructor(props){
        super(props);
        this.SaveTFFI=this.SaveTFFI.bind(this);
        this.SaveMap=this.SaveMap.bind(this);
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

    SaveMap()
    {
        let ele = document.createElement('a');
        ele.setAttribute('href',this.props.map);
        ele.setAttribute('download','download.svg');
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }
    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <Button
                            size='lg' className="btn-save btn-dark btn-outline-dark"
                            onClick={this.SaveTFFI} type="button"
                            >SaveTrip</Button>
                        <Button
                            size='lg' className="btn-save btn-dark btn-outline-dark"
                            onClick={this.SaveMap} type="button"
                        >SaveMap</Button>
                     </CardBody>
                </Card>
            </div>
        )
    };
}

export default SaveTrip;