import api from "../api/api.js";

const userService = {
    getById: async (id) => {
        const response = await api.get(`/api/users/${id}`);
        return response.data; // { id, username, ... }
    },
};

export default userService;
