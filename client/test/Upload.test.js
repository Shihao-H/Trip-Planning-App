import Upload from "../src/components/Application/Upload";

test('Check Upload Constructor', () => {
    const obj = new Upload();
    expect(obj).toBe(obj);
});