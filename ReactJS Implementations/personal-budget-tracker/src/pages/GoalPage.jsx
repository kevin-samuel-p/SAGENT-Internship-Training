import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { getGoals, addGoal, deleteGoal, contribute } from "../services/goalService";

export default function GoalPage() {

  const userId = localStorage.getItem("userId");

  const [goals, setGoals] = useState([]);
  const [showModal, setShowModal] = useState(false);

  const [form, setForm] = useState({
    goalName: "",
    targetAmount: "",
    currentAmount: 0,
    targetDate: ""
  });

  const loadData = async () => {
    const res = await getGoals(userId);
    setGoals(res.data.data);
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    await addGoal({
      ...form,
      userId: userId
    });

    setShowModal(false);
    loadData();
  };

  const handleDelete = async (id) => {
    await deleteGoal(id);
    loadData();
  };

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="flex justify-between mb-6">
          <h2 className="text-3xl font-bold">
            Financial Goals
          </h2>

          <button
            onClick={() => setShowModal(true)}
            className="bg-indigo-600 text-white px-4 py-2 rounded"
          >
            Add Goal
          </button>
        </div>

        <div className="space-y-6">
          {goals.map((g) => {

            const percent =
              (g.currentAmount / g.targetAmount) * 100;

            return (
              <div key={g.goalId}
                className="bg-white p-6 rounded-xl shadow">

                <div className="flex justify-between mb-2">
                  <div>
                    <h3 className="font-semibold text-lg">
                      {g.goalName}
                    </h3>
                    <p className="text-sm text-gray-500">
                      Target: ₹ {g.targetAmount}
                    </p>
                  </div>

                  <button
                    onClick={async () => {
                      const amount = prompt("Enter contribution amount");
                      if (!amount) return;

                      await contribute(goalId, amount);
                      loadData();
                    }}
                    className="text-indigo-600 mr-4"
                  >
                    Contribute
                  </button>

                  <button
                    onClick={() => handleDelete(g.goalId)}
                    className="text-red-600"
                  >
                    Delete
                  </button>
                </div>

                <div className="w-full bg-gray-200 rounded-full h-4">
                  <div
                    className="bg-green-600 h-4 rounded-full"
                    style={{ width: `${Math.min(percent,100)}%` }}
                  />
                </div>

                <div className="text-right text-sm mt-1">
                  ₹ {g.currentAmount} saved ({percent.toFixed(1)}%)
                </div>

              </div>
            );
          })}
        </div>

        {/* Add Goal Modal */}
        {showModal && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40">

            <form
              onSubmit={handleSubmit}
              className="bg-white p-6 rounded-xl shadow w-96"
            >
              <h3 className="text-xl mb-4">
                Add Goal
              </h3>

              <input
                placeholder="Goal Name"
                className="w-full border p-2 mb-3"
                onChange={(e) =>
                  setForm({ ...form, goalName: e.target.value })
                }
              />

              <input
                type="number"
                placeholder="Target Amount"
                className="w-full border p-2 mb-3"
                onChange={(e) =>
                  setForm({ ...form, targetAmount: e.target.value })
                }
              />

              <input
                type="date"
                className="w-full border p-2 mb-4"
                onChange={(e) =>
                  setForm({ ...form, targetDate: e.target.value })
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