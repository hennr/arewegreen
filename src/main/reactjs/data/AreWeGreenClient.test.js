import mockServerNode from "mockserver-node";
import mockServerClient from "mockserver-client";
import {fetchData} from "./AreWeGreenClient";

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

test('fetches data on fetchData call', async () => {
    // given
    const expectedResult = '666';
    const mockedPath = '/getMe';
    const mockHttpServer = mockServerClient.mockServerClient;
    await mockHttpServer('127.0.0.1', 8080)
        .mockAnyResponse(
            {
                httpRequest: {
                    method: 'GET',
                    path: mockedPath,
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
    const result = await fetchData(mockedPath);
    // then
    expect(result.data.value).toEqual(expectedResult);
});
