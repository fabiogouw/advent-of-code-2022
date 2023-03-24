(ns aoc2022.day09.adapter.rope-text
  (:require [clojure.string :as s]))

(defn text->rope [input]
  (let [lines (s/split-lines input)]
    (reduce (fn [acc line]
              (let [[direction times] (re-seq #"[A-Z]|\d+" line) ; line contains a direction and the number of times it should move: L 17
                    times             (int (bigint times))]
                (into acc (take times (repeat direction))))
              ) [] lines)))