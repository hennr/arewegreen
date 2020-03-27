import {convertDataToComponents, getComponents} from "./LayoutService";
import LayoutClient from "./LayoutClient";

jest.mock('./LayoutClient');

describe("LayoutService", () => {

    test("uses layoutClient to retrieve data", async () => {
        // given
        LayoutClient.mockImplementation(() => {
            return {
                fetchLayout: () => {
                    return new Promise((resolve, reject) => {
                        resolve("does not matter")
                    });
                },
            };
        });
        // when
        await getComponents();
        // then
        expect(LayoutClient).toHaveBeenCalled()
    });

    test.skip("generates single component from layout.json", async () => {
        // when
        let components = convertDataToComponents([[{
            "visual": "standard",
            "image": "comets",
            "refreshIntervalInSeconds": "30",
            "title": "single tile",
            "dataSource": "data?source=foo.sh"
        }]]);
        // then
        const renderedComponents = render(components);
        expect(renderedComponents.text()).toEqual("foo");
    });

    test.skip("honors rows from layout.json", () => {

    });

    test.skip("returns error component if layout.json is empty", () => {
        // given
        const expectedErrorMessage = "error message";
        LayoutClient.mockImplementation(() => {
            return {
                fetchLayout: () => {
                    return new Promise((resolve, reject) => {
                        reject(expectedErrorMessage)
                    });
                },
            };
        });
        // when
        let components = getComponents();
        const renderedComponents = render(components);

        // then
        // FIXME async await geht hier vielleicht nicht, lieber mit expect().rejects arbeiten
        expect(renderedComponents.text()).toEqual(expectedErrorMessage);
    });

    test.skip("returns error component if layout.json is invalid", () => {
    });

    test.skip("returns error component if layout.json can't be fetched", () => {
    });
});
