(ns aoc2022.day04.logic.section-assignment)

(defn fully-overlaps? [{:keys [first-elf second-elf]}]
  ; full overlapping example
  ; |-2-----8-|
  ; |---4-6---|
  (or (and (<= (:start first-elf) (:start second-elf))
           (>= (:end first-elf) (:end second-elf)))
      (and (<= (:start second-elf) (:start first-elf))
           (>= (:end second-elf) (:end first-elf)))))

(defn fully-overlaps-count [pairs]
  (let [overlapped? (map (comp #(if % 1 0) fully-overlaps?) pairs)]
    (reduce + 0 overlapped?)))

(defn no-overlaps? [{:keys [first-elf second-elf]}]
  ; no overlapping example
  ; |-2-4-----|
  ; |-----6--9|
  (or (> (:start second-elf) (:end first-elf))
      (> (:start first-elf) (:end second-elf)))
  )
(defn overlaps? [pair]
  ; overlapping example
  ; |-2-----8-| |-2-----8-| |-2--5----|
  ; |---4-6---| |-2-4-----| |---4-6---|
  (not (no-overlaps? pair)))

(defn overlaps-count [pairs]
  (let [overlapped? (map (comp #(if % 1 0) overlaps?) pairs)]
    (reduce + 0 overlapped?)))