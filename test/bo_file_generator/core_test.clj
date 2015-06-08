(ns bo-file-generator.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [bo-file-generator.core :refer :all]
            [bo-file-generator.core :as dsl]
             [bo-file-generator.handler :refer :all]))


(deftest should-raise-exception-if-config-misses-mandatory-key
  (testing "mandatory Validation"
(is (thrown-with-msg? Exception #"Doesn't have needed key.." (dsl/convert (array-map :a 10 :c 33 :c 44)))
   )
  )

  (testing "should validate all needed fields"
(is (= nil (dsl/convert (array-map :url_schema {} :destination_file "" :field_lengths {} :data {})))
   )
  
  )
  
  )


(deftest should-format-fields-based-on-type
  (testing "Should prepend 0's for numbers"
(is (= "012" (get_persistable_data {:field_lengths {:store_id 3}, :data {
      :store_id 12}} :store_id)
   )
  
  )
  )

  (testing "Should append white space for non numbers"
(is (= "A    " (get_persistable_data {:field_lengths {:store_name 5}, :data {
      :store_name "A"}} :store_name)
   )
  
  )
  )
  
  )

