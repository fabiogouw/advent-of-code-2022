(ns aoc2022.day05.adapter.stacks-text
  (:require [clojure.string :as string]))

(defn- transpose [matrix]
  "[[1 2] [3 4]] transforms into [[1 3] [2 4]] (https://stackoverflow.com/questions/10347315/matrix-transposition-in-clojure)"
  (apply mapv vector matrix))

(defn- normalized-stack-values [raw-stack]
  (map #(first (char-array %)) raw-stack))

(defn- lines->arrays-of-raw-stacks [lines]
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
       (map #(re-seq #"[A-Z]|    |   " %))                  ; brings regex matches (("    " "D" "    ") ("N" "C" "    ") ("Z" "M" "P")) - or letter, or 4 empty spaces, or 3 empty spaces (in the end of the line)
       (map normalized-stack-values)                        ; converts into array of chars (\space \D \space) (\N \C \space) (\Z \M \P)
       (transpose)                                          ; transposes to ((\space \N \Z) (\D \C \M) (\space \space \P)) - rows become columns, columns become rows
       (map reverse)                                        ; reverses the order ((\Z \N \space) (\M \C \D) (\P \space \space))
       (map (fn [line] (filter #(not= % \space) line)))     ; removes \space from every stack
       ))                                      

(defn- raw-stacks->stacks [raw-stacks]
  (let [number-of-stacks (count raw-stacks)]
    (loop [i      0
           stack  {}]
    (if (>= i number-of-stacks)
      stack
      (let [new-stack (assoc stack (+ 1 i) (vec (nth raw-stacks i)))]
        (recur (+ 1 i) new-stack))))))

(defn- stack-parsed [input]
  (let [lines (string/split-lines input)
        raw-stacks (lines->arrays-of-raw-stacks lines)
        stacks (raw-stacks->stacks raw-stacks)]
    stacks))

(defn- commands-parsed [input]
  (let [lines (string/split-lines input)]
    (map (fn [line]
           (let [numbers (re-seq #"\d+" line)]
             {:movements  (read-string (first numbers))
              :from       (read-string (second numbers))
              :to         (read-string (nth numbers 2))})
           ) lines)))
(defn text->stacks [input]
  (let [parts (string/split input #"\n\n")]
    {:stacks    (stack-parsed (first parts))
     :commands  (commands-parsed (last parts))})
  )
