export const buildFinancialContext = ({
  monthlyIncome,
  monthlyExpenses,
  balance,
  budgets,
  goals
}) => {

  const budgetSummary = budgets.map(b =>
    `${b.category}: spent ${b.spent} / limit ${b.limit}`
  ).join("\n");

  const goalSummary = goals.map(g =>
    `${g.goalName}: ${g.currentAmount} / ${g.targetAmount}`
  ).join("\n");

  return `
User Financial Summary:

Total Monthly Income: ${monthlyIncome}
Total Monthly Expenses: ${monthlyExpenses}
Current Balance: ${balance}

Budgets:
${budgetSummary || "No budgets set"}

Goals:
${goalSummary || "No goals set"}

Provide clear, helpful, and concise advice.
`;
};