import axios from "axios";

export interface ServerData {
    value: string
  }

export default class AreWeGreenDataClient {
    fetchData(path: string) {
        return axios.request<ServerData>({
              url: '/' + path
        });
    }
}
