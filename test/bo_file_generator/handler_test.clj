(ns bo-file-generator.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [bo-file-generator.core :refer :all]
            [bo-file-generator.core :as dsl]
             [bo-file-generator.handler :refer :all]))

;(deftest test-app
  ;(testing "main route"
    ;(let [response (app (mock/request :get "/"))]
      ;(is (= (:status response) 200))
      ;(is (= (:body response) "Ok"))))

  ;(testing "not-found route"
    ;(let [response (app (mock/request :get "/invalid"))]
      ;(is (= (:status response) 404)))))



(deftest should-raise-exception-if-config-misses-mandatory-key
  (testing "mandatory Validation"
(is (thrown? Exception (dsl/convert (array-map :a 10 :c 33 :c 44)))
   )
  
  )
  )



;(deftest should-fail-if-config-has-no-url-schema

 ;(is (= "a" (convert "{}")))
;)
