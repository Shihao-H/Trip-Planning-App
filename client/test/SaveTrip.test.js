import SaveTrip from "../src/components/Application/SaveTrip";

test('Check SaveTrip Constructor', () => {
    const obj = new SaveTrip();
    expect(obj).toBe(obj);
});

test('testing funcs', () => {
    let a = new SaveTrip({});
    a.SaveMap
    a.SaveTFFI
});