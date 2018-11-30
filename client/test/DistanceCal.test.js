import './enzyme.config.js'                   // (1)
import React from 'react' // (2)
import DistanceCal from '../src/components/Application/DistanceCal'

test('default test', () => {
    <DistanceCal />
});

test('default constructor', () => {
    let cal = new DistanceCal();
    cal.updateLoc('latitude', 12 ,false);
    cal.updateDistance('miles', 120);
    cal.Display()
});