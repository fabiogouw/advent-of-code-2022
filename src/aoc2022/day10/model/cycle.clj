(ns aoc2022.day10.model.cycle
  (:require [schema.core :as s]))

(s/defschema Instruction
  {:cycle         s/Int
   :value-to-add  s/Int})
(s/defschema Cycle
  {:cycle             s/Int
   :x-value-at-start  s/Int
   :x-value-at-end    s/Int
   :signal-strength   s/Int})