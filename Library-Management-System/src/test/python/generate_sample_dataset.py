import random
import string
import mysql.connector
from faker import Faker

fake = Faker()

# ---- DB connection ----
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="admin",
    database="library_db"
)
cursor = conn.cursor()

# ---------------- USERS ----------------
for _ in range(50):
    name = fake.name()
    email = fake.unique.email()
    password = ''.join(random.choices(string.ascii_letters + string.digits, k=10))

    cursor.execute("""
        INSERT INTO users (user_name, user_email, user_password, user_role)
        VALUES (%s, %s, %s, %s)
    """, (name, email, password, '0'))

# ---------------- AUTHORS ----------------
author_ids = []
for _ in range(20):
    name = fake.name()

    cursor.execute("""
        INSERT INTO author (author_name)
        VALUES (%s)
    """, (name,))
    author_ids.append(cursor.lastrowid)

# ---------------- SUBJECTS ----------------
subjects_list = [
    "Science", "Mathematics", "History", "Computer Science",
    "Physics", "Chemistry", "Literature", "Economics"
]

subject_ids = []
for s in subjects_list:
    cursor.execute("""
        INSERT INTO subject (subject_name)
        VALUES (%s)
    """, (s,))
    subject_ids.append(cursor.lastrowid)

# ---------------- BOOKS ----------------
for _ in range(100):
    title = fake.sentence(nb_words=4)
    author_id = random.choice(author_ids)
    subject_id = random.choice(subject_ids)

    cursor.execute("""
        INSERT INTO book (book_title, author_id, subject_id, book_status)
        VALUES (%s, %s, %s, %s)
    """, (title, author_id, subject_id, '0'))

conn.commit()
cursor.close()
conn.close()

print("Database populated successfully.")