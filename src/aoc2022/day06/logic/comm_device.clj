(ns aoc2022.day06.logic.comm-device)

(def chuck-size 4)
(defn- are-different [datastream start end]
  (when (> (count datastream) end)
    (let [chunk           (subs datastream start end)
          distinct-chunk  (distinct (char-array chunk))]
      (= (count chunk) (count distinct-chunk)))))
(defn start-of-packet [datastream]
  (let [datastream-length (count datastream)]
    (if (>= datastream-length chuck-size)
      (loop [i 0]
        (let [position-checked (+ i chuck-size)]
          (cond
            (are-different datastream i position-checked) position-checked
            (> position-checked datastream-length) -1
            :else (recur (+ i 1)))))
      -1))
  )
