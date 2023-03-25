(ns aoc2022.day09.logic.rope
  (:require [clojure.math :refer [sqrt]]
            [schema.core :as s]
            [aoc2022.day09.model.rope :as model]))

(def distance-considered-far 2)
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
  (>= (distance head tail) distance-considered-far))

(defn- same-row-or-column? [knot1 knot2]
  (or (= (:x knot1) (:x knot2))
      (= (:y knot1) (:y knot2))))

(defn- direction-to-follow-diagonally [tail new-head direction-taken]
  (cond
    (some #{direction-taken} '("U" "D"))  (if (> (:x tail) (:x new-head))
                                            "L"
                                            "R")
    (some #{direction-taken} '("L" "R"))  (if (> (:y tail) (:y new-head))
                                            "D"
                                            "U")))
(defn- move-to [knot direction]
  (cond
    (= direction "U") (update-in knot [:y] inc)
    (= direction "D") (update-in knot [:y] dec)
    (= direction "R") (update-in knot [:x] inc)
    (= direction "L") (update-in knot [:x] dec)
    :else             knot))

#_(defn move [{:keys [head tail]} direction]
  (let [new-head  (move-to head direction)
        new-tail  (if (farther? new-head tail)
                    (let [direction-to-follow (when (not (same-row-or-column? new-head tail))
                                                (direction-to-follow-diagonally tail new-head direction))]
                      (-> tail
                          (move-to direction)
                          (move-to direction-to-follow)))
                    tail)]
    {:head new-head
     :tail new-tail}))

(defn- move-head [head direction]
  (move-to head direction))
(defn- move-knot [new-first-knot follower-knot direction]
  (let [new-follower-knot  (if (farther? new-first-knot follower-knot)
                             (let [direction-to-follow (when (not (same-row-or-column? new-first-knot follower-knot))
                                                         (direction-to-follow-diagonally follower-knot new-first-knot direction))]
                               (-> follower-knot
                                   (move-to direction)
                                   (move-to direction-to-follow)))
                             follower-knot)]
    new-follower-knot))

(defn- move-knots [new-head follower-knots direction]
  (let [new-follower-knots [(move-knot new-head (first follower-knots) direction)]]
    (loop [follower-knots (rest follower-knots)
           new-follower-knots new-follower-knots]
      (if (empty? follower-knots)
        new-follower-knots
        (let [updated-previous-knot       (last new-follower-knots)
              updated-first-follower-knot (move-knot updated-previous-knot (first follower-knots) direction)
              new-follower-knots          (conj new-follower-knots updated-first-follower-knot)]
          (recur (rest follower-knots) new-follower-knots))))))

(defn move [rope direction]
  (let [head               (first rope)
        new-head           (move-head head direction)
        new-follower-knots (move-knots new-head (rest rope) direction)
        new-rope           (into [new-head] new-follower-knots)]
    new-rope))

(s/defn tail-move-count [rope  :- [model/Position]
                         moves :- [s/Str]]
  (let [tail-positions (loop [rope           rope
                              moves          moves
                              tail-positions (set [(last rope)])]
                         (if (empty? moves)
                           tail-positions
                           (let [direction          (first moves)
                                 new-rope           (move rope direction)]
                             (recur new-rope (rest moves) (conj tail-positions (last new-rope))))))]
    (count tail-positions)))