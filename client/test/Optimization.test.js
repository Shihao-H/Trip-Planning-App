import Optimization from "../src/components/Application/Optimization";

test('Check Optimization Constructor', () => {
    const obj = new Optimization();
    expect(obj).toBe(obj);
});