import HomePage from "../src/components/Application/HomePage";

test('default constructor', () => {
    const obj = new HomePage();
    expect(obj).toBe(obj);
});