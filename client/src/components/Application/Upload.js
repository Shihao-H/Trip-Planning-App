import React, {Component} from 'react'
import {Button} from "reactstrap";
import { Card, CardHeader, CardBody } from 'reactstrap'
import { request } from '../../api/api';

//import ReactDOM from 'react-dom'
class Upload extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.fileInput = React.createRef();
    }

    LoadFile(event) {
        let file=event.target.files[0];
        let fileReader = new FileReader();;
        fileReader.readAsText(file)
        fileReader.onload = (event) =>{
            console.log("file data", event.target.result);
            let obj=JSON.parse(event.target.result);
            console.log("Try and error ",obj);
        }
    }

    handleSubmit(event) {
        // event.preventDefault();
        alert(
            `Selected file - ${
                this.fileInput.current.files[0].name
                }`);
        let obj=this.props.handleSubmitHelper();
        // console.log(typeof this.props.state.trip);
        request(obj,'plan').then((FinalTrip)=>
        {
            console.log(typeof FinalTrip);
            console.log(FinalTrip);
        });
        // this.props.updateTrip()
    }

    render() {
        return (
            <div className="card">
                <CardBody>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Submit your trip!
                            <br/><br/>
                            <input type="file" ref={this.fileInput} onChange={(event)=>this.props.updateTrip("file",this.LoadFile(event))}/>
                            <small className="form-text text-muted">
                                Upload a file that contains a Trip object in the TFFI format.
                            </small>
                        </label>
                        <br/>
                        <Button className='btn-outline-dark' type="submit">Submit</Button>
                    </form>
                </CardBody>
            </div>
        );
    }

}

export default Upload;