import {render} from "enzyme";
import App from "./App"
import Tile from "./Tile"
import * as layoutService from "../layout/LayoutService";

jest.mock("../layout/LayoutService");

describe("App", () => {

    test('renders components according to layout service', () => {
        // given
        layoutService.getLayout.mockImplementation(() => {
            return <Tile dataSource={"data?source=demo.sh"} text={"LayoutServiceMock"}/>
        });
        // when
        const app = render(<App/>);
        // then
        expect(app.find("[data-test-text]").text()).toEqual("LayoutServiceMock");
    });
});
