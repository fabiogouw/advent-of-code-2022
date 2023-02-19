(ns aoc2022.day07.logic.filesystem)

(defn- file? [item]
  (contains? item :size))

(defn- directory? [item]
  (contains? item :items))

(defn- directory-matched-in-list? [other-list item]
  (some #(= (:name item) (:name %)) other-list))
(defn- total-size-of-directories-list [directory]
  (let [items                               (:items directory)
        sub-directories                     (filter directory? items)
        directory-matched?                  (partial directory-matched-in-list? sub-directories)
        all-sub-directories-size-list       (reduce (fn [acc item]
                                                      (concat acc (total-size-of-directories-list item))) [] sub-directories)
        directly-sub-directories-size-list  (filter directory-matched? all-sub-directories-size-list)
        sub-directories-size                (reduce (fn [acc item]
                                                      (+ acc (:dir-size item))) 0 directly-sub-directories-size-list)
        files                               (filter file? items)
        files-size                          (reduce (fn [acc item]
                                                      (+ acc (:size item))) 0 files)]
    (conj all-sub-directories-size-list {:name (:name directory) :dir-size (+ files-size sub-directories-size)})))

(defn total-size-at-most [filesystem at-most-directory-value]
  (let [size-list (total-size-of-directories-list filesystem)
        filtered (filter #(< (:dir-size %) at-most-directory-value) size-list)]
    (reduce (fn [acc item]
              (+ acc (:dir-size item))) 0 filtered)))

(defn total-size-filesystem [filesystem]
  (->> (total-size-of-directories-list filesystem)
       (filter #(= (:name %) "/"))
       first
       :dir-size))