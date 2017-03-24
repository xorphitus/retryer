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
  (testing "with execption, finally success"
    (let [cnt (atom 0)
          expectation 3]
      (is (= (retryer
              (fn [n]
                (do (swap! cnt inc)
                    (if (< n 2)
                      (throw (RuntimeException. "NG"))
                      @cnt)))
              3
              RuntimeException)
             expectation))))
  (testing "with execption, finally fail"
    (let [expectation nil]
      (is (= (retryer
              (fn [n]
                (throw (RuntimeException. "NG")))
              3
              RuntimeException)
             expectation)))))
