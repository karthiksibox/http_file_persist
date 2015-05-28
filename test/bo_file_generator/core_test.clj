(ns bo-file-generator.handler-test
  (:require [clojure.test :refer :all]
            [bo-file-generator.core :as dsl]))


(deftest should-raise-exception-if-config-misses-mandatory-key
  (testing "mandatory Validation"
(let [exception (dsl/convert (array-map :a 10 :c 33 :c 44))])
   )
  
  )






