(ns aoc2022.day01.adapters.calories-text
  (:require [clojure.string :as str]))

(defn- with-elf-id-added
  [bags]
  (loop [id         0
         named-bags []]
    (if (>= id (count bags))
      named-bags
      (let [next-bag-id (inc id)
            next-bag    (merge {:elf-id next-bag-id} (nth bags id))]
        (recur next-bag-id (conj named-bags next-bag))))))
(defn text->map
  [input-text]
  (let [lines (str/split-lines input-text)]
    (->> lines
         (partition-by str/blank?)
         (filter (partial not= '("")))
         (map (comp (fn [a] {:food a}) (partial map read-string)))
         (with-elf-id-added))))