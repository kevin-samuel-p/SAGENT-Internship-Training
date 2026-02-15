import { useCart } from "../context/CartContext";

function Cart() {

  const { cart } = useCart();

  if (!cart) return <p>Loading...</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>Your Cart</h2>

      {cart.items?.map(item => (
        <div key={item.productId}>
          {item.productName} — Qty: {item.quantity}
        </div>
      ))}

      <h3>Total: ₹{cart.totalValue}</h3>
    </div>
  );
}

export default Cart;
