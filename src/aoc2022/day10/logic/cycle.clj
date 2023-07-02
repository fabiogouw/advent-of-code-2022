(ns aoc2022.day10.logic.cycle
  (:require [aoc2022.day10.model.cycle :as model]
            [schema.core :as s]))

(s/defn signal-strength [start-value    :- s/Int
                         instructions   :- [model/Instruction]] :- [model/Cycle]
  (loop [instructions instructions
         x-value      start-value
         cycles       []]
    (if (empty? instructions)
      cycles
      (let [current-instruction (first instructions)
            cycle               (:cycle current-instruction)
            new-x-value         (+ x-value (:value-to-add current-instruction))
            signal-strength     (* cycle x-value)
            cycles              (conj cycles {:cycle cycle :x-value-at-start x-value :x-value-at-end new-x-value :signal-strength signal-strength})]
        (recur (rest instructions) new-x-value cycles)))))

(s/defn signal-strength-summed [cycles :- [model/Cycle]] :- s/Int
  (->> cycles
       (filter #(= (mod (+ (:cycle %) 20) 40) 0))
       (map :signal-strength)
       (reduce +)))