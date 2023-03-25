(ns aoc2022.day09.logic.rope
  (:require [clojure.math :refer [sqrt]]
            [schema.core :as s]
            [aoc2022.day09.model.rope :as model]))

(def distance-considered-far 2)                             ; to be exactly, could be more than (sqrt 2)
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

(defn- direction-to-follow [updated-previous-knot follower-knot]
  (cond
    (>= (distance updated-previous-knot follower-knot :x) distance-considered-far) (if (> (:x updated-previous-knot) (:x follower-knot))
                                                                                     "R"
                                                                                     "L")
    (>= (distance updated-previous-knot follower-knot :y) distance-considered-far) (if (> (:y updated-previous-knot) (:y follower-knot))
                                                                                     "U"
                                                                                     "D")))

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

(defn- move-head [head direction]
  (move-to head direction))
(s/defn follow-knot [follower-knot          :- model/Knot
                     updated-previous-knot  :- model/Knot]  :- model/Knot
  (if (farther? updated-previous-knot follower-knot)
    (let [direction-to-follow              (direction-to-follow updated-previous-knot follower-knot)
          direction-to-follow-diagonally   (when (not (same-row-or-column? updated-previous-knot follower-knot))
                                             (direction-to-follow-diagonally follower-knot updated-previous-knot direction-to-follow))]
      (-> follower-knot
          (move-to direction-to-follow)
          (move-to direction-to-follow-diagonally)))
    follower-knot))

(defn- move-followers [new-head followers]
  (let [first-follower-to-update  (first followers)
        updated-followers         [(follow-knot first-follower-to-update new-head)]]
    (loop [followers         (rest followers)
           updated-followers updated-followers]
      (if (empty? followers)
        updated-followers
        (let [previous-updated-knot           (last updated-followers)
              next-follower-to-update         (first followers)
              updated-next-follower           (follow-knot next-follower-to-update previous-updated-knot)
              updated-previous-knot           (conj updated-followers updated-next-follower)]
          (recur (rest followers) updated-previous-knot))))))

(defn move-rope [rope direction]
  (let [head               (first rope)
        new-head           (move-head head direction)
        new-follower-knots (move-followers new-head (rest rope))
        new-rope           (into [new-head] new-follower-knots)]
    new-rope))

(s/defn tail-move-count [rope  :- [model/Knot]
                         moves :- [s/Str]]  :- s/Int
  (let [tail-positions (loop [rope           rope
                              moves          moves
                              tail-positions (set [(last rope)])]
                         (if (empty? moves)
                           tail-positions
                           (let [direction          (first moves)
                                 new-rope           (move-rope rope direction)
                                 tail               (last new-rope)]
                             (recur new-rope (rest moves) (conj tail-positions tail)))))]
    (count tail-positions)))