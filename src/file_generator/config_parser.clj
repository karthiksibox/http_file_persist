(ns file-generator.config-parser
  (:require [clojure.java.io :as io])
  )
;(def config_file "config.properties" )

(defn load-props
  [file-name]
  (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)]
    (let [props (java.util.Properties.)]
      (.load props reader)
      (into {} (for [[k v] props] [(keyword k) (read-string v)])))))

(defn get_config [configuration]
  ( get (load-props "config.properties") configuration))


