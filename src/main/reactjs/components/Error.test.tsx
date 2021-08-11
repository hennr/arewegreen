import Error from "./Error"
import {configure, shallow} from "enzyme";
import React from "react";
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

describe("Error", () => {
    test("shows given error message", () => {
        // when
        const component = shallow(<Error errorMessage={"message"}/>);
        // then
        expect(component.text()).toEqual("message");
    });
});
