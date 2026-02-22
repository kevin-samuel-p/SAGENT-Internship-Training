import api from "../api/axiosConfig";

export const getExpenses = (userId) =>
  api.get(`/expenses/user/${userId}`);

export const addExpense = (data) =>
  api.post("/expenses", data);

export const deleteExpense = (id) =>
  api.delete(`/expenses/${id}`);

export const getExpenseCategories = () =>
  api.get("/expenses/categories/all");

export const getExpenseSummary = (userId, month, year) =>
  api.get(`/expenses/summary/${userId}/${month}/${year}`);


