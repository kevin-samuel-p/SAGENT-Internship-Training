-- Insert expense categories
INSERT INTO expense_categories (category_id, category_name, category_description) VALUES
(1, 'Operating Expenses', 'Costs for daily operations, including rent, utilities, salaries, and marketing.'),
(2, 'Cost of Goods Sold', 'Direct costs of producing goods or services, such as raw materials and direct labor.'),
(3, 'Fixed Expenses', 'Consistent monthly costs, such as rent, insurance, and salaries.'),
(4, 'Variable Expenses', 'Fluctuating costs, such as shipping, commissions, and raw materials.'),
(5, 'Non-Operating Expenses', 'Costs outside core operations, including interest, legal fees, and depreciation.'),
(6, 'Marketing & Advertising', 'Website maintenance, ads, and promotional materials.'),
(7, 'Travel & Entertainment', 'Business meals, airfare, and lodging.'),
(8, 'Housing', 'Mortgage/rent, property taxes, maintenance.'),
(9, 'Food', 'Groceries and dining out.'),
(10, 'Transportation', 'Car payments, fuel, insurance, and public transit.'),
(11, 'Utilities', 'Electricity, water, internet, and phone.'),
(12, 'Health & Wellness', 'Insurance premiums, doctor visits, gym memberships, etc.'),
(13, 'Others', NULL);

-- Insert income sources
INSERT INTO income_sources (source_id, source_name, source_description) VALUES
(1, 'Salary/Wages', 'Regular income from a full-time or part-time job.'),
(2, 'Freelancing/Consulting', 'Income from specialized, project-based work like writing, design, or coding.'),
(3, 'Business Profits', 'Money generated from operating a business or providing services.'),
(4, 'Commission', 'Payments earned from selling products or services.'),
(5, 'Gig Economy', 'Short-term jobs like delivery, ride-sharing, or manual labor.'),
(6, 'Rental Income', 'Earning money from leasing out property, storage areas, or parking spaces.'),
(7, 'Dividend Income', 'Payments received from owning shares or mutual funds.'),
(8, 'Interest Income', 'Earnings from bank savings accounts, fixed deposits, or bonds.'),
(9, 'Royalties', 'Income from intellectual property, such as books, music, or patents.'),
(10, 'Digital Products/Content', 'Revenue from blogs, YouTube channels, e-books, or online courses.'),
(11, 'Affiliate Marketing', 'Earning commissions by promoting products online.'),
(12, 'Peer-to-Peer Lending', 'Lending money to individuals or businesses through online platforms.'),
(13, 'Capital Gains', 'Profit made from selling assets, such as stocks, property, or valuable items.'),
(14, 'Pensions', 'Regular income received after retirement.'),
(15, 'Gifts/Prizes', 'Winnings from lotteries, games, or puzzles.'),
(16, 'Others', NULL);
