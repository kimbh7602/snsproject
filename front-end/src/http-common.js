import axios from "axios";

export default axios.create({
    // baseURL: "http://192.168.100.41:8080/api",
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-type": "application/json",
    }
})