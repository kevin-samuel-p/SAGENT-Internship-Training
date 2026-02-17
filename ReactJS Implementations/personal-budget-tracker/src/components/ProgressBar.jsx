import React from "react";

const ProgressBar = ({ spent, limit }) => {
  const percentage = Math.min((spent / limit) * 100, 100);

  let color = "bg-green-500";
  if (percentage >= 80) color = "bg-yellow-500";
  if (percentage >= 100) color = "bg-red-600";

  return (
    <div className="w-full">
      <div className="w-full bg-gray-200 rounded-full h-4">
        <div
          className={`${color} h-4 rounded-full transition-all duration-500`}
          style={{ width: `${percentage}%` }}
        />
      </div>
      <div className="text-sm mt-1 text-right">
        {percentage.toFixed(0)}%
      </div>
    </div>
  );
};

export default ProgressBar;
