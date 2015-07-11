(ns migrations.001-migrate
  (:use [korma core db]))

(defn up []
  (transaction
   (exec-raw (str "CREATE TABLE job_tracker ("
                  "id INT PRIMARY KEY,"
                  "firstname TEXT NOT NULL,"
                  "lasname TEXT NOT NULL"
                  ")"))
   (exec-raw (str "CREATE TABLE book ("
                  "id INT PRIMARY KEY,"
                  "title TEXT NOT NULL,"
                  "author_id INT REFERENCES author(id)"
                  ")"))))

(defn down []
  (transaction
   (exec-raw "DROP TABLE book")
   (exec-raw "DROP TABLE author")))
