(ns bo-file-generator.core
  (:require [clojure.java.io :as io])
  
  )

(defn validate [a] 
  (def input (get a 0))
  (def validate_for?  (get a 1))
  (if (contains? input validate_for?) 1 (throw (Exception. "Doesn't have needed key..")))
  )

(defn get_persistable_data[data key]
( def field_length  (get (get data :field_lengths ) key))
(format (str  '%0 field_length 'd) (get (get data :data) key))  
)

(defn writetofile[file_name content]
(with-open [wrtr (io/writer file_name :append true)]
  (.write wrtr content))
)
(
 defn persist [file data]
( def url_schema  (get data :url_schema))
(def file_name (get data :destination_file))
(doseq 
  [key  url_schema]
  (writetofile file_name (get_persistable_data data (keyword key)))
  
)
)
(defn convert [data] 
  (validate [data :url_schema])

  (persist "it05.txt" data)
  (def schema (get data :url_schema))

  )


