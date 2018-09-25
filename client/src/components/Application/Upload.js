import React, {Component} from 'react'
import {Button} from "reactstrap";
import { Card, CardHeader, CardBody } from 'reactstrap'
//import ReactDOM from 'react-dom'



class Upload extends Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.fileInput = React.createRef();
    }
    handleSubmit(event) {
        event.preventDefault();
        alert(
            `Selected file - ${
                this.fileInput.current.files[0].name
                }`
        );
    }

    render() {
        // highlight-range{5}
        return (
            <div className="card">
                <CardBody>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Submit your trip!
                            <br /><br />
                            <input type="file" ref={this.fileInput} />
                            <small className="form-text text-muted">Upload a file that contains a Trip object in the TFFI format.</small>
                        </label>
                        <br />
                        <Button className='btn-outline-dark' type="submit">Submit</Button>
                    </form>
                </CardBody>
            </div>
        );
    }

//ReactDOM.render(<FileInput />,document.getElementById('root'));
 }

export default Upload;
