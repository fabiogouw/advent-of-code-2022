(ns aoc2022.day02.logic.jokenpo
  (:require [clojure.set :as set]))

(defn- move-type-point
  [move]
  (case (:you move)
    :rock     1
    :paper    2
    :scissors 3))

(defn- turn-result-point
  [move]
  (let [your-move     (:you move)
        opponent-move (:opponent move)]
    (cond
      (= your-move opponent-move) 3
      (and (= :rock your-move) (= :scissors opponent-move)) 6
      (and (= :paper your-move) (= :rock opponent-move)) 6
      (and (= :scissors your-move) (= :paper opponent-move)) 6
      :else 0)))

(defn- points
  [move]
  (+ (move-type-point move) (turn-result-point move)))

(defn moves-summed
  [moves]
  (->> moves
       (map points)
       (reduce +)))

(def wins {:rock     :paper
           :paper    :scissors
           :scissors :rock})

(def losses (set/map-invert wins))
(defn- desired-result
  [opponent-move list]
  (val (find list opponent-move)))
(defn- move-for-desired-result
  [move]
  (let [opponent-move  (:opponent move)
        result-as-move (case (:you move)
                         :draw   opponent-move
                         :win    (desired-result opponent-move wins)
                         :lose   (desired-result opponent-move losses))]
    {:opponent opponent-move :you result-as-move}))
(defn result-summed
  [moves]
  (->> moves
       (map move-for-desired-result)
       (map points)
       (reduce +)))