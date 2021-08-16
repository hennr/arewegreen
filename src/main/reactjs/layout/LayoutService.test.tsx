import {convertDataToComponents, getComponents} from "./LayoutService";
import LayoutClient from "./LayoutClient";
import {configure, render} from "enzyme";
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

describe("LayoutService", () => {

    test("uses layoutClient to retrieve data", async () => {
        let layoutClient = new LayoutClient();
        const spy = jest.spyOn(layoutClient, 'fetchLayout');
        // when
        await getComponents(layoutClient);
        // then
        expect(spy).toHaveBeenCalled()
    });

    test("generates single component from layout.json", () => {
        // when
        let components = convertDataToComponents({
            "visual": "standard",
            "image": "comets",
            "refreshIntervalInSeconds": 30,
            "title": "single tile",
            "dataSource": "data?source=foo.sh"
        });
        // then
        const renderedComponents = render(components);
        expect(renderedComponents.find("[data-test-text]").text()).toEqual("single tile");
    });

    test.skip("generates multiple components from layout.json", async () => {
    });

    test.skip("honors rows from layout.json", () => {

    });

    // test.skip("returns error component if layout.json is empty", () => {
    //     // given
    //     const expectedErrorMessage = "error message";
    //     LayoutClient.mockImplementation(() => {
    //         return {
    //             fetchLayout: () => {
    //                 return new Promise((resolve, reject) => {
    //                     reject(expectedErrorMessage)
    //                 });
    //             },
    //         };
    //     });
    //     // when
    //     let components = getComponents();
    //     const renderedComponents = render(components);

    //     // then
    //     // FIXME async await geht hier vielleicht nicht, lieber mit expect().rejects arbeiten
    //     expect(renderedComponents.text()).toEqual(expectedErrorMessage);
    // });

    test.skip("returns error component if layout.json is invalid", () => {
    });

    test.skip("returns error component if layout.json can't be fetched", () => {
    });
});
