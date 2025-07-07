import axios from 'axios';

const api = axios.create({
    baseURL: `http://${process.env.BACKEND_HOST_NAME || 'localhost'}:8080`,
    headers: {
        "Content-Type": "application/json"
    }
});

export default api;