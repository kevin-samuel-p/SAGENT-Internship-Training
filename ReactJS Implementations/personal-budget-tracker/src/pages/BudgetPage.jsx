import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { getBudgetUsage, addBudget } from "../services/budgetService";
import { getExpenseCategories } from "../services/expenseService";

export default function BudgetPage() {
  const userId = localStorage.getItem("userId");

  const [budgets, setBudgets] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [categories, setCategories] = useState([]);

  const [form, setForm] = useState({
    categoryId: "",
    monthyLimit: "",
    month: new Date().getMonth() + 1,
    year: new Date().getFullYear()
  });

  // Fetch expense categories
  useEffect(() => {
    const loadCategories = async () => {
      try {
        const res = await getExpenseCategories();
        setCategories(res.data.data);
        // because ResponseUtil wraps inside .data
        console.log("Categories:", categories);
      } catch (err) {
        console.error("Failed to fetch categories:", err);
      }
    };

    loadCategories();
  }, []);

  const loadData = async () => {
    const res = await getBudgetUsage(userId);
    setBudgets(res.data);
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addBudget({
      ...form,
      userId: userId
    });

    setShowModal(false);
    loadData();
  };

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">
        <h2 className="text-3xl font-bold mb-6">
          Budget Usage
        </h2>

        <button
          onClick={() => setShowModal(true)}
          className="bg-indigo-600 text-white px-4 py-2 rounded"
        >
          Add Budget
        </button>

        <div className="space-y-6">
          {budgets.map((b, index) => (

            <div key={index} className="bg-white p-6 rounded-xl shadow">

            <div className="flex justify-between mb-2">
              <span className="font-semibold">
                {b.categoryName}
              </span>
              <span>
                ₹ {b.totalSpent} / ₹ {b.monthlyLimit}
              </span>
              </div>

              <div className="w-full bg-gray-200 rounded-full h-4">
                <div
                  className="bg-indigo-600 h-4 rounded-full"
                  style={{ width: `${Math.min(b.percentageUsed,100)}%` }}
                />
              </div>

              <div className="text-right text-sm mt-1">
                {b.percentageUsed.toFixed(1)}%
              </div>

            </div>
          ))}
        </div>

        {showModal && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">
            <form
              onSubmit={handleSubmit}
              className="bg-white p-6 rounded-xl shadow w-96"
            >
              <h3 className="text-xl mb-4">
                Add Budget
              </h3>

              <select
                className="w-full border p-2 mb-3"
                value={form.categoryId}
                onChange={(e) => setForm({ ...form, categoryId: e.target.value })}
              >
                <option value="">Select category</option>

                {categories.map((category) => (
                  <option
                    key={category.id}
                    value={category.id}
                    title={category.description || ""}
                  >
                    {category.category}
                  </option>
                ))}
              </select>

              <input
                placeholder="Monthly Limit"
                type="number"
                className="w-full border p-2 mb-3"
                onChange={(e) => setForm({
                    ...form, monthlyLimit: e.target.value 
                  })
                }
              />

              <div className="flex gap-2 mb-3">
                <select
                  className="w-full border p-2 mb-3"
                  value={form.month}
                  onChange={(e) =>
                    setForm({
                      ...form,
                      month: Number(e.target.value), // store as number (1–12)
                    })
                  }
                >
                  <option value="">Select Month</option>

                  <option value={1}>January</option>
                  <option value={2}>February</option>
                  <option value={3}>March</option>
                  <option value={4}>April</option>
                  <option value={5}>May</option>
                  <option value={6}>June</option>
                  <option value={7}>July</option>
                  <option value={8}>August</option>
                  <option value={9}>September</option>
                  <option value={10}>October</option>
                  <option value={11}>November</option>
                  <option value={12}>December</option>
                </select>

                <input 
                  type="number"
                  placeholder="Year"
                  className="w-1/2 border p-2"
                  value={form.year}
                  onChange={(e) => setForm({ 
                      ...form, year: e.target.value 
                    })
                  }
                />
              </div>

              <div className="flex justify-end gap-2">
                <button
                  type="button"
                  onClick={() => setShowModal(false)}
                  className="px-3 py-1 border rounded"
                >
                  Cancel
                </button>

                <button
                  type="submit"
                  className="bg-indigo-600 text-white px-3 py-1 rounded"
                >
                  Save
                </button>
              </div>
            </form>
          </div>
        )}
      </div>
    </>
  );
}