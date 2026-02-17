import api from "../api/axiosConfig";

export const getIncomes = (userId) =>
    api.get(`/income/user/${userId}`);

export const addIncome = (data) =>
    api.post("/income", data);

export const deleteIncome = (id) =>
    api.delete(`/income/${id}`);
