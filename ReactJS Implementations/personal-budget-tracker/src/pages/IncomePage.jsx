import { useEffect, useState } from "react";
import { getIncomes, addIncome, deleteIncome, getIncomeSources } from "../services/incomeService";
import Navbar from "../components/Navbar";

export default function IncomePage() {
  const userId = localStorage.getItem("userId") || null;

  const [incomes, setIncomes] = useState([]);
  const [showModal, setShowModal] = useState(false);

  const [form, setForm] = useState({
    amount: "",
    sourceId: "",
    incomeDate: ""
  });
  const [sources, setSources] = useState([]);

  // Fetch income sources
  useEffect(() => {
    const loadSources = async () => {
      try {
        const res = await getIncomeSources();
        setSources(res.data.data); 
        // because ResponseUtil wraps inside .data
        console.log("Sources:", sources);
      } catch (err) {
        console.error("Failed to fetch sources:", err);
      }
    };

    loadSources();
  }, []);

  // Fetch incomes for current user
  const loadData = async () => {
    if (!userId) return;

    try {
      const res = await getIncomes(userId);
      setIncomes(res.data.data); // ResponseUtil wraps in .data
      console.log("Incomes:", incomes);
    } catch (err) {
      console.error("Failed to fetch incomes:", err);
    }
  };

  useEffect(() => {
    loadData();
  }, [userId]);

  // Add new income
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!userId) return;

    try {
      await addIncome({ ...form, userId });
      setShowModal(false);
      setForm({ amount: "", sourceId: "", incomeDate: "" });
      loadData();
    } catch (err) {
      console.error("Failed to add income:", err);
    }
  };

  // Delete income
  const handleDelete = async (id) => {
    try {
      await deleteIncome(id);
      loadData();
    } catch (err) {
      console.error("Failed to delete income:", err);
    }
  };

  return (
    <>
      <Navbar />

      <div className="p-8">
        <div className="flex justify-between mb-6">
          <h2 className="text-2xl font-bold">Income</h2>

          {!showModal && (
            <button
              className="bg-indigo-600 text-white px-4 py-2 rounded"
              onClick={() => setShowModal(true)}
            >
              Add Income
            </button>
          )}
        </div>

        {incomes.length !== 0 && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">
            <table className="w-full bg-white shadow rounded">
              <thead>
                <tr className="bg-gray-100">
                  <th className="p-3">Source</th>
                  <th className="p-3">Amount</th>
                  <th className="p-3">Date</th>
                  <th className="p-3">Action</th>
                </tr>
              </thead>

              <tbody>
                {incomes.map((i) => (
                  <tr key={i.incomeId} className="border-t">
                    <td className="p-3">
                      {sources[i.sourceId - 1]?.source || "-"}
                    </td>
                    <td className="p-3">₹ {i.amount}</td>
                    <td className="p-3">{i.incomeDate}</td>
                    <td className="p-3">
                      <button
                        className="text-red-600"
                        onClick={() => handleDelete(i.incomeId)}
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
                        <b>Income Source</b>
                      </label>
                    </td>
                    <td className="p-3">
                      <select
                        className="w-full border p-2 mb-3"
                        value={form.sourceId}
                        onChange={(e) => setForm({ ...form, sourceId: e.target.value })}
                      >
                        <option value="">Select source</option>

                        {sources.map((source) => (
                          <option
                            key={source.id}
                            value={source.id}
                            title={source.description || ""}
                          >
                            {source.source}
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
                        <b>Income Date</b>
                      </label>
                    </td>
                    <td className="p-3">
                      <input
                        type="date"
                        className="w-full border p-2 mb-4"
                        value={form.incomeDate}
                        onChange={(e) => setForm({ ...form, incomeDate: e.target.value })}
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
                          disabled={!form.amount || !form.incomeDate || !form.sourceId}
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
