// @flow

import axios from "axios";
import type {$AxiosXHR} from "axios";

export function fetchData(path: string): Promise<$AxiosXHR<*,*>> {
    return axios.get('http://127.0.0.1:8080/' + path)
}
