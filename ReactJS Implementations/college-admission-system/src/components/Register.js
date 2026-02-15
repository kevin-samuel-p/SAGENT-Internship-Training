import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [form, setForm] = useState({
        name: '',
        email: '',
        password: '',
        role: 'ROLE_STUDENT'
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleRegister = async () => {
        try {
            await axios.post('http://localhost:8080/api/auth/register', form);
            alert('Registration successful! Please login.');
            navigate('/login');
        } catch (error) {
            alert(error.response?.data || 'Registration Failed');
        }
    };

    return (
        <div>
            <h2>Register</h2>

            <input
                name="name"
                placeholder="Full Name"
                value={form.name}
                onChange={handleChange}
            /><br/>

            <input
                name="email"
                placeholder="Email"
                value={form.email}
                onChange={handleChange}
            /><br/>

            <input
                type="password"
                name="password"
                placeholder="Password"
                value={form.password}
                onChange={handleChange}
            /><br/>

            <button onClick={handleRegister}>
                Register
            </button>
        </div>
    );
};

export default Register;