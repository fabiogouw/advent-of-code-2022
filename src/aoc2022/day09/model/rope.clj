(ns aoc2022.day09.model.rope
  (:require [schema.core :as s]))

(s/defschema Knot
  {:x s/Int
   :y s/Int})