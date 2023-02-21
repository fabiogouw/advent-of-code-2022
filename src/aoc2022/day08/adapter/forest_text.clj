(ns aoc2022.day08.adapter.forest-text
  (:require [clojure.string :as str]))

(defn text->forest [input]
  (let [lines (str/split-lines input)]
    (loop [lines lines
           matrix []]
      (if (empty? lines)
        matrix
        (let [matrix-line (-> lines
                              first
                              (str/split #""))
              matrix-line (mapv (comp int bigint) matrix-line)
              matrix      (conj matrix matrix-line)]
         (recur (rest lines) matrix))))))