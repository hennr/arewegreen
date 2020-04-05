// @flow

import axios from "axios";
import type { $AxiosXHR } from "axios";

export default class LayoutClient {
    fetchLayout(): Promise<$AxiosXHR<*,*>> {
        return axios.get("/layout.json")
    }
}
