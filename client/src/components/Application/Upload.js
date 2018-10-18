import React, {Component} from 'react'
import {Button, Row, Col} from "reactstrap";
import {CardBody} from 'reactstrap'
import {request} from '../../api/api';

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
            this.props.updateTrip('title', obj.title);
            this.props.updateOptions('units', obj.options.units);
            if(obj.options.units==="user defined")
            {
                this.props.updateOptions('unitRadius',obj.options.unitRadius);
                this.props.updateOptions('unitName',obj.options.unitName);
            }
            this.props.updateTrip('places', obj.places);
            this.props.updateTrip('map', obj.map);
        };
        this.props.updateSelected(new Map());
        this.props.updateSelectAll(false);
    }

    handleSubmit(event) {
        event.preventDefault();
        let obj=this.props.trip;
        if(this.props.otherTeams === null){
            request(obj,'plan').then((Fi)=>
            {
                this.props.updateTrip('distances',Fi.distances);
            });
        } else {
            request(obj,'plan', this.props.otherTeams, this.props.host).then((Fi)=>
            {
                this.props.updateTrip('distances',Fi.distances);
            });
        }
    }

    render() {
        return (
            <div className="card">
                <CardBody>
                    <Row>
                        <Col md={6}>
                            <label>
                                Submit your trip!
                                <br/>
                                <input type="file" ref={this.fileInput} onChange={this.LoadFile}/>
                                <small className="form-text text-muted">
                                    Upload a file that contains a Trip object in the TFFI format.
                                </small>
                            </label>
                            <br/>
                            <Button className='btn-outline-dark' type="submit" onClick={this.handleSubmit}>Plan</Button>
                        </Col>
                    </Row>
                </CardBody>
            </div>
        );
    }
}

export default Upload;
