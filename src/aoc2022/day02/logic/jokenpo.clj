(ns aoc2022.day02.logic.jokenpo)

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