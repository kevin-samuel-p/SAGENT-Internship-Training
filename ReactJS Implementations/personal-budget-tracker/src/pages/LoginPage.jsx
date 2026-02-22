import { useState, useContext } from "react";
import { login as loginApi } from "../services/authService";
import { AuthContext } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await loginApi({ email, password });
            console.log(res);    
            login(res.data);

            navigate("/dashboard");
        } catch (err) {
            setError("Invalid credentials");
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <form
                onSubmit={handleSubmit}
                className="bg-white p-8 rounded-2xl shadow-md w-80"
            >
                <h2 className="text-2xl font-bold mb-6 text-center">
                    Login
                </h2>

                <input
                    className="w-full mb-3 p-2 border rounded"
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    className="w-full mb-4 p-2 border rounded"
                    placeholder="Password"
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button className="w-full bg-indigo-600 text-white py-2 rounded">
                    Login
                </button>
            </form>
        </div>
    );
}
