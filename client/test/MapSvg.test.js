import MapSvg from "../src/components/Application/MapSvg";

test('Check Map Constructor', () => {
    const obj = new MapSvg();
    expect(obj).toBe(obj);
});