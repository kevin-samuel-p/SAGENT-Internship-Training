import { useCart } from "../context/CartContext";

function ProductCard({ product }) {

  const { addToCart } = useCart();

  return (
    <div
      style={{
        border: "1px solid #ccc",
        padding: "15px",
        width: "200px"
      }}
    >
      <h3>{product.productName}</h3>
      <p>Price: ₹{product.price}</p>
      <p>Stock: {product.stockQuantity}</p>

      <button onClick={() => addToCart(product.productId, 1)}>
        Add to Cart
      </button>
    </div>
  );
}

export default ProductCard;
