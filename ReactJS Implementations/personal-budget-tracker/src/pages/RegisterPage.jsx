import { useState } from "react";
import { register } from "../services/authService";
import { useNavigate } from "react-router-dom";

export default function RegisterPage() {

    const [form, setForm] = useState({
        name: "",
        email: "",
        password: ""
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await register(form);
        navigate("/login");
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                name="name"
                placeholder="Name"
                onChange={handleChange}
            />
            <input
                name="email"
                placeholder="Email"
                onChange={handleChange}
            />
            <input
                type="password"
                name="password"
                placeholder="Password"
                onChange={handleChange}
            />
            <button>Register</button>
        </form>
    );
}
