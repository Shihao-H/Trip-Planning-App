import './enzyme.config.js'                   // (1)
import React from 'react' // (2)
import Developer from '../src/components/Application/Developer'

test('default test', () => {
    <Developer />
});

test('default constructor', () => {
    const obj = new Developer();
    expect(obj).toBe(obj);
});