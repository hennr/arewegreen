import Tile from "./Tile";
import {mount} from "enzyme";
import * as client from "../data/AreWeGreenClient";

jest.mock("../data/AreWeGreenClient");

describe("Tile", () => {

    test('shows spinner on component initialisation', () => {
        // given
        const promise = Promise.reject();
        client.fetchData.mockImplementation(() => promise);
        // when
        const tile = mount(<Tile dataSource={"BAM"} text={"bam"}/>);
        // then
        return client.fetchData().catch(() => {
            expect(tile.find(".spinner")).toExist();
        });
    });

    test('renders fetched data on mount', () => {
        // given
        const expectedResult = "666";
        const promise = Promise.resolve({value: expectedResult});
        client.fetchData.mockImplementation(() => promise);
        // when
        const tile = mount(<Tile dataSource={"getMe"} text={"foo"}/>);
        // then
        return client.fetchData().then(() => {
            tile.update();
            expect(tile.find("[data-test-value]").text()).toEqual(expectedResult);
        });
    });
});
