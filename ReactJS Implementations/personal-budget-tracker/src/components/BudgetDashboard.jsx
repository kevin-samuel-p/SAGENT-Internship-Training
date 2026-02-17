import React, { useEffect, useState } from "react";
import { getMonthlyBudgets } from "../services/budgetService";
import BudgetCard from "./BudgetCard";

const BudgetDashboard = ({ userId }) => {
  const [budgets, setBudgets] = useState([]);
  const [loading, setLoading] = useState(true);

  const month = new Date().getMonth() + 1;
  const year = new Date().getFullYear();

  useEffect(() => {
    fetchBudgets();
  }, []);

  const fetchBudgets = async () => {
    try {
      const response = await getMonthlyBudgets(userId, month, year);
      const data = response.data.data;

      // 🔹 TEMP: mock spent data (replace with API call)
      const enriched = data.map((b) => ({
        ...b,
        spent: Math.random() * b.monthlyLimit
      }));

      setBudgets(enriched);
    } catch (error) {
      console.error("Error fetching budgets", error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <p>Loading budgets...</p>;

  const totalLimit = budgets.reduce(
    (sum, b) => sum + Number(b.monthlyLimit),
    0
  );

  const totalSpent = budgets.reduce(
    (sum, b) => sum + Number(b.spent),
    0
  );

  const remaining = totalLimit - totalSpent;

  return (
    <div className="p-8 bg-gray-100 min-h-screen">
      <h2 className="text-2xl font-bold mb-6">
        Monthly Budget Overview
      </h2>

      {/* 🔥 SUMMARY SECTION */}
      <div className="bg-white shadow-md rounded-xl p-6 mb-8">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 text-center">

          <div>
            <p className="text-gray-500 text-sm">Total Budget</p>
            <p className="text-xl font-bold text-blue-600">
              ${totalLimit.toFixed(2)}
            </p>
          </div>

          <div>
            <p className="text-gray-500 text-sm">Total Spent</p>
            <p className="text-xl font-bold text-purple-600">
              ${totalSpent.toFixed(2)}
            </p>
          </div>

          <div>
            <p className="text-gray-500 text-sm">Remaining</p>
            <p
              className={`text-xl font-bold ${
                remaining < 0 ? "text-red-600" : "text-green-600"
              }`}
            >
              ${remaining.toFixed(2)}
            </p>
          </div>

        </div>
      </div>

      {/* 📊 BUDGET GRID */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {budgets.map((budget) => (
          <BudgetCard
            key={budget.budgetId}
            categoryName={budget.categoryName}
            monthlyLimit={Number(budget.monthlyLimit)}
            spent={Number(budget.spent)}
          />
        ))}
      </div>
    </div>
  );
};

export default BudgetDashboard;
