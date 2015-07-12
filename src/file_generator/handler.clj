(ns file-generator.handler
  (:require [compojure.core :refer :all]
            [clj-http.client :as client]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [file-generator.core :as dsl ]
            [file-generator.config-parser :refer :all ]
            [file-generator.job-tracker :refer [track] ]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults ]]))

(def resources (get_config :resources))

(defn fetch_config [resource]
  (def base_url (get_config :base_url))
  (get (client/get (str base_url resource)) :body)
  )
(def start  (System/currentTimeMillis))

(defn process[resource]
  (def start  (System/currentTimeMillis))
  (def config (fetch_config resource) )
  (dsl/convert (json/read-str config :key-fn keyword)) ;;persisted to file here
  (def end  (System/currentTimeMillis))
  (cond (= (get_config :track_jobs) true)
        (track start end)
        )
)



(defn generate-file[]
  (def status (map #(process %1) resources))
  (println status)
)


(defroutes app-routes
  (GET "/" [] (generate-file)  "Done")

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

