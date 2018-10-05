import React, {Component} from 'react'
import {Button} from "reactstrap";
import {CardBody} from 'reactstrap'
import {request} from '../../api/api';

class Upload extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.LoadFile=this.LoadFile.bind(this);
        this.updateOtherTeams=this.updateOtherTeams.bind(this);
        this.SaveTFFI=this.SaveTFFI.bind(this);
        this.fileInput = React.createRef();
        this.state = {
            otherTeams: 31403,
            host: null
        };

    }

    updateOtherTeams(event) {
            var str = event.target.value;
            var portAndHost = str.split(':');
            this.setState({otherTeams: portAndHost[1]});
            this.setState({host: portAndHost[0]});

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
        let obj=this.props.trip;
        if(this.state.host === null){
            request(obj,'plan').then((Fi)=>
            {
                this.props.updateTrip('distances',Fi.distances);
            });
        }

        request(obj,'plan', this.state.otherTeams, this.state.host).then((Fi)=>
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
                        <Button className='btn-outline-dark' size='lg' type="submit">Plan</Button>
                        <br/><br/>
                        REST API Server Address Port:
                        <br/>
                        <input type="text"
                               placeholder="For example: black-bottle.cs.colostate.edu:31403"
                               style={{width: 350}}
                               onChange={this.updateOtherTeams}
                        />
                        <br/>
                        <Button className="btn-save" size='lg' onClick={this.SaveTFFI} type="button">Save</Button>
                        <br/>
                    </form>
                </CardBody>
            </div>
        );
    }
}

export default Upload;
