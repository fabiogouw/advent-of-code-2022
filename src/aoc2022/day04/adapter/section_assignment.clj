(ns aoc2022.day04.adapter.section-assignment
  (:require [clojure.string :as string]))

(defn- part->start-end [part]
  (let [boundaries (string/split part #"-")]
    {:start (read-string (first boundaries))
     :end   (read-string (second boundaries))}))
(defn- line->section-assignments [line]
  (let [parts (string/split line #",")]
    {:first-elf   (part->start-end (first parts))
     :second-elf  (part->start-end (second parts))}))

(defn text->section-assignments [input]
  (let [lines (clojure.string/split-lines input)]
    (map line->section-assignments lines)))
