import axios, { AxiosPromise } from "axios";

export default class LayoutClient {
    fetchLayout(): AxiosPromise {
        return axios.get("/layout.json")
    }
}
