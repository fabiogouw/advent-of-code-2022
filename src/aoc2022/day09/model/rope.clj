(ns aoc2022.day09.model.rope
  (:require [schema.core :as s]))

(s/defschema Position
  {:x s/Int
   :y s/Int})

(s/defschema Rope
  {:head Position
   :tail Position})