import Itinerary from "../src/components/Application/Itinerary";

test('Check Itinerary Constructor', () => {
    const obj = new Itinerary();
    expect(obj).toBe(obj);
});