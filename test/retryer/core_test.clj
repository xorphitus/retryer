(ns retryer.core-test
  (:require [clojure.test :refer :all]
            [retryer.core :refer :all]))

(deftest retryer-test
  (let [cnt (atom 0)
        expectation 1]
    (is (= (retryer
            (fn [n] (swap! cnt inc))
            1)
           expectation))))
