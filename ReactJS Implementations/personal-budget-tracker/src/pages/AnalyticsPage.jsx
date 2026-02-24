import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import Navbar from "../components/Navbar";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  Legend
} from "recharts";

export default function AnalyticsPage() {

  const userId = localStorage.getItem("userId");
  const [data, setData] = useState([]);

  useEffect(() => {
    const load = async () => {
      const res =
        await api.get(`dashboard/analytics/monthly/${userId}`);
      setData(res.data.data);
    };
    load();
  }, []);

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <h2 className="text-3xl font-bold mb-6">
          Monthly Financial Trends
        </h2>

        <div className="bg-white p-6 rounded-xl shadow">

          <LineChart width={800} height={400} data={data}>
            <CartesianGrid stroke="#ccc" />
            <XAxis dataKey="month" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line
              type="monotone"
              dataKey="totalIncome"
              stroke="#16a34a"
            />
            <Line
              type="monotone"
              dataKey="totalExpense"
              stroke="#dc2626"
            />
          </LineChart>

        </div>

      </div>
    </>
  );
}