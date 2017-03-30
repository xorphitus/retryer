(ns retryer.core)

(def sleeps
  (let [base-interval 0.5
        multiplier 1.5
        sleep-sec (iterate #(* % multiplier) base-interval)]
    (map #(do (Thread/sleep %)
              nil)
         sleep-sec)))

(defmacro retryer
  "retry a given function"
  [f times execption]
  `(->> (take ~times (iterate inc 0))
        (map
         (fn [n#] (try
                    (~f n#)
                    (catch ~execption e#
                      nil))))
        (#(interleave % sleeps))
        (filter #(not (nil? %)))
        (take 1)
        first))
