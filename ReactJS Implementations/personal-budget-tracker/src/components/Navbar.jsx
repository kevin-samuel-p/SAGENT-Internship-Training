import { useContext } from "react";
import { AuthContext } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Navbar() {

    const { logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        localStorage.removeItem("userId");
        navigate("/login");
    };

    return (
        <nav className="bg-indigo-600 text-white p-4 flex justify-between">
            <h1 className="text-xl font-semibold cursor-pointer"
                onClick={() => navigate("/dashboard")}>
                Budget Tracker
            </h1>

            <button onClick={() => navigate("/income")} className="mr-4">Income</button>
            <button onClick={() => navigate("/expense")} className="mr-4">Expense</button>

            <button
                onClick={handleLogout}
                className="bg-white text-indigo-600 px-4 py-1 rounded-lg font-medium"
            >
                Logout
            </button>
        </nav>
    );
}
