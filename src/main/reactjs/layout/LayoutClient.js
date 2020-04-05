// @flow

import axios from "axios";
import type { $AxiosXHR } from "axios";

export default class LayoutClient {
    fetchLayout(): Promise<$AxiosXHR<*,*>> {
        return axios.get("http://127.0.0.1:8080/layout.json")
    }
}
