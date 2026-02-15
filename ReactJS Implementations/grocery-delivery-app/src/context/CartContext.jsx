import { createContext, useContext, useEffect, useState } from "react";
import { getCart, addToCartApi } from "../api/cartApi";

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {

  const [cart, setCart] = useState(null);
  const customerId = 1; // temporary until login exists

  const loadCart = async () => {
    try {
      const data = await getCart(customerId);
      setCart(data);
    } catch (err) {
      console.error("Failed to load cart");
    }
  };

  const addToCart = async (productId, quantity = 1) => {
    await addToCartApi(customerId, productId, quantity);
    await loadCart();
  };

  useEffect(() => {
    loadCart();
  }, []);

  return (
    <CartContext.Provider value={{ cart, addToCart, loadCart }}>
      {children}
    </CartContext.Provider>
  );
};
