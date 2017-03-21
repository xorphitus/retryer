(ns retryer.core-test
  (:require [clojure.test :refer :all]
            [retryer.core :refer :all]))

(deftest retryer-test
  (testing "without execption"
    (let [cnt (atom 0)
          expectation 1]
      (is (= (retryer
              (fn [n] (swap! cnt inc))
              3
              RuntimeException)
             expectation))))
  (testing "with execption"
    (let [cnt (atom 0)
          expectation 3]
      (is (= (retryer
              (fn [n]
                (if (> (swap! cnt inc) 3)
                  (throw (RuntimeException. "NG"))
                  @cnt))
              3
              RuntimeException)
             expectation)))))
