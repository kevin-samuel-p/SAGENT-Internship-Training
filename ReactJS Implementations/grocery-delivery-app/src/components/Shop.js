import React, { useEffect, useState } from 'react';
import API from '../services/api';

export default function Shop() {
  const [products, setProducts] = useState([]);
  const [cart, setCart] = useState([]);
  const [search, setSearch] = useState('');
  const [customerId, setCustomerId] = useState('');

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    const res = await API.get('/products');
    setProducts(res.data);
  };

  const searchProducts = async () => {
    const res = await API.get('/products/search?name=' + search);
    setProducts(res.data);
  };

  const addToCart = async (productId, quantity) => {
    await API.post('/cart/add', { customerId, productId, quantity });
    alert('Added to cart');
  };

  const viewCart = async () => {
    const res = await API.get('/cart/' + customerId);
    setCart(res.data);
  };

  const placeOrder = async () => {
    await API.post('/orders/place', { customerId });
    alert('Order placed!');
  };

  return (
    <div style={{padding:20}}>
      <h2>Shop</h2>
      <input placeholder="Customer ID" onChange={e=>setCustomerId(e.target.value)} />
      <br/><br/>
      <input placeholder="Search products" onChange={e=>setSearch(e.target.value)} />
      <button onClick={searchProducts}>Search</button>

      <div style={{display:'grid', gridTemplateColumns:'repeat(4,1fr)', gap:20, marginTop:20, maxHeight:300, overflowY:'scroll'}}>
        {products.map(p => (
          <div key={p.product_id} style={{border:'1px solid #ccc', padding:10}}>
            <div style={{fontSize:40}}>🛒</div>
            <h4>{p.product_name}</h4>
            <p>₹{p.price}</p>
            <input type="number" min="1" defaultValue="1" id={'qty-'+p.product_id} />
            <button onClick={()=>addToCart(p.product_id, document.getElementById('qty-'+p.product_id).value)}>Add</button>
          </div>
        ))}
      </div>

      <hr/>
      <button onClick={viewCart}>View Cart</button>

      <div>
        <h3>Cart</h3>
        {cart.map((item,i)=>(
          <div key={i}>
            {item.productName} - {item.quantity}
          </div>
        ))}
        <button onClick={placeOrder}>Place Order</button>
      </div>
    </div>
  );
}