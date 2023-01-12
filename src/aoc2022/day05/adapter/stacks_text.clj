(ns aoc2022.day05.adapter.stacks-text
  (:require [clojure.string :as string]))

(defn- transpose [m]
  "[[1 2] [3 4]] transforms into [[1 3] [2 4]] (https://stackoverflow.com/questions/10347315/matrix-transposition-in-clojure)"
  (apply mapv vector m))

(defn- lines->arrays-of-pieces [lines]
  "Transforms 
      [D]    
  [N] [C]    
  [Z] [M] [P]
   1   2   3 
  into
  ((\\Z \\N) (\\M \\C \\D) (\\P))
  "
  (->> lines
       (drop-last)                                          ; removes the line " 1   2   3 "
       (map #(re-seq #"([A-Z]|   )" %))                     ; brings regex matches (([       ] [D D] [       ]) ([N N] [C C] [       ]) ([Z Z] [M M] [P P]))
       (map (fn [piece]
              (->> piece
                   (map first)                              ; remove regex groups ((    D    ) (N C    ) (Z M P))
                   (map #(first (char-array %))))           ; converts into array of chars (\space \D \space) (\N \C \space) (\Z \M \P)
              ))
       (transpose)                                          ; transpose to ((\space \N \Z) (\D \C \M) (\space \space \P))
       (map reverse)                                        ; reverse the order ((\Z \N \space) (\M \C \D) (\P \space \space))
       (map (fn [line] (filter #(not= % \space) line)))     ; remove \space from every line
       ))                                      

(defn- pieces->stacks [pieces]
  (let [number-of-stacks (count pieces)]
    (loop [i 0
         stack {}]
    (if (>= i number-of-stacks)
      stack
      (let [new-stack (assoc stack (+ 1 i) (nth pieces i))]
        (recur (+ 1 i) new-stack))))))
(defn- stack-parsed [input]
  (let [lines (string/split-lines input)
        pieces (lines->arrays-of-pieces lines)
        stacks (pieces->stacks pieces)]
    stacks))

(defn- commands-parsed [input]
  (let [lines (string/split-lines input)]
    (map (fn [line]
           (let [numbers (re-seq #"\d" line)]
             {:movements  (read-string (first numbers))
              :from       (read-string (second numbers))
              :to         (read-string (nth numbers 2))})
           ) lines)))
(defn text->stacks [input]
  (let [parts (string/split input #"\n\n")]
    {:stacks    (stack-parsed (first parts))
     :commands  (commands-parsed (last parts))})
  )
