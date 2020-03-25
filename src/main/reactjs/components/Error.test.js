import Error from "./Error"
import {shallow} from "enzyme";

describe("Error", () => {
    test("shows given error message", () => {
        // when
        const component = shallow(<Error errorMessage={"message"}/>);
        // then
        expect(component.text()).toEqual("message");
    });
});
