import mysql.connector
import random

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="admin",
    database="grocery_db"
)

cursor = conn.cursor()

# -------------------------
# Insert Categories
# -------------------------
categories = ["Fruits", "Vegetables", "Dairy", "Snacks"]

for cat in categories:
    cursor.execute(
        "INSERT INTO categories(category_name) VALUES (%s)",
        (cat,)
    )

conn.commit()

# Fetch category ids
cursor.execute("SELECT category_id FROM categories")
category_ids = [row[0] for row in cursor.fetchall()]

# -------------------------
# Insert Products
# -------------------------
products = [
    ("Apple", 80),
    ("Banana", 40),
    ("Milk", 60),
    ("Potato", 35),
    ("Tomato", 30),
    ("Cheese", 120),
    ("Chips", 50)
]

for (name, price) in products:
    cursor.execute("""
        INSERT INTO products(product_name, price, stock_quantity, category_id)
        VALUES (%s, %s, %s, %s)
    """, (
        name,
        price,
        random.randint(50, 200),
        random.choice(category_ids)
    ))

conn.commit()

# -------------------------
# Insert Stores
# -------------------------
stores = [
    ("Fresh Mart", "Anna Nagar"),
    ("Daily Needs", "T Nagar"),
    ("Green Basket", "Velachery")
]

for name, addr in stores:
    cursor.execute(
        "INSERT INTO stores(store_name, store_address) VALUES (%s,%s)",
        (name, addr)
    )

conn.commit()

# -------------------------
# Insert Delivery Persons
# -------------------------
delivery_people = [
    ("Ravi Kumar", "9876543210"),
    ("Arjun Singh", "9123456780"),
    ("Manoj Das", "9988776655")
]

for name, phone in delivery_people:
    cursor.execute("""
        INSERT INTO delivery_persons(name, mobile_number)
        VALUES (%s,%s)
    """, (name, phone))

conn.commit()

cursor.close()
conn.close()

print("Sample data inserted successfully.")
