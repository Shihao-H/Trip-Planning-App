import Trip from "../src/components/Application/Trip";

test('Check Trip Constructor', () => {
    const obj = new Trip();
    expect(obj).toBe(obj);
});