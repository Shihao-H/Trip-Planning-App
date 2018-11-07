import Map from "../src/components/Application/MapSvg";

test('Check Map Constructor', () => {
    const obj = new Map();
    expect(obj).toBe(obj);
});