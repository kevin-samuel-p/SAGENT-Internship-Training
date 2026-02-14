import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await axios.post('http://localhost:8080/api/auth/login', { email, password });
            localStorage.setItem('token', res.data);
            
            // Simple logic to redirect based on email for demo
            // In real app, decode JWT to get role
            if(email.includes('admin')) navigate('/officer');
            else navigate('/student');
        } catch (e) {
            alert('Login Failed');
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <input placeholder="Email" onChange={e => setEmail(e.target.value)} /><br/>
            <input type="password" placeholder="Password" onChange={e => setPassword(e.target.value)} /><br/>
            <button onClick={handleLogin}>Login</button>
        </div>
    );
};

export default Login;