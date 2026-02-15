import { useEffect, useState } from "react";
import { fetchProducts } from "../api/productApi";
import ProductCard from "../components/ProductCard";

function Products() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      const data = await fetchProducts();
      setProducts(data);
    } catch (error) {
      console.error("Error loading products", error);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Products</h2>

      <div style={{ display: "flex", gap: "20px", flexWrap: "wrap" }}>
        {products.map((p) => (
          <ProductCard key={p.id} product={p} />
        ))}
      </div>
    </div>
  );
}

export default Products;
