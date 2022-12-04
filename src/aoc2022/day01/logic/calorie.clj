(ns aoc2022.day01.logic.calorie)

(def food-summed (comp (partial reduce +) :food))
(defn most-calories
  [bags]
  (let [calories-summed (map food-summed bags)]
    (apply max calories-summed)))

(defn- bag-with-summed
  [bag]
  (assoc bag :sum (food-summed bag)))
(defn top-3-most-calories
  [bags]
  (->> bags
       (map bag-with-summed)
       (sort-by :sum #(compare %2 %1))
       (take 3)
       (map :sum)
       (reduce +)))