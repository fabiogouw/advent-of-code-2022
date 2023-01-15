(ns aoc2022.day06.logic.comm-device)

(def packet-chuck-size 4)
(def message-chuck-size 14)
(defn- are-different [datastream start end]
  (when (> (count datastream) end)
    (let [chunk           (subs datastream start end)
          distinct-chunk  (distinct (char-array chunk))]
      (= (count chunk) (count distinct-chunk)))))
(defn- start-of-something [datastream chuck-size]
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

(defn start-of-packet [datastream]
  (start-of-something datastream packet-chuck-size))

(defn start-of-message [datastream]
  (start-of-something datastream message-chuck-size))
