(ns aoc2022.day01.logic.calorie)

(defn most-calories
  [elves]
  (let [calories-summed (map (comp (partial reduce +) :food) elves)]
    (apply max calories-summed)))
