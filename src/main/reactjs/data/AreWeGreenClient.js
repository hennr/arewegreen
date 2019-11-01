import axios from "axios";

export function fetchData(path) {
    return axios.get('http://127.0.0.1:1080/' + path)
}
