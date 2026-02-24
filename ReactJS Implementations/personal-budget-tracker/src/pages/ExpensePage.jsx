import { useEffect, useState } from "react";
import { getExpenses, addExpense, deleteExpense, getExpenseCategories } from "../services/expenseService";
import Navbar from "../components/Navbar";

export default function ExpensePage() {
  const userId = localStorage.getItem("userId") || null;

  const [expenses, setExpenses] = useState([]);
  const [showModal, setShowModal] = useState(false);

  const [form, setForm] = useState({
    amount: "",
    categoryId: "",
    expenseDate: ""
  });
  const [categories, setCategories] = useState([]);

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

  // Fetch expenses for current user
  const loadData = async () => {
    if (!userId) return;

    try {
      const res = await getExpenses(userId);
      setExpenses(res.data.data); // ResponseUtil wraps in .data
      console.log("Expenses:", expenses);
    } catch (err) {
      console.error("Failed to fetch expenses:", err);
    }
  };

  useEffect(() => {
    loadData();
  }, [userId]);

  // Add new expense
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!userId) return;

    try {
      await addExpense({ ...form, userId });
      setShowModal(false);
      setForm({ amount: "", categoryId: "", expenseDate: "" });
      loadData();
    } catch (err) {
      console.error("Failed to add expense:", err);
    }
  };

  // Delete expense
  const handleDelete = async (id) => {
    try {
      await deleteExpense(id);
      loadData();
    } catch (err) {
      console.error("Failed to delete expense:", err);
    }
  };

  return (
    <>
      <Navbar />

      <div className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 text-center">
          {/* Header */}
          <div className="text-center">
            <h2 className="text-3xl font-bold text-gray-900">Expenses</h2>
            <p className="mt-2 text-gray-600">Manage and track your expenses</p>
          </div>

          <div className="text-center">
            {!showModal && (
              <button
                className="mt-4 sm:mt-0 bg-indigo-600 text-white px-6 py-3 rounded-lg hover:bg-indigo-700 transition-colors duration-200 font-medium shadow-lg hover:shadow-xl transform hover:-translate-y-0.5"
                onClick={() => setShowModal(true)}
              >
                <span className="flex items-center justify-center">
                  Add Expense
                </span>
              </button>
            )}
          </div>

        {expenses.length === 0 && (
          <div className="text-center py-12">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-gray-100 rounded-full mb-4">
              <svg className="w-[220px] h-[220px] max-w-none text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" style={{width: '220px', height: '220px'}}>
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
              </svg>
            </div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">No expenses yet</h3>
            <p className="text-gray-500 mb-6">Start tracking your expenses by adding your first one.</p>
            <button
              className="bg-indigo-600 text-white px-6 py-3 rounded-lg hover:bg-indigo-700 transition-colors duration-200 font-medium"
              onClick={() => setShowModal(true)}
            >
              Add Your First Expense
            </button>
          </div>
        )}

        {expenses.length !== 0 && (
          <div className="bg-white rounded-xl shadow-lg overflow-hidden">
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead className="bg-gradient-to-r from-indigo-50 to-purple-50">
                  <tr>
                    <th className="p-4 text-left font-semibold text-gray-700">Category</th>
                    <th className="p-4 text-left font-semibold text-gray-700">Amount</th>
                    <th className="p-4 text-left font-semibold text-gray-700">Date</th>
                    <th className="p-4 text-left font-semibold text-gray-700">Action</th>
                  </tr>
                </thead>

                <tbody>
                  {expenses.map((e) => (
                    <tr key={e.expenseId} className="border-t border-gray-100 hover:bg-gray-50 transition-colors">
                      <td className="p-4">
                        <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-indigo-100 text-indigo-800">
                          {categories[e.categoryId - 1]?.category || "-"}
                        </span>
                      </td>
                      <td className="p-4 font-semibold text-gray-900">₹ {e.amount}</td>
                      <td className="p-4 text-gray-600">{e.expenseDate}</td>
                      <td className="p-4">
                        <button
                          className="text-red-600 hover:text-red-800 hover:bg-red-50 px-3 py-1 rounded-md transition-all duration-200 font-medium"
                          onClick={() => handleDelete(e.expenseId)}
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        )}

        {showModal && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 backdrop-blur-sm z-50">
            <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md mx-4 transform transition-all">
              <form onSubmit={handleSubmit}>
                <div className="p-6 border-b border-gray-100">
                  <h3 className="text-xl font-bold text-gray-900">Add New Expense</h3>
                </div>
                
                <div className="p-6 space-y-4">
                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-2">
                      Expense Category
                    </label>
                    <select
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-all duration-200"
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
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-2">
                      Amount
                    </label>
                    <input
                      type="number"
                      placeholder="Enter amount"
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-all duration-200"
                      value={form.amount}
                      onChange={(e) => setForm({ ...form, amount: e.target.value })}
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-semibold text-gray-700 mb-2">
                      Expense Date
                    </label>
                    <input
                      type="date"
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-all duration-200"
                      value={form.expenseDate}
                      onChange={(e) => setForm({ ...form, expenseDate: e.target.value })}
                    />
                  </div>
                </div>

                <div className="p-6 bg-gray-50 rounded-b-2xl flex justify-end gap-3">
                  <button
                    type="button"
                    onClick={() => setShowModal(false)}
                    className="px-6 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-100 transition-colors duration-200 font-medium"
                  >
                    Cancel
                  </button>
                  <button
                    type="submit"
                    className="px-6 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors duration-200 font-medium disabled:opacity-50 disabled:cursor-not-allowed"
                    disabled={!form.amount || !form.expenseDate || !form.categoryId}
                  >
                    Save Expense
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}
        </div>
      </div>
    </>
  );
}
