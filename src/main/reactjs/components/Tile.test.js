import Tile from "./Tile";
import * as client from "../data/AreWeGreenDataClient";

jest.mock("../data/AreWeGreenDataClient");

describe("Tile", () => {

    test('shows spinner on component initialisation', () => {
        // given
        const promise = Promise.reject();
        client.fetchData.mockImplementation(() => promise);
        // when
        const tile = shallow(<Tile dataSource={"BAM"} text={"bam"}/>);
        // then
        return client.fetchData().catch(() => {
            expect(tile.find(".spinner")).toHaveLength(1);
        });
    });

    test('renders fetched data on mount', () => {
        // given
        const expectedResult = "666";
        const promise = Promise.resolve({data: {value: expectedResult}});
        client.fetchData.mockImplementation(() => promise);
        // when
        const tile = shallow(<Tile dataSource={"getMe"} text={"foo"}/>);
        // then
        return client.fetchData().then(() => {
            tile.update();
            expect(tile.find("[data-test-value]").text()).toEqual(expectedResult);
        });
    });
});
