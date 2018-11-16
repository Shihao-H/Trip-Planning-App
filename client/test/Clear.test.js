import Clear from "../src/components/Application/Clear";
import React from "react";

test('Check Clear Constructor', () => {
    const obj = new Clear();
    expect(obj).toBe(obj);
});

test('testing funcs', () => {
    let a = new Clear({});
    a.clear
});
