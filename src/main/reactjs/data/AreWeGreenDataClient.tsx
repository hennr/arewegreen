import axios, { AxiosPromise, AxiosResponse } from "axios";

interface ServerResponse {
    value: ServerData
  }
  
  interface ServerData {
    finalValue: string
  }
  
//   axios.request<ServerData>({
//     url: 'https://example.com/path/to/data',
//     transformResponse: (r: ServerResponse) => r.data
//   }).then((response) => {
//     // `response` is of type `AxiosResponse<ServerData>`
//     const { data } = response
//     // `data` is of type ServerData, correctly inferred
//   })

export default class AreWeGreenDataClient {
    fetchData(path: string) {
        const result = axios.request<ServerData>({
            url: '/' + path,
            transformResponse: (response: ServerResponse) => response.value
          })
          return result;
    }
}
