import Tile from "../components/Tile"
import {getLayout} from "./LayoutService";

describe("LayoutService", () => {

    test("returns dummy data for now", ()=> {
        // given
        const expectedData = <React.Fragment>
            <div className={"row"}>
                <Tile dataSource={"data?source=demo.sh"} text={"foo"}/>
                <Tile dataSource={"data?source=demo.sh"} text={"bar"}/>
            </div>
            <div className={"row"}>
                <Tile dataSource={"data?source=demo.sh"} text={"baz"}/>
            </div>
        </React.Fragment>;
        // when
        const result = getLayout();
        // then
        expect(result).toEqual(expectedData);
    });

    test.skip("uses the LayoutClient to retrieve the raw input", () => {

    });

    test.skip("converts the layoutClient result correctly to react components", () => {

    });

});
