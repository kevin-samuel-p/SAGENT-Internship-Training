import { useEffect, useState, useContext } from "react";
import { getIncomes, addIncome, deleteIncome } from "../services/incomeService";
import Navbar from "../components/Navbar";
import { AuthContext } from "../auth/AuthContext";

export default function IncomePage() {
    const { userId } = useContext(AuthContext);

    const [incomes, setIncomes] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const [form, setForm] = useState({
        amount: "",
        sourceId: "",
        incomeDate: ""
    });

    // Fetch incomes for current user
    const loadData = async () => {
        if (!userId) return;

        try {
            const res = await getIncomes(userId);
            setIncomes(res.data.data); // ResponseUtil wraps in .data
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

                    <button
                        className="bg-indigo-600 text-white px-4 py-2 rounded"
                        onClick={() => setShowModal(true)}
                    >
                        Add Income
                    </button>
                </div>

                <table className="w-full bg-white shadow rounded">
                    <thead>
                        <tr className="bg-gray-100">
                            <th className="p-3">Amount</th>
                            <th className="p-3">Date</th>
                            <th className="p-3">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {incomes.map((i) => (
                            <tr key={i.incomeId} className="border-t">
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

                {showModal && (
                    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">
                        <form
                            onSubmit={handleSubmit}
                            className="bg-white p-6 rounded-xl shadow w-96"
                        >
                            <h3 className="text-xl mb-4">Add Income</h3>

                            <input
                                placeholder="Amount"
                                className="w-full border p-2 mb-3"
                                value={form.amount}
                                onChange={(e) => setForm({ ...form, amount: e.target.value })}
                            />

                            <input
                                type="date"
                                className="w-full border p-2 mb-4"
                                value={form.incomeDate}
                                onChange={(e) => setForm({ ...form, incomeDate: e.target.value })}
                            />

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
                                    disabled={!form.amount || !form.incomeDate}
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
