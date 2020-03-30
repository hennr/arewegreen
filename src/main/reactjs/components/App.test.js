// @flow

import React from "react";
import {render} from "enzyme";
import App from "./App"

jest.mock("../layout/LayoutService");

describe("App", () => {
    it("renders without errors", () => {
        render(<App/>);
    });
});
