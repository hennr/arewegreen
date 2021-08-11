import Tile from "./Tile";
import AreWeGreenDataClient from "../data/AreWeGreenDataClient";
import {configure, shallow} from "enzyme";
import React from "react";
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

describe("Tile", () => {

    test("shows spinner for value if promise is not resolvable", () => {
        // given
        const expectedText = "bam"
        const client = new AreWeGreenDataClient();
        jest.spyOn(client, 'fetchData').mockReturnValue(Promise.reject())
        // when
        const tile = shallow(<Tile dataSource={"BAM"} text={expectedText}/>);
        // then
        return client.fetchData("").catch(() => {
            expect(tile.find(".spinner")).toHaveLength(1);
            expect(tile.find("[data-test-text]").text()).toEqual(expectedText);
        });
    });

    test('renders fetched data on mount', async () => {
        // given
        const expectedText = "foo";
        const expectedValue = "666";
        const spy = jest.spyOn(AreWeGreenDataClient.prototype, 'fetchData').mockReturnValue(Promise.resolve({
            status: 200,
            statusText: "ok",
            headers: "",
            config: {},
            data: {finalValue: expectedValue}
        }))
        // when
        const tile = shallow(<Tile dataSource={"getMe"} text={expectedText}/>);
        // make enzyme wait for the Promise and re-render the component after the trigger state update :/
        await Promise.resolve()
        tile.update()
        // then
        expect(spy).toHaveBeenCalled()
        expect(tile.find("[data-test-text]").text()).toEqual(expectedText);
        expect(tile.find("[data-test-value]").text()).toEqual(expectedValue);
    });
});
