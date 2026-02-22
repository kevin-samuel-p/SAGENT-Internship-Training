import { useEffect, useState, useContext } from "react";
import api from "../api/axiosConfig";
import Navbar from "../components/Navbar";
import { AuthContext } from "../auth/AuthContext";

export default function DashboardPage() {
  const { userId } = useContext(AuthContext);

  const [data, setData] = useState({
    totalIncome: 0,
    totalExpense: 0,
    balance: 0
  });

  useEffect(() => {
    if (!userId) return; // don't fetch if userId not set

    const fetchDashboard = async () => {
      try {
        const res = await api.get(`/dashboard/${userId}`);
        setData(res.data.data); // ResponseUtil wraps data
      } catch (err) {
        console.error("Failed to fetch dashboard:", err);
      }
    };

    fetchDashboard();
  }, [userId]);

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">
        <h2 className="text-3xl font-bold mb-6">Dashboard</h2>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-white p-6 rounded-2xl shadow">
            <h3 className="text-gray-500">Total Income</h3>
            <p className="text-2xl font-bold text-green-600">₹ {data.totalIncome}</p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h3 className="text-gray-500">Total Expense</h3>
            <p className="text-2xl font-bold text-red-600">₹ {data.totalExpenses}</p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h3 className="text-gray-500">Balance</h3>
            <p className="text-2xl font-bold text-indigo-600">₹ {data.balance}</p>
          </div>
        </div>
      </div>
    </>
  );
}
