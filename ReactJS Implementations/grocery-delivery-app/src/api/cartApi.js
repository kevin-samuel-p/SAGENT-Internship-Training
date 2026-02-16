import api from "./axiosConfig";

export const getCart = async (customerId) => {
  const res = await api.get(`/cart/${customerId}`);
  return res.data;
};

export const addToCartApi = async (customerId, productId, quantity) => {
  const res = await api.post(`/cart/add`, {
    customerId,
    productId,
    quantity
  });
  return res.data;
};

// TODO: XXX: Needs to be implemented: 
// export const removeCartItemApi = async (customerId, productId) => {
//   return api.delete(`/cart/remove`, {
//     data: { customerId, productId }
//   });
// };
