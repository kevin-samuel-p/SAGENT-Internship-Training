import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav style={{ padding: "10px", background: "#eee" }}>
      <Link to="/" style={{ marginRight: "20px" }}>Home</Link>
      <Link to="/products">Products</Link>
      <Link to="/cart">Cart</Link>
    </nav>
  );
}

export default Navbar;
