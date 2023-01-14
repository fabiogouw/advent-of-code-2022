(ns aoc2022.day05.logic.stacks)

(defn- top-stacks [stacks]
  (reduce (fn [result stack]
            (str result (last stack))) "" (->> stacks
                                               (into (sorted-map-by <))
                                               (vals))))

(defn- pop-and-push [stacks from to]
  (let [value-to-move (peek (get stacks from))
        stacks (update-in stacks [from] pop)
        stacks (update-in stacks [to] conj value-to-move)]
    stacks))
(defn- command-applied [stacks {:keys [movements from to]}]
  (loop [i      1
         stacks stacks]
    (let [stacks (pop-and-push stacks from to)]
      (if (>= i movements)
        stacks
        (recur (+ 1 i) stacks)))))
(defn final-stack [{:keys [stacks commands]}]
  (loop [commands  commands
         stacks   stacks]
    (let [command (first commands)]
      (if (nil? command)
        (top-stacks stacks)
        (let [stacks (command-applied stacks command)]
          (recur (rest commands) stacks))))))
