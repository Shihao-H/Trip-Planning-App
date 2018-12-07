import React, {Component} from 'react';
import {Button, Row, Col} from 'reactstrap';
import {get, request} from '../../api/api';

class Upload extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.LoadFile = this.LoadFile.bind(this);
        this.loadFileHelper = this.loadFileHelper.bind(this);
        this.fileInput = React.createRef();
    }

    loadFileHelper(obj){
        this.props.updateOptions('optimization', (obj.options.optimization ?
            obj.options.optimization : 'none'));
        this.props.updateOptions('mapForOption', (obj.options.map ? obj.options.map : 'svg'));
    }

    LoadFile(event) {
        let file = event.target.files[0];
        let fileReader = new FileReader();
        fileReader.readAsText(file);
        fileReader.onload = (event) => {
            let obj = JSON.parse(event.target.result);
            this.props.updateTrip('title', obj.title);
            this.props.updateOptions('units', obj.options.units);
            this.loadFileHelper(obj);
            if (obj.options.units === "user defined") {
                this.props.updateOptions('unitRadius', obj.options.unitRadius);
                this.props.updateOptions('unitName', obj.options.unitName);
            }
            this.props.updateTrip('places', obj.places);
            get('map').then(map => {
                    this.props.updateTrip("map", map.map);
                }
            );
        };
        this.props.updateSelected(new Map());
        this.props.updateSelectAll(false);
    }

    handleSubmit(event) {
        event.preventDefault();
        let obj = this.props.trip;
        obj.options.map = obj.options.mapForOption;
        request(obj, 'plan', this.props.otherTeams, this.props.host).then((Fi) => {
            this.props.updateTrip('distances', Fi.distances);
            this.props.updateTrip('places', Fi.places);
            this.props.updateTrip('map', Fi.map);
        });
    }

    render() {
        return (
            <Row>
                <Col>
                    <input type="file" ref={this.fileInput}
                           id="fileInput"
                           onChange={(event) => this.LoadFile(event)}/>
                    <small className="form-text text-muted">
                        Upload a file that contains a Trip<br/>object in the TFFI format.
                    </small><br/>
                    <Button
                        size='lg'
                        type="button"
                        className='btn-dark btn-outline-dark'
                        onClick={this.handleSubmit}>Plan</Button>
                </Col>
            </Row>
        );
    }
}

export default Upload;
