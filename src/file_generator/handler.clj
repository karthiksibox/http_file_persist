(ns file-generator.handler
  (:require [compojure.core :refer :all]
            [clj-http.client :as client]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [file-generator.core :as dsl ]
            [file-generator.config-parser :as read_config ]
            [file-generator.job-tracker :refer [track] ]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults ]]))


(defn fetch_config [resource]
  (def base_url (read_config/get_config :base_url))
  (get (client/get (str base_url resource)) :body)
  )


(defn file_persist [target]
(def start  (System/currentTimeMillis))
  (def price_config (fetch_config target) )
  (dsl/convert (json/read-str  price_config :key-fn keyword)) ;;persisted to file here

  (def end  (System/currentTimeMillis))
    (track start end)
  )
(defroutes app-routes
  (GET "/" [] (file_persist "/it05.json")
       "Done")

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

