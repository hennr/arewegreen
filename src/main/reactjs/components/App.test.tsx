import { render } from "enzyme";
import React from "react";
import App from "./App"

describe("App", () => {
    it("renders without errors", () => {
        render(<App/>);
    });
});
