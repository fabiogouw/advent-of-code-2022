(ns aoc2022.day08.logic.forest)

(defn- get-tree-height [forest row col]
  (get (get forest row) col))

(defn- number-of-columns
  "All rows in the forest must have the same size"
  [forest]
  (count (get forest 0)))

(defn- number-of-rows [forest]
  (dec (count forest)))

(defn- horizontal-surrounding-heights
  "Returns the trees on the left and the right side of one tree as 2 sequences"
  [forest row col]
  (map vec (split-at col (get forest row))))

(defn- vertical-range [forest col start-row end-row]
  (loop [row start-row
         trees-heights []]
    (if (> row end-row)
      trees-heights
      (let [tree-height   (get-tree-height forest row col)
            trees-heights (conj trees-heights tree-height)]
        (recur (inc row) trees-heights)))))

(defn- horizontal-range [forest row start-col end-col]
  (let [number-of-columns (number-of-columns forest)] 
    (if (or (<= end-col 0) (>= start-col number-of-columns))
      []
      (subvec (get forest row) start-col end-col)))
  )

(defn- surrounding-heights [forest row col]
  (let [top           (vertical-range forest col 0 (dec row))
        down          (vertical-range forest col (inc row) (number-of-rows forest))
        left          (horizontal-range forest row 0 col)
        right         (horizontal-range forest row (inc col) (number-of-columns forest))]
    [top down left right]))

(defn- visible? [tree-height other-trees-height]
  (not (some #(>= % tree-height) other-trees-height)))

(defn- visible-from-all-sides? [forest row col]
  (let [tree-height         (get-tree-height forest row col)
        surrounding-heights (surrounding-heights forest row col)]
    (boolean (some (partial visible? tree-height) surrounding-heights))))

(defn- all-visible-tree-in-row [forest row total-visible]
  (let [number-of-columns (number-of-columns forest)]
    (loop [col 0
           total-visible total-visible]
      (if (>= col number-of-columns)
        total-visible
        (let [total-visible (if (visible-from-all-sides? forest row col)
                              (inc total-visible)
                              total-visible)]
          (recur (inc col) total-visible))))))

(defn all-visible-trees [forest]
  (let [number-of-rows (number-of-rows forest)]
    (loop [row 0
           total-visible 0]
      (if (> row number-of-rows)
        total-visible
        (recur (inc row) (all-visible-tree-in-row forest row total-visible))))))
