import React, {Component} from 'react'
import {Button} from "reactstrap";
import { Card, CardHeader, CardBody } from 'reactstrap'
import { request } from '../../api/api';

//import ReactDOM from 'react-dom'
class Upload extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.LoadFile=this.LoadFile.bind(this);
        this.fileInput = React.createRef();
    }

    LoadFile(event) {
        let file=event.target.files[0];
        let fileReader = new FileReader();
        fileReader.readAsText(file);
        fileReader.onload = (event) =>{

            let obj=JSON.parse(event.target.result);
            //this.props.trip=obj;
                this.props.updateTrip('title', obj.title);
                this.props.updateOptions('units', obj.options.units);
                if(obj.options.units==="user defined")
                {
                    this.props.updateOptions('unitRadius',obj.options.unitRadius);
                    this.props.updateOptions('unitName',obj.options.unitName);
                }
                this.props.updateTrip('places', obj.places);
                //this.props.updateTrip('distances', obj.distances);
                this.props.updateTrip('map', obj.map);

        }

    }

    handleSubmit(event) {
        let obj=this.props.trip;
        request(obj,'plan').then((Fi)=>
        {
            this.props.updateTrip('distances',Fi.distances);
        });
    }

    render() {
        return (
            <div className="card">
                <CardBody>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Submit your trip!
                            <br/><br/>
                            <input type="file" ref={this.fileInput} onChange={this.LoadFile}/>
                            <small className="form-text text-muted">
                                Upload a file that contains a Trip object in the TFFI format.
                            </small>
                        </label>
                        <br/>
                        <Button className='btn-outline-dark' type="submit">Plan</Button>
                    </form>
                </CardBody>
            </div>
        );
    }

}

export default Upload;


// onChange={(event)=>this.props.updateTrip("file",this.LoadFile(event))}
