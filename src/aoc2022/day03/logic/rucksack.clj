(ns aoc2022.day03.logic.rucksack)

(defn priority
  [item]
  (let [ascii-value (int item)]
    (if (> ascii-value 96)
      (- ascii-value 96)                                    ; 96 is 'a' in ASCII
      (+ 26 (- ascii-value 64)))))                          ; 64 is 'A' in ASCII, 26 starts the representation in this problem

(defn repeated
  [{:keys [first-compartment second-compartment]}]
  (reduce (fn [repeated-item item]
            (if (some #(= item %) second-compartment)
              item
              repeated-item)
            ) nil first-compartment))

(defn sum-repeated-priority
  [rucksacks]
  (let [repeated-items (map (comp priority repeated) rucksacks)]
    (reduce + repeated-items)))