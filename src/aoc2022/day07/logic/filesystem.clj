(ns aoc2022.day07.logic.filesystem)

(defn total-size [filesystem]
  (loop [items      (:items filesystem)
         current-total-size 0]
    (let [files (filter #(contains? % :size) items)
          files-size (reduce (fn [acc, item]
                               (+ acc (:size item))) 0 files)
          current-total-size (+ current-total-size files-size)
          directories (filter #(contains? % :items) items)
          directories-items (reduce (fn [acc item]
                                      (concat acc (:items item))) [] directories)]
      (if (empty? directories-items)
        current-total-size
        (recur directories-items current-total-size)))))

(defn- file? [item]
  (contains? item :size))

(defn- directory? [item]
  (contains? item :items))
(defn total-size-of-all-directories [directory]
  (let [items           (:items directory)
        files           (filter #(file? %) items)
        sub-directories (filter #(directory? %) items)
        files-size (reduce (fn [acc, item]
                             (+ acc (:size item))) 0 files)
        sub-directories-size-list (reduce (fn [acc item]
                                            (concat acc (total-size-of-all-directories item))) [] sub-directories)
        sub-directories-size (reduce (fn [acc item]
                                       (+ acc (:dir-size item))) 0 sub-directories-size-list)
        directory-size (+ files-size sub-directories-size)]
    (conj sub-directories-size-list {:name (:name directory) :dir-size directory-size})))

(defn total-size-at-most [filesystem at-most-directory-value]
  (let [size-list (total-size-of-all-directories filesystem)
        filtered (filter #(< (:dir-size %) at-most-directory-value) size-list)]
    (reduce (fn [acc item]
              (+ acc (:dir-size item))) 0 filtered))
  )