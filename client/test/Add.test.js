import './enzyme.config.js'                   // (1)
import React from 'react'
import Add from '../src/components/Application/Add'

test('check default', () => {
    <Add />
});

test('testing funcs', () => {
    const startProps = {
        'type': "trip",
        'title': "Test trip",
        'options' :
            {
                'units': "miles",
                'unitName': "miles",
                'unitRadius': 3959,
                'optimization': 'none',
                'map': 'svg'
            },
        'places': [],
        'distances': [],
        'map': '',
    }

    let a = new Add({});
    a.props.trip = startProps

    a.props.updateTrip = (a, b) => {}

    a.addPlace()
    a.updatePlaces('id', 'hi')
    a.updatePlaces('latitude', '15.0')
    a.updatePlaces('longitude', '-15.0')
    a.render()
});
