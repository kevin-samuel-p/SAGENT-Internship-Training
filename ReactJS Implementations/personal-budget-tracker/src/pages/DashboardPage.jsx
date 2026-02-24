import { useEffect, useState, useContext } from "react";
import api from "../api/axiosConfig";
import Navbar from "../components/Navbar";
import { AuthContext } from "../auth/AuthContext";
import { allocateSavings } from "../services/goalService";

export default function DashboardPage() {
  const { userId } = useContext(AuthContext);

  const [data, setData] = useState({
    totalIncome: 0,
    totalExpense: 0,
    balance: 0
  });
  const [isLoading, setIsLoading] = useState(true);
  const [isAllocating, setIsAllocating] = useState(false);

  useEffect(() => {
    if (!userId) return;

    const fetchDashboard = async () => {
      try {
        setIsLoading(true);
        const res = await api.get(`/dashboard/${userId}`);
        setData(res.data.data);
      } catch (err) {
        console.error("Failed to fetch dashboard:", err);
      } finally {
        setIsLoading(false);
      }
    };

    fetchDashboard();
  }, [userId]);

  const handleAllocateSavings = async () => {
    try {
      setIsAllocating(true);
      await allocateSavings(userId);
      alert("Savings allocated to goals successfully!");
    } catch (err) {
      console.error("Failed to allocate savings:", err);
      alert("Failed to allocate savings. Please try again.");
    } finally {
      setIsAllocating(false);
    }
  };

  const dashboardCards = [
    {
      title: "Total Income",
      value: data.totalIncome,
      icon: (
                        <svg className="w-[220px] h-[220px] max-w-none" fill="none" stroke="currentColor" viewBox="0 0 24 24" style={{width: '220px', height: '220px'}}>
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    ),
      bgColor: "bg-gradient-to-br from-emerald-50 to-green-100",
      textColor: "text-emerald-700",
      valueColor: "text-emerald-600"
    },
    {
      title: "Total Expenses",
      value: data.totalExpenses,
      icon: (
                        <svg className="w-[220px] h-[220px] max-w-none" fill="none" stroke="currentColor" viewBox="0 0 24 24" style={{width: '220px', height: '220px'}}>
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    ),
      bgColor: "bg-gradient-to-br from-rose-50 to-pink-100",
      textColor: "text-rose-700",
      valueColor: "text-rose-600"
    },
    {
      title: "Current Balance",
      value: data.balance,
      icon: (
                        <svg className="w-[220px] h-[220px] max-w-none" fill="none" stroke="currentColor" viewBox="0 0 24 24" style={{width: '220px', height: '220px'}}>
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 9V7a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2m2 4h10a2 2 0 002-2v-6a2 2 0 00-2-2H9a2 2 0 00-2 2v6a2 2 0 002 2zm7-5a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                    ),
      bgColor: "bg-gradient-to-br from-blue-50 to-indigo-100",
      textColor: "text-blue-700",
      valueColor: "text-blue-600"
    }
  ];

  return (
    <>
      <Navbar />

      <div className="gradient-bg min-h-screen">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 text-center">
          {/* Header */}
          <div className="text-center mb-8">
            <h1 className="page-header">Dashboard</h1>
            <p className="page-subheader">Overview of your financial status</p>
          </div>

          {isLoading ? (
            <div className="flex items-center justify-center h-64">
              <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
            </div>
          ) : (
            <>
              {/* Stats Cards */}
              <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
                {dashboardCards.map((card, index) => (
                  <div
                    key={index}
                    className={`${card.bgColor} rounded-2xl p-6 shadow-cool hover:shadow-cool-lg transition-all duration-300 transform hover:-translate-y-1 border border-gray-100`}
                  >
                    <div className="flex items-center justify-between mb-4">
                      <div className={`${card.textColor} opacity-80`}>
                        {card.icon}
                      </div>
                      <div className={`text-sm font-medium ${card.textColor} opacity-70`}>
                        {card.title}
                      </div>
                    </div>
                    <div className={`text-3xl font-bold ${card.valueColor}`}>
                      ₹ {card.value.toLocaleString()}
                    </div>
                  </div>
                ))}
              </div>

              {/* Action Section */}
              <div className="card">
                <h3 className="text-lg font-semibold text-gray-900 mb-4">Quick Actions</h3>
                <div className="flex flex-col sm:flex-row gap-4">
                  <button
                    onClick={handleAllocateSavings}
                    disabled={isAllocating}
                    className="btn-primary flex-1"
                  >
                    {isAllocating ? (
                      <span className="flex items-center justify-center">
                        <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                          <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                          <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                        </svg>
                        Allocating...
                      </span>
                    ) : (
                      <span className="flex items-center justify-center">
                        <svg className="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7h12m0 0l-4-4m4 4l-4 4m0 6H4m0 0l4 4m-4-4l4-4" />
                        </svg>
                        Allocate Savings
                      </span>
                    )}
                  </button>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </>
  );
}
