import React, { useState, useEffect } from "react";
import { buildFinancialContext } from "../services/contextBuilder";
import { getBudgetUsage } from "../services/budgetService";
import { getGoals } from "../services/goalService";
import api from "../api/axiosConfig";

const AIChatPanel = () => {
  const userId = localStorage.getItem("userId") || null;

  const [open, setOpen] = useState(false);
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");

  // 🔹 Load Puter.js once when component mounts
  useEffect(() => {
    if (!document.getElementById("puter-script")) {
      const script = document.createElement("script");
      script.src = "https://js.puter.com/v2/";
      script.async = true;
      script.id = "puter-script";
      document.body.appendChild(script);
    }
  }, []);

  const togglePanel = () => setOpen(!open);

  const sendMessage = async () => {
    if (!input.trim()) return;

    try {
      // Fetch financial data
      const [dashboardRes, budgetRes, goalRes] = await Promise.all([
        api.get(`/dashboard/${userId}`),
        getBudgetUsage(userId),
        getGoals(userId)
      ]);

      const dashboard = dashboardRes.data.data;
      const budgets = budgetRes.data;
      const goals = goalRes.data.data;

      console.log(dashboard);
      console.log(budgets);
      console.log(goals);

      console.log("Budgets is array?", Array.isArray(budgets));
      console.log("Goals is array?", Array.isArray(goals));

      console.log("Context payload:", {
        monthlyIncome: dashboard?.totalIncome,
        monthlyExpenses: dashboard?.totalExpenses,
        balance: dashboard?.balance,
        budgets,
        goals
      });
      // Build financial context
      const context = buildFinancialContext({
        monthlyIncome: dashboard.totalIncome,
        monthlyExpenses: dashboard.totalExpenses,
        balance: dashboard.balance,
        budgets,
        goals
      });

      // 🔹 Example Puter AI call
      let reply;

      if (window.puter && window.puter.ai) {
        const response = await window.puter.ai.chat({
          messages: [
            { role: "system", content: context },
            { role: "user", content: input }
          ]
        });

        reply = response.message?.content || "No response received.";
      } else {
        reply = await askAI(input, context);
      }

      setMessages(prev => [
        ...prev,
        { role: "user", content: input },
        { role: "assistant", content: reply }
      ]);

      setInput("");
    } catch (error) {
      console.error("AI Chat Error:", error);
    }
  };

  return (
    <>
      {/* Puter script fallback (in case JS injection fails) */}
      <script
        src="https://js.puter.com/v2/"
        async
        id="puter-script-fallback"
      ></script>

      <div className="ai-chat-container">
        <button onClick={togglePanel}>
          {open ? "Close AI Chat" : "Open AI Chat"}
        </button>

        {open && (
          <div className="ai-chat-panel">
            <div className="messages">
              {messages.map((msg, index) => (
                <div key={index} className={`message ${msg.role}`}>
                  {msg.content}
                </div>
              ))}
            </div>

            <div className="input-area">
              <input
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Ask about your finances..."
              />
              <button onClick={sendMessage}>Send</button>
            </div>
          </div>
        )}
      </div>
    </>
  );
};

export default AIChatPanel;