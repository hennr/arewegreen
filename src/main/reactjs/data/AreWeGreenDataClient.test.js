import {fetchData} from "./AreWeGreenDataClient";

import axios from "axios";
import MockAdapter from "axios-mock-adapter";

describe("AreWeGreenClient", () => {

    let mock;

    beforeEach(() => {
        mock = new MockAdapter(axios);
    });

    afterEach(() => {
        mock.restore();
    });

    test('fetches data on fetchData call', async () => {
        // given
        const expectedResult = '666';
        const urlPath = "foo";
        const mock = new MockAdapter(axios);
        mock.onGet(`/${urlPath}`).reply(200, {
            value: expectedResult,
        });
        // when
        const result = await fetchData(urlPath);
        // then
        expect(result.data.value).toEqual(expectedResult);
    });
});
