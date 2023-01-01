(ns aoc2022.day03.adapters.rucksack-text)

(defn line->rucksack
  [line]
  (let [compartment-size (/ (count line) 2)]
    {:first-compartment (seq (char-array (subs line 0 compartment-size)))
     :second-compartment (seq (char-array (subs line compartment-size)))}
    ))

(defn input->rucksacks
  [input]
  (let [lines (clojure.string/split-lines input)]
    (map line->rucksack lines)))