import {configure, render} from "enzyme";
import React from "react";
import App from "./App"
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

describe("App", () => {
    it("renders without errors", () => {
        render(<App/>);
    });
});
