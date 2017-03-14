(ns retryer.core)

(defn retryer
  "retry a given function"
  [f times]
  (->> (take times (iterate inc 0))
       (map f)
       (filter #(not= nil? %))
       (take 1)
       first))
