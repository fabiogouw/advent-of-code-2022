(ns aoc2022.day03.logic.rucksack)

(defn priority
  [item]
  (let [ascii-value (int item)]
    (if (> ascii-value 96)
      (- ascii-value 96)                                    ; 96 is 'a' in ASCII
      (+ 26 (- ascii-value 64)))))                          ; 64 is 'A' in ASCII, 26 starts the representation in this problem

(defn repeated
  ([first-sequence second-sequence]
   (reduce (fn [repeated-items item]
             (if (some #(= item %) second-sequence)
               (conj repeated-items item)
               repeated-items)) [] first-sequence))
  ([{:keys [first-compartment second-compartment]}]
   (repeated first-compartment second-compartment)))

(defn sum-repeated-priority
  [rucksacks]
  (let [repeated-items (map (comp priority first repeated) rucksacks)]
    (reduce + repeated-items)))

(defn badges-summed
  [rucksacks]
  (let [rucksacks (partition 3 rucksacks)]
    (loop [rucksacks          rucksacks
           total-accumulated  0]
      (let [this-group-rucksacks            (first rucksacks)
            others-rucksacks                (rest rucksacks)
            compartment-combined-rucksacks  (map #(concat (:first-compartment %) (:second-compartment %)) this-group-rucksacks)
            repeated-items                  (distinct (reduce repeated compartment-combined-rucksacks))
            badge-value                     (priority (first repeated-items))
            total-accumulated               (+ total-accumulated badge-value)]
        (if (empty? others-rucksacks)
          total-accumulated
          (recur others-rucksacks total-accumulated))))))