import api from "../api/api.js";

const testeService = {
    getAll: async () => {
        const response = await api.get("/api/teste");
        return response.data;  // list of TestDTO
    },

    getFull: async (id) => {
        const res = await api.get(`/api/teste/${id}/take`);
        return res.data; // TestFullDTO
    },
    submitTest: async (testId, answers) => {
        const payload = {
            testId,
            answers: Object.entries(answers).map(([questionId, selectedOptionIds]) => ({
                questionId: parseInt(questionId),
                selectedOptionIds: selectedOptionIds.map(id => parseInt(id)),
            })),
        };

        const res = await api.post(`/api/teste/${testId}/submit`, payload);
        return res.data;
    },
};

export default testeService;
