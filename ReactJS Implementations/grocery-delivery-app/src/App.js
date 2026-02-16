import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Shop from './components/Shop';

function App() {
  return (
    <div>
      <nav style={{padding:10, background:'#2e7d32', color:'white'}}>
        <Link to="/" style={{marginRight:15, color:'white'}}>Login</Link>
        <Link to="/register" style={{marginRight:15, color:'white'}}>Register</Link>
        <Link to="/shop" style={{color:'white'}}>Shop</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/shop" element={<Shop />} />
      </Routes>
    </div>
  );
}

export default App;
