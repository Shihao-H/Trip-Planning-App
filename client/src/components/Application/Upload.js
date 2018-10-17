import React, {Component} from 'react'
import {Button, Row, Col} from "reactstrap";
import {CardBody} from 'reactstrap'
import {request} from '../../api/api';
import SaveTrip from './SaveTrip';
import Add from "./Add";
import Clear from "./Clear";


class Upload extends Component {
    /* This is a constructor for upload.
     * otherTeams: Users are supposed to give a port number from other teams as input,
     * in order to enable clients and servers to inter-operate.
     */
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.LoadFile=this.LoadFile.bind(this);
        this.updateOtherTeams=this.updateOtherTeams.bind(this);
        this.fileInput = React.createRef();
        this.state = {otherTeams: null};
    }

    /* This function updates the value of otherTeam (a port number from other teams).
     * A event is a user typing something in the input text field.
     */
    updateOtherTeams(event) {
        this.setState({otherTeams: event.target.value});

    }

    /* This function loads the file using a fileReader.
     * This function will be called if we click the load button.
     * Parse the file to a JSON object and then update the trip object (in application).
     * This is related to lifting states up.
     */
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

        }

    }
    
    /* This function plans the trip which means it calculates the distances from places.
     * This function will be called if we click the plan button.
     * By default, it runs on our server (localhost 8088/ localhost 31403/ black-bottle 31403).
     * If someone types in a port number from other teams, it will run on that server in order to achieve inter-operate.
     */
    handleSubmit(event) {
        event.preventDefault();
        let obj=this.props.trip;
        if(this.state.otherTeams === null){
            request(obj,'plan').then((Fi)=>
            {
                this.props.updateTrip('distances',Fi.distances);
            });
       } else {
            request(obj,'plan', this.state.otherTeams, 'black-bottle.cs.colostate.edu').then((Fi)=>
            {
                console.log('g',this.state.otherTeams);
                this.props.updateTrip('distances',Fi.distances);
            });
        }
    }

    /* Renders the upload component.
     * Holds sate of everything we get from the file shared with the trip.
     */
    render() {
        return (
            <div className="card">
                <CardBody>
                    <Row>
                        <Col md={6}>
                        <label>
                            Submit your trip!
                            <br/><br/>
                            <input type="file" ref={this.fileInput} onChange={this.LoadFile}/>
                            <small className="form-text text-muted">
                                Upload a file that contains a Trip object in the TFFI format.
                            </small>
                        </label>
                        <br/>
                        <Button className='btn-outline-dark' size='lg' type="submit" onClick={this.handleSubmit}>Plan</Button>
                        <br/><br/>
                        REST API Server Address Port:
                        <br/>
                        <input type="text"
                               placeholder="for example: 31403"
                               style={{width: 300}}
                               onChange={this.updateOtherTeams}
                        />
                        <br/>
                            <SaveTrip trip={this.props.trip}/>
                            <Clear
                                   LoadFile={this.LoadFile} trip={this.props.trip} search={this.props.search} config={this.props.config} clearConfig={this.props.clearConfig}
                                   updateOptions={this.props.updateOptions} updateSearch={this.props.updateSearch} updateTrip={this.props.updateTrip}/>
                        <br/>
                        </Col>
                        <Col md={6}>
                            <Add trip={this.props.trip} search={this.props.search} config={this.props.config} updateTrip={this.props.updateTrip}
                                  updateSearch={this.props.updateSearch} updateOptions={this.props.updateOptions}/>
                        </Col>
                    </Row>
                </CardBody>
            </div>
        );
    }
}

export default Upload;
