import './enzyme.config.js'
import Attributes from "../src/components/Application/Attributes";

test('Check Attributes Constructor', () => {
    const obj = new Attributes();
    expect(obj).toBe(obj);
});

