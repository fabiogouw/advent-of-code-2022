(ns aoc2022.day09.logic.rope
  (:require [clojure.math :refer [sqrt]]
            [schema.core :as s]
            [aoc2022.day09.model.rope :as model]))

(def close-distance (sqrt 2))
(defn- distance
  ([head tail axis]
   (let [x-distance (abs (- (:x head) (:x tail)))
         y-distance (abs (- (:y head) (:y tail)))]
     (cond 
       (= axis :x)  x-distance
       (= axis :y)  y-distance
       :else        (sqrt (+ (* x-distance x-distance) (* y-distance y-distance))))))
  ([head tail]
   (distance head tail nil)))
(defn farther? [head tail]
  (> (distance head tail) close-distance))

(defn move [{:keys [head tail]} direction]
  (let [new-head (cond
                   (= direction "U") (update-in head [:y] inc)
                   (= direction "D") (update-in head [:y] dec)
                   (= direction "R") (update-in head [:x] inc)
                   (= direction "L") (update-in head [:x] dec))
        new-tail (if (farther? new-head tail)
                   head                                     ; old head is the new tail position
                   tail)]
    {:head new-head
     :tail new-tail}))

(s/defn tail-move-count [rope :- model/Rope
                         moves :- [s/Str]]
  (let [tail-positions (loop [rope           rope
                              moves          moves
                              tail-positions (set [(:tail rope)])]
                         (if (empty? moves)
                           tail-positions
                           (let [new-rope (move rope (first moves))]
                             (recur new-rope (rest moves) (conj tail-positions (:tail new-rope))))))]
    (count tail-positions)))