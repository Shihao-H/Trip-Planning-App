import DistanceCal from "../src/components/Application/DistanceCal";

test('Check DistanceCal Constructor', () => {
    const obj = new DistanceCal();
    expect(obj).toBe(obj);
});