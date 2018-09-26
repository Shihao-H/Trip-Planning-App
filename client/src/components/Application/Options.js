import React, {Component} from 'react'
import { Card, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'
import { FormGroup } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */

function DisplayUnitDefinedInputFields(props){
    if(!props.ifDisplay){
        return null;
    }

    return(
        <form>
            <FormGroup>
                <label>Unit Name: </label>
                <input type="text"
                       placeholder="Enter unit name"
                       onChange={props.updateUnitName}
                />
            </FormGroup>
            <FormGroup>
                <label>Unit Radius: </label>
                <input type="text"
                       placeholder="Enter unit radius"
                       onChange={props.updateUnitRadius}
                />
            </FormGroup>
        </form>
    );
}

class Options extends Component{
  constructor(props) {
    super(props);
    this.state = {ifDisplayUserDefinedInputFields: false};
    this.clickUserDefinedButton = this.clickUserDefinedButton.bind(this);
    this.updateUnitName = this.updateUnitName.bind(this);
    this.updateUnitRadius = this.updateUnitRadius.bind(this);
  }

  clickUserDefinedButton(event){
      this.props.updateOptions('unit', event.target.value);

      if(event.target.value === 'user defined'){
            this.setState({ifDisplayUserDefinedInputFields: true});
      } else {
            this.setState({ifDisplayUserDefinedInputFields: false});
      }
  }

  updateUnitName(event){
      this.props.updateOptions('unitName', event.target.value);
  }

  updateUnitRadius(event){
      this.props.updateOptions('unitRadius', event.target.value);
  }

  render() {
    const buttons = this.props.config.units.map((unit) =>
      <Button
        key={'distance_button_' + unit}
        className='btn-outline-dark unit-button'
        active={this.props.options.unit === unit}
        value={unit}
        onClick={this.clickUserDefinedButton}
      >
        {unit.charAt(0).toUpperCase() + unit.slice(1)}
      </Button>
    );

    return(
      <Card>
        <CardBody>
          <p>Select the options you wish to use.</p>
          <ButtonGroup>
            {buttons}
          </ButtonGroup>
          <p>{' '}</p>
          <DisplayUnitDefinedInputFields ifDisplay={this.state.ifDisplayUserDefinedInputFields} />
        </CardBody>
      </Card>
    );
  }
}

export default Options;
