import LayoutClient from "./LayoutClient";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";

describe("LayoutClient", () => {

    let mock;

    beforeEach(() => {
        mock = new MockAdapter(axios);
    });

    afterEach(() => {
        mock.restore();
    });

    test('fetches layout.json on fetchLayout call', async () => {
        // given
        const expectedResult = "dummy return value";

        const mock = new MockAdapter(axios);
        mock.onGet(`http://127.0.0.1:8080/layout.json`).reply(200, {
            value: expectedResult,
        });

        // when
        const result = await new LayoutClient().fetchLayout();
        // then
        expect(result.data.value).toEqual(expectedResult);
    });
});