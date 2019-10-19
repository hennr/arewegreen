import Tile from "./Tile";

describe("Tile", () => {
    it('renders given values', () => {
        const rendered = shallow(<Tile value={"42"} text={"foo"}/>);
        expect(rendered.find("[data-test-value]").text()).toEqual('42');
        expect(rendered.find("[data-test-text]").text()).toEqual('foo');
    });
});
