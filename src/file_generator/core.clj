(ns bo-file-generator.core
  (:require [clojure.java.io :as io])
  )
(def exception_message "Doesn't have needed key.. expected")
(def mandatory_fields '(:url_schema :destination_file :field_lengths :data))

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
(def url_schema  (get data :url_schema))
(def file_name (get data :destination_file))
(doseq 
  [key  url_schema]
  (writetofile file_name (get_persistable_data data (keyword key)))
  
)
)

(defn validate [data mandatory_fields]
  (loop [mandatory_field mandatory_fields]
    (when (not (empty? mandatory_field))
  (cond (contains? data (first mandatory_field)) nil
    :else (throw (Exception. exception_message)))
    (recur (rest mandatory_field))
  )
)
)

(defn convert [data] 
  (validate data mandatory_fields)
  (persist data)
)



