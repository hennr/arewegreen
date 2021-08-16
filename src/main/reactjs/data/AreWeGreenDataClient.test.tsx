import AreWeGreenDataClient from "./AreWeGreenDataClient";

import axios from "axios";
import MockAdapter from "axios-mock-adapter";

describe("AreWeGreenClient", () => {

    let mock: MockAdapter;

    beforeEach(() => {
        mock = new MockAdapter(axios);
    });

    afterEach(() => {
        mock.restore();
    });

    test('fetches data on fetchData call', async () => {
        // given
        const expectedResult = '666';
        const queriedUrl = "foo";
        const mock = new MockAdapter(axios);
        mock.onGet(`/${queriedUrl}`).reply(200, {
            value: expectedResult,
        });
        // when
        const client = new AreWeGreenDataClient();
        const result = await client.fetchData(queriedUrl);
        // then
        expect(result.data.value).toEqual(expectedResult);
    });
});
