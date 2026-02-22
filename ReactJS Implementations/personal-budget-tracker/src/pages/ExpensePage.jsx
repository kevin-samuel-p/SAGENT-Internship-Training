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

      <div className="p-8">
        <div className="flex justify-between mb-6">
          <h2 className="text-2xl font-bold">Expense</h2>

          {!showModal && (
            <button
              className="bg-indigo-600 text-white px-4 py-2 rounded"
              onClick={() => setShowModal(true)}
            >
              Add Expense
            </button>
          )}
        </div>

        {expenses.length !== 0 && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">
            <table className="w-full bg-white shadow rounded">
              <thead>
                <tr className="bg-gray-100">
                  <th className="p-3">Category</th>
                  <th className="p-3">Amount</th>
                  <th className="p-3">Date</th>
                  <th className="p-3">Action</th>
                </tr>
              </thead>

              <tbody>
                {expenses.map((e) => (
                  <tr key={e.expenseId} className="border-t">
                    <td className="border-t">
                      {categories[e.categoryId - 1]?.category || "-"}
                    </td>
                    <td className="p-3">₹ {e.amount}</td>
                    <td className="p-3">{e.expenseDate}</td>
                    <td className="p-3">
                      <button
                        className="text-red-600"
                        onClick={() => handleDelete(e.expenseId)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <br /><br /><br />
          </div>
        )}

        {showModal && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">
            <form
              onSubmit={handleSubmit}
              className="bg-white p-6 rounded-xl shadow w-96"
            >
              <table className="w-full">
                <tbody>
                  <tr>
                    <td className="p-3">
                      <label className="block mb-1">
                        <b>Expense Category</b>
                      </label>
                    </td>
                    <td className="p-3">
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
                    </td>
                  </tr>

                  <tr>
                    <td className="p-3">
                      <label className="block mb-1">
                        <b>Amount</b>
                      </label>
                    </td>
                    <td className="p-3">
                      <input
                        placeholder="Amount"
                        className="w-full border p-2 mb-3"
                        value={form.amount}
                        onChange={(e) => setForm({ ...form, amount: e.target.value })}
                      />
                    </td>
                  </tr>

                  <tr>
                    <td className="p-3">
                      <label className="block mb-1">
                        <b>Expense Date</b>
                      </label>
                    </td>
                    <td className="p-3">
                      <input
                        type="date"
                        className="w-full border p-2 mb-4"
                        value={form.expenseDate}
                        onChange={(e) => setForm({ ...form, expenseDate: e.target.value })}
                      />
                    </td>
                  </tr>

                  <tr>
                    <td colSpan="2" className="p-3">
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
                          disabled={!form.amount || !form.expenseDate || !form.categoryId}
                        >
                          Save
                        </button>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </form>
          </div>
        )}
      </div>
    </>
  );
}
