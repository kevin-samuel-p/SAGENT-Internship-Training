import api from "../api/axiosConfig";

export const getGoals = (userId) =>
  api.get(`/goals/user/${userId}`);

export const addGoal = (data) =>
  api.post("/goals", data);

export const deleteGoal = (id) =>
  api.delete(`/goals/${id}`);

export const allocateSavings = (userId) =>
  api.post(`/goals/allocate/${userId}`);

export const contribute = (goalId, amount) =>
  api.post(`/goals/contribute/${goalId}?amount=${amount}`);