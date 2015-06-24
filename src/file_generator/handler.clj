(ns bo-file-generator.handler
  (:require [compojure.core :refer :all]
            [clj-http.client :as client]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [bo-file-generator.core :as dsl ]
            [bo-file-generator.config-parser :as read_config ]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults ]]))


(defn fetch_config [resource]
  (def base_url (read_config/get_config :base_url))
  (get (client/get (str base_url resource)) :body)
)


(defroutes app-routes
  (GET "/" [] 
       (def price_config (fetch_config "/it05.json") )
       (dsl/convert (json/read-str  price_config :key-fn keyword)) ;;persisted to file here
       "Done")

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
