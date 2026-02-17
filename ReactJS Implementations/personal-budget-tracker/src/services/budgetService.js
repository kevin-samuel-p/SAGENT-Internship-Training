import axios from "axios";

const API_BASE = "http://localhost:8080/api";

export const getMonthlyBudgets = (userId, month, year) => {
  return axios.get(`${API_BASE}/budgets/${userId}/${month}/${year}`);
};

// If you have expense summary endpoint
export const getExpenseSummary = (userId, month, year) => {
  return axios.get(`${API_BASE}/expenses/summary/${userId}/${month}/${year}`);
};
