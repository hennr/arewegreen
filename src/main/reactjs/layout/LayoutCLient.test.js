import mockServerNode from "mockserver-node";
import mockServerClient from "mockserver-client";
import {fetchLayout} from "./LayoutCLient";

beforeAll(async () => {
    await mockServerNode.start_mockserver({
        serverPort: 8080,
        trace: true,
        jvmOptions: '-Dmockserver.enableCORSForAllResponses=true'
    });
});

afterAll(async () => {
    await mockServerNode.stop_mockserver({
        serverPort: 8080
    });
});

test('fetches layout.json on fetchLayout call', async () => {
    // given
    const expectedResult = `[
\t[
\t\t{ "visual": "number", "class": "storm", "title": "the lucky number",  "xhr": "/data?source=demo.sh", "xhrValue": "value", "xhrInterval": 10}
\t],
\t[
\t\t{ "visual": "number", "class": "comets", "build": "green", "value": "OK", "title": "Build" },
\t\t{ "visual": "number", "class": "desert", "unit": "percent", "build": "orange", "value": "82", "title": "Coverage" }
\t],
\t[
\t\t{ "visual": "number", "class": "waterfall", "title": "https://github.com/hennr/arewegreen/wiki", "build": "none", "value": "docs"}
\t]
]
`;
    const mockHttpServer = mockServerClient.mockServerClient;
    await mockHttpServer('127.0.0.1', 8080)
        .mockAnyResponse(
            {
                httpRequest: {
                    method: 'GET',
                    path: '/layout.json',
                },
                httpResponse: {
                    statusCode: 200,
                    'body': JSON.stringify({value: expectedResult}),
                    delay: {
                        timeUnit: 'MILLISECONDS',
                        value: 0
                    },
                },
                times: {
                    remainingTimes: 5,
                    unlimited: false
                }
            }
        )
        .then(
            (result) => {
                console.log('mock server training finished');
            },
            (error) => {
                console.log(`training the mock server went wrong: ` + error);
            }
        );

    // when
    const result = await fetchLayout();
    // then
    expect(result.data.value).toEqual(expectedResult);
});
