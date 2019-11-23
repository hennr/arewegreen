import axios from "axios";

export function fetchLayout() {
    return axios.get('http://127.0.0.1:8080/layout.json')
}
