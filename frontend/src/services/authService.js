import api from "../api/api.js";

const authService = {
    login: async (username, parola) => {
        const response = await api.post("/login", { username, parola });
        return response.data; // { token: "..." }
    },

    register: async (username, parola) => {
        const response = await api.post("/register", { username, parola });
        return response.data; // { token: "..." }
    },
};

export default authService;
