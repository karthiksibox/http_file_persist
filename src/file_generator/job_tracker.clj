(ns file-generator.job-tracker
  (:import java.util.Date java.sql.Timestamp)
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [clojure.string :as str]
            [clj-time.core :as time]
            [file-generator.config-parser :refer [get_config] ]
            ))

(defdb db (postgres {:db (str (get_config :db))
                     :user (str (get_config :user))
                     :password (str (get_config :password))
                     :host (str (get_config :host))
                     :port (str (get_config :port))
                     }))

(defentity jobs)
(defn track[start end]
  (insert jobs (values {:started_at (java.sql.Timestamp. start) :completed_at (java.sql.Timestamp. end)}))
  )

