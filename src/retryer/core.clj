(ns retryer.core)

(defmacro retryer
  "retry a given function"
  [f times execption]
  `(->> (take ~times (iterate inc 0))
       (map
        (fn [n#] (try
                  (~f n#)
                  (catch ~execption e#
                    nil))))
       (filter #(not= nil? %))
       (take 1)
       first))
