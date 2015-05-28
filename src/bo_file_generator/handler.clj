(ns bo-file-generator.handler
  (:require [compojure.core :refer :all]
            [clj-http.client :as client]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [bo-file-generator.core :as dsl ]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults ]]))
(def configs {} )



(defn fetch_price_config [url]
  (get (client/get "http://localhost:7777/it05.json") :body))

(defroutes app-routes
  (GET "/" [] (fn [req] )(def price_config (fetch_price_config "http://localhost:7777/it05.json") 
                 )  (dsl/convert (json/read-str  price_config :key-fn keyword)) "ASEFDD" )
  
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
