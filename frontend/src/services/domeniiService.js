import api from "../api/api.js";

const domeniiService = {
    getAll: async () => {
        const response = await api.get("/api/domenii");
        return response.data; // [{ id, name }]
    },
};

export default domeniiService;
