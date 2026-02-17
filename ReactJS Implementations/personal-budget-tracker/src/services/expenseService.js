import api from "../api/axiosConfig";

export const getExpenses = (userId) =>
    api.get(`/expense/user/${userId}`);

export const addExpense = (data) =>
    api.post("/expense", data);

export const deleteExpense = (id) =>
    api.delete(`/expense/${id}`);
