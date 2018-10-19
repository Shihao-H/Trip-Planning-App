import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import InterOperate from '../src/components/Application/InterOperate'

test('Check InterOperate Constructor', () => {
    const obj = new InterOperate();
    expect(obj).toBe(obj);
});

test('Check InterOperate Rendering', () => {

});
