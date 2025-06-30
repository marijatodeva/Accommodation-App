import axios from "axios";

const baseURL =
    import.meta.env.MODE === 'development'
        ? 'http://localhost:9090/api'        // кога работиш со docker compose локално
        : 'http://backend.local/api';         // кога работиш со Kubernetes Ingress

const axiosInstance = axios.create({
    baseURL,
    headers: {
        "Content-Type": "application/json",
    },
});

export default axiosInstance;