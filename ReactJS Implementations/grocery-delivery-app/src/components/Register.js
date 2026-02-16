import React, { useState } from 'react';
import API from '../services/api';

export default function Register() {
  const [form, setForm] = useState({ name:'', address:'', contact_details:'' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await API.post('/customers', form);
    alert('Registered successfully!');
  };

  return (
    <div style={{padding:20}}>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="Name" onChange={e=>setForm({...form,name:e.target.value})} /><br/><br/>
        <input placeholder="Address" onChange={e=>setForm({...form,address:e.target.value})} /><br/><br/>
        <input placeholder="Contact" onChange={e=>setForm({...form,contact_details:e.target.value})} /><br/><br/>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}