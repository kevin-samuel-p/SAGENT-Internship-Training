import api from "./axiosConfig";

export const fetchProducts = async () => {
  const response = await api.get("/products");
  return response.data;
};
