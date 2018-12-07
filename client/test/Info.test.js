import './enzyme.config.js'                   // (1)
import React from 'react' // (2)
import Info from '../src/components/Application/Info'

test('default test', () => {
    <Info />
});

test('default constructor', () => {
    const obj = new Info();
    expect(obj).toBe(obj);
});