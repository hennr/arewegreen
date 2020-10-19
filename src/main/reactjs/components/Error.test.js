// @flow

import Error from "./Error"

describe("Error", () => {
    test("shows given error message", () => {
        // when
        const component = shallow(<Error errorMessage={"message"}/>);
        // then
        expect(component.text()).toEqual("message");
    });
});
