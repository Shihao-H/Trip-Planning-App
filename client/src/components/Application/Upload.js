import React, {Component} from 'react'
import {Button} from "reactstrap";
import {CardBody} from 'reactstrap'
import {request} from '../../api/api';
import SaveTrip from './SaveTrip';

class Upload extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.LoadFile=this.LoadFile.bind(this);
        this.updateOtherTeams=this.updateOtherTeams.bind(this);
        this.fileInput = React.createRef();
        this.state = {otherTeams: null};
    }

    updateOtherTeams(event) {
        this.setState({otherTeams: event.target.value});

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

        }

    }

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

    render() {
        return (
            <div className="card">
                <CardBody>
                    <form>
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
                            <SaveTrip/>
                        <br/>
                    </form>
                </CardBody>
            </div>
        );
    }
}

export default Upload;
