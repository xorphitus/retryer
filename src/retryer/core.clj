(ns retryer.core)

(defn retryer
  "retry a given function"
  [f times execption]
  (->> (take times (iterate inc 0))
       (map
        (fn [n] (f n)))
       (filter #(not= nil? %))
       (take 1)
       first))
