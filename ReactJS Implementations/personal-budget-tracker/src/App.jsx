import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import DashboardPage from "./pages/DashboardPage";
import ExpensePage from "./pages/ExpensePage";
import IncomePage from "./pages/IncomePage";
import PrivateRoute from "./auth/PrivateRoute";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/income" element={<PrivateRoute><IncomePage/></PrivateRoute>} />
                <Route path="/expense" element={<PrivateRoute><ExpensePage/></PrivateRoute>} />

                <Route
                    path="/dashboard"
                    element={
                        <PrivateRoute>
                            <DashboardPage />
                        </PrivateRoute>
                    }
                />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
