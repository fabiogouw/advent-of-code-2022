(ns aoc2022.day05.logic.stacks)

(defn- top-stacks [stacks]
  (reduce (fn [result stack]
            (str result (last stack))) "" (->> stacks
                                               (into (sorted-map-by <))
                                               (vals))))

(defn- final-stack [{:keys [stacks commands]} fn-command-applied]
  (loop [commands  commands
         stacks   stacks]
    (let [command (first commands)]
      (if (nil? command)
        (top-stacks stacks)
        (let [stacks (fn-command-applied stacks command)]
          (recur (rest commands) stacks))))))

(defn- pop-and-push [stacks from to]
  (let [value-to-move (peek (get stacks from))
        stacks (update-in stacks [from] pop)
        stacks (update-in stacks [to] conj value-to-move)]
    stacks))
(defn- pop-and-push-command-applied [stacks {:keys [movements from to]}]
  (loop [i      1
         stacks stacks]
    (let [stacks (pop-and-push stacks from to)]
      (if (>= i movements)
        stacks
        (recur (+ 1 i) stacks)))))

(defn final-stack-9000 [input]
  (final-stack input pop-and-push-command-applied))

(defn- pop-many-and-push-command-applied
  ([stacks from to total]
   (let [from-stack                       (get stacks from)
         split-index                      (- (count from-stack) total)
         [values-to-leave values-to-move] (split-at split-index from-stack)
         stacks                           (assoc stacks from values-to-leave)
         stacks                           (update-in stacks [to] concat values-to-move)]
     stacks))
  ([stacks {:keys [movements from to]}]
   (pop-many-and-push-command-applied stacks from to movements)))

(defn final-stack-9001 [input]
  (final-stack input pop-many-and-push-command-applied))