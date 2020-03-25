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

    test('fetches layout.json on fetchLayout call', (done) => {
        // given
        const expectedResult = "dummy return value";

        const mock = new MockAdapter(axios);
        mock.onGet(`http://127.0.0.1:8080/layout.json`).reply(200, {
            value: expectedResult,
        });

        // when
        return new LayoutClient().fetchLayout().then(result =>
        {
            // then
            expect(result.data.value).toEqual(expectedResult);
            done();
        }
        )
    });
});
