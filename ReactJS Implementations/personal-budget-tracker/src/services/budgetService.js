import api from "../api/axiosConfig";

export const addBudget = (data) => 
  api.post("/budget", data);

export const getMonthlyBudgets = (userId, month, year) => 
  api.get(`/budget/${userId}/${month}/${year}`);


export const getBudgetUsage = (userId) => 
  api.get(`/budget/usage/${userId}`);
