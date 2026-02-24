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

    const navLinks = [
        { path: "/dashboard", label: "Dashboard" },
        { path: "/budget", label: "Budget" },
        { path: "/income", label: "Income" },
        { path: "/expense", label: "Expense" },
        { path: "/goals", label: "Goals" },
        { path: "/analytics", label: "Analytics" }
    ];

    return (
        <nav className="bg-accent text-white shadow-cool-lg">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-between h-16">
                    {/* Logo */}
                    <div className="flex items-center">
                        <h1 
                            className="text-xl font-bold cursor-pointer hover:text-blue-100 transition-colors duration-300"
                            onClick={() => navigate("/dashboard")}
                        >
                            💰 Budget Tracker
                        </h1>
                    </div>

                    {/* Navigation Links */}
                    <div className="flex items-center space-x-4">
                        {navLinks.map((link) => (
                            <button
                                key={link.path}
                                onClick={() => navigate(link.path)}
                                className="px-3 py-2 rounded-xl text-sm font-medium hover:bg-white hover:bg-opacity-20 transition-all duration-300"
                            >
                                {link.label}
                            </button>
                        ))}
                        <button 
                            onClick={handleLogout}
                            className="ml-4 bg-white text-blue-600 px-4 py-2 rounded-xl font-medium hover:bg-blue-50 transition-colors duration-300 shadow-sm hover:shadow-md"
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </div>
        </nav>
    );
}
