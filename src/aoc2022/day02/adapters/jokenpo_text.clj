(ns aoc2022.day02.adapters.jokenpo-text
  (:require [clojure.string :as str]))

(defn- opponent-move
  [line]
  (let [move (get line 0)]
    (case move
      \A :rock
      \B :paper
      \C :scissors)))

(defn- your-move
  [line]
  (let [move (get line 2)]
    (case move
      \X :rock
      \Y :paper
      \Z :scissors)))

(defn- move-list
  [line]
  {:opponent  (opponent-move line)
   :you       (your-move line)})
(defn text->map
  [input-text]
  (let [lines (str/split-lines input-text)]
    (->> lines
         (map move-list))))