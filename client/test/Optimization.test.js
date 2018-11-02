import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Optimization from '../src/components/Application/Optimization'

    const obj = new Optimization();
    expect(obj).toBe(obj);

    /* Both of these tests are functionally identical although the standard way
     *  of writing tests uses lambda or anonymous functions. These are useful
     *  for defining functions that will only be in your code once but may be
     *  called multiple times by whatever they are passed to.
    */

    /* A test response for our client to use;
     * this object represents the props that would be passed to the Options
     * component on construction.
     */
    const startProps = {
        'config': { 'optimization': [{"label":"none", "description":"The trip is not optimized."},
                {"label": "short", "description": "Nearest neighbor"},
                {"label": "shorter", "description": "2-opt."},
                {"label": "shortest", "description": "3-opt."},]},
        'options': { 'optimization': 'none' }
    };

    /* Test example using a pre-defined function */
    function testExample() {
        const optimization = mount((
            <Optimization config={startProps.config} options={startProps.options}/>
        ));

        let actual = [];
        optimization.find('Button').map((element) => actual.push(element.prop('value')));

        expect(actual).toEqual([undefined,"none", "short", "shorter","shortest"]);
    }

    test('Check to see if table gets made correctly (Function)', testExample);

    /*--------------------------------------------------------------------------*/

    /* Test example using an anonymous function */
    test('Check to see if table gets made correctly (Lambda)', () => {
        /*  First, we create a version of our Options component, using the
         *  startProps object defined above for its props (1). With our new unrendered
         *  component, we can call ReactWrapper.find() to extract a certain part
         *  of the component and its children (2). Lastly, we check to see if the
         *  value of the buttons created by the component is what we expect,
         *  given the example input (3).
        */
        const optimization = mount((   // (1)
            <Optimization config={startProps.config} options={startProps.options}/>
        ));

        let actual = [];
        optimization.find('Button').map((element) => actual.push(element.prop('value')));  // (2)

        expect(actual).toEqual([undefined,"none", "short", "shorter","shortest"]);  // (3)
    });
