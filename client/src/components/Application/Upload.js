import React, {Component} from 'react'
import {CardBody,Card,Button,Row,Col} from 'reactstrap'
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
            request(obj,'plan', this.props.otherTeams, this.props.host + ".cs.colostate.edu").then((Fi)=>
            {
                this.props.updateTrip('distances',Fi.distances);
            });
        }
    }

    render() {
        return (
            <Row>
                <Col>
            <label><br/>
                Submit your trip!
                <br/>
                <input type="file" maxLength={"40px"} ref={this.fileInput} onChange={this.LoadFile}/>
                <small className="form-text text-muted">
                    Upload a file that contains a Trip<br/>object in the TFFI format.
                </small>
            </label>
            <br/>
            <Button
                size='lg'
                className='btn-dark btn-outline-dark'
                type="submit"
                onClick={this.handleSubmit}
            >Plan&nbsp;</Button>
                </Col>
            </Row>
        );
    }
}

export default Upload;
