import Plan from "../src/components/Application/Plan";

test('Check Plan Constructor', () => {
    const obj = new Plan();
    expect(obj).toBe(obj);
});