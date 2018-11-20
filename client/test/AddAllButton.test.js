import './enzyme.config.js'
import AddAllButton from "../src/components/Application/AddAllButton";

test('Check AddAllButton Constructor', () => {
    const obj = new AddAllButton();
    expect(obj).toBe(obj);
});

test('testing addAll Button', () => {
    let a = new AddAllButton({});
    a.addNewPlace
});

