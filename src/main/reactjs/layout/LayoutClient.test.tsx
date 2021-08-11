import LayoutClient from "./LayoutClient";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";

describe("LayoutClient", () => {

    let mock: MockAdapter;

    beforeEach(() => {
        mock = new MockAdapter(axios);
    });

    afterEach(() => {
        mock.restore();
    });

    test('fetches layout.json on fetchLayout call', () => {
        // given
        const expectedResult = "dummy return value";

        const mock = new MockAdapter(axios);
        mock.onGet(`/layout.json`).reply(200, {
            value: expectedResult,
        });

        // when
        return new LayoutClient().fetchLayout().then(result =>
        {
            // then
            expect(result.data.value).toEqual(expectedResult);
        }
        )
    });
});
