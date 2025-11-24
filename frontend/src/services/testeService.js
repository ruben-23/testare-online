import api from "../api/api.js";

const testeService = {
    getAll: async () => {
        const response = await api.get("/api/teste");
        return response.data;  // list of TestDTO
    },
};

export default testeService;
