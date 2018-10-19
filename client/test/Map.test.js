import Map from "../src/components/Application/Map";

test('Check Map Constructor', () => {
    const obj = new Map();
    expect(obj).toBe(obj);
});