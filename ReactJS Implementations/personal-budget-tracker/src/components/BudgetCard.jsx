import React from "react";
import ProgressBar from "./ProgressBar";

const BudgetCard = ({ categoryName, monthlyLimit, spent }) => {
  return (
    <div className="bg-white shadow-md rounded-xl p-5">
      <h3 className="text-lg font-semibold mb-2">{categoryName}</h3>

      <div className="flex justify-between text-sm mb-2">
        <span>${spent.toFixed(2)} spent</span>
        <span>${monthlyLimit.toFixed(2)} limit</span>
      </div>

      <ProgressBar spent={spent} limit={monthlyLimit} />
    </div>
  );
};

export default BudgetCard;
