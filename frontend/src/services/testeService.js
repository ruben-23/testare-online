import api from "../api/api.js";

const testeService = {
    getAll: async () => {
        const response = await api.get("/api/teste");
        return response.data;  // list of TestDTO
    },

    getFull: async (id) => {
        const res = await api.get(`/api/teste/${id}/info`);
        return res.data; // TestFullDTO
    },
    takeTest: async (id) => {
        const res = await api.get(`/api/teste/${id}/take`);
        return res.data; // TestFullDTO
    },
    submitTest: async (testId, answers, guestUsername) => {
        const payload = {
            testId,
            answers: Object.entries(answers).map(([questionId, selectedOptionIds]) => ({
                questionId: parseInt(questionId),
                selectedOptionIds: selectedOptionIds.map(id => parseInt(id)),
            })),
            guestUsername: guestUsername || null
    };

        const res = await api.post(`/api/teste/${testId}/submit`, payload);
        return res.data;
    },
    create: async (testData) => {
        const response = await api.post("/api/teste/create/full", testData);
        return response.data;
    },
    update: async (id, testData) => {
        const response = await api.put(`/api/teste/${id}/full`, testData);
        return response.data;
    },

    delete: async (id) => {
        const response = await api.delete(`/api/teste/${id}`);
        return response.data;
    },
};

export default testeService;
