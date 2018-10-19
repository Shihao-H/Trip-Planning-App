import Application from "../src/components/Application/Application";

test('Check Application Constructor', () => {
    const obj = new Application();
    expect(obj).toBe(obj);
});