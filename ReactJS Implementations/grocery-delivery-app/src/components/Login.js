import React, { useState } from 'react';

export default function Login() {
  const [customerId, setCustomerId] = useState('');

  return (
    <div style={{padding:20}}>
      <h2>Login (No Auth - Enter Customer ID)</h2>
      <input placeholder="Customer ID" onChange={e=>setCustomerId(e.target.value)} />
      <p>Use this ID while shopping.</p>
    </div>
  );
}