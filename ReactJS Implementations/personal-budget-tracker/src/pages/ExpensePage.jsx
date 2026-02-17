import { useEffect, useState } from "react";
import { getExpenses, addExpense, deleteExpense } from "../services/expenseService";
import Navbar from "../components/Navbar";

export default function ExpensePage() {

    const userId = localStorage.getItem("userId");

    const [expenses, setExpenses] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const [form, setForm] = useState({
        amount: "",
        sourceId: "",
        expenseDate: ""
    });

    const loadData = async () => {
        const res = await getExpenses(userId);
        setExpenses(res.data);
    };

    useEffect(() => {
        loadData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        await addExpense({
            ...form,
            userId: userId
        });

        setShowModal(false);
        loadData();
    };

    const handleDelete = async (id) => {
        await deleteExpense(id);
        loadData();
    };

    return (
        <>
            <Navbar />

            <div className="p-8">

                <div className="flex justify-between mb-6">
                    <h2 className="text-2xl font-bold">Expense</h2>

                    <button
                        className="bg-indigo-600 text-white px-4 py-2 rounded"
                        onClick={() => setShowModal(true)}
                    >
                        Add Expense
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
                        {expenses.map(i => (
                            <tr key={i.expenseId} className="border-t">
                                <td className="p-3">₹ {i.amount}</td>
                                <td className="p-3">{i.expenseDate}</td>
                                <td className="p-3">
                                    <button
                                        className="text-red-600"
                                        onClick={() => handleDelete(i.expenseId)}
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
                            <h3 className="text-xl mb-4">Add Expense</h3>

                            <input
                                placeholder="Amount"
                                className="w-full border p-2 mb-3"
                                onChange={(e) =>
                                    setForm({ ...form, amount: e.target.value })
                                }
                            />

                            <input
                                type="date"
                                className="w-full border p-2 mb-4"
                                onChange={(e) =>
                                    setForm({ ...form, expenseDate: e.target.value })
                                }
                            />

                            <div className="flex justify-end gap-2">
                                <button
                                    type="button"
                                    onClick={() => setShowModal(false)}
                                    className="px-3 py-1 border rounded"
                                >
                                    Cancel
                                </button>

                                <button className="bg-indigo-600 text-white px-3 py-1 rounded">
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
