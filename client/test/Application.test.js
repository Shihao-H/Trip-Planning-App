import './enzyme.config.js'                   // (1)
import React from 'react' // (2)
import Application from '../src/components/Application/Application'

test('default test', () => {
    <Application />
});

test('default constructor', () => {
    let app = new Application({});

    app.state.trip.places = [
        {
        "id": "bl",
        "name": "Obama",
        "latitude": -55.9467,
        "longitude": -67.2751
        },
        {
            "id": "tl",
            "name": "Bush",
            "latitude": 61.2181,
            "longitude": -149.9003
        },
        {
            "id": "tr",
            "name": "Cliton",
            "latitude": 59.2331,
            "longitude": 163.0675
        },
        {
            "id": "br",
            "name": "hell",
            "latitude": -43.5321,
            "longitude": 172.6362
        },
        {
            "id":"null",
            "name":"LOL",
            "latitude": 0,
            "longitude": 0
        }];

    app.updateSelected(new Map());
    app.updateSelectAll(true);

    app.updateTrip('map', '');
    app.updateBasedOnResponse({'trip':{'stuff':'things'} });
    app.updateBasedOnResponse({'stuff':'things'});
    app.updateBasedOnResponse({'config':{'stuff':'things'} });
    app.updateSearch({
        version: 4,
        type: "search",
        match: "",
        filter: {
            name:"",
            values:[]
        },
        limit: 0,
        found: 0,
        places: []
    });

    app.updateOptions('units', 'miles');
    app.updateOptions('units', 'user defined');
    app.updateOptions('nothing', 'hi')
});
