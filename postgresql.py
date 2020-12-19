# Imports

import psycopg2

# PostgreSQL connection

try:
        conn = psycopg2.connect("dbname='distribuidos' password='root' user='admin' host='127.0.0.1' port='5432'")

        cursor = conn.cursor()

        create_table_query = """CREATE TABLE mobile (ID INT PRIMARY KEY NOT NULL, MODEL TEXT NOT NULL, PRICE REAL);"""

        cursor.execute(create_table_query)

        insert_query = """INSERT INTO mobile (ID, MODEL, PRICE) VALUES (%s, %s, %s)"""
        record_to_insert = (5, "One Plus 6", 950)
        cursor.execute(insert_query, record_to_insert)

        conn.commit()
        count = cursor.rowcount()
        print (count, "Record inserted successfully into mobile table")

except (Exception, psycopg2.Error) as error:
        if (conn):
                print("PostgreSQL error", error)

finally:
        # Closing database connection
        if (conn):
                cursor.close()
                conn.close()
                print("PostgreSQL connection is closed")
