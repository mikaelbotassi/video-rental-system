import axios from "axios";

const config = {
    baseURL: "http://localhost:8080/api",
    headers: {
        'Accept': 'application/json',
        'Authorization': '',
    },
}

const api = axios.create(config);

export default api