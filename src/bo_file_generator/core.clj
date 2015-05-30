(ns bo-file-generator.core
  (:require [clojure.java.io :as io])
  )

(defn validate [input validate_for?] 
  (cond (not (contains? input validate_for?)) (throw (Exception. "Doesn't have needed key..")))
  )

(defn get_persistable_data[data key]
( def field_length  (get (get data :field_lengths ) key))
(def value(get (get data :data) key))
(cond (= (type value) java.lang.Long)
(format (str  '%0 field_length 'd) value)  
:else (format (str  '%- field_length 'S) value)  
)
)


(defn writetofile[file_name content]
(with-open [wrtr (io/writer file_name :append true)]
  (.write wrtr content))
)


(defn persist [data]
( def url_schema  (get data :url_schema))
(def file_name (get data :destination_file))
(doseq 
  [key  url_schema]
  (writetofile file_name (get_persistable_data data (keyword key)))
  
)
)


(defn convert [data] 
  (validate data :url_schema)
  (validate data :destination_file)
  (validate data :field_lengths)
  (validate data :data)
  (persist data)
)


