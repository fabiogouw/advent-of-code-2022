(ns aoc2022.day07.logic.filesystem
  (:require [clojure.string :as str]))

(defn- file? [item]
  (contains? item :size))

(defn- directory? [item]
  (contains? item :items))

(defn- directory-matched-in-list?
  "Returns true if the directory from the size list exists in the list of hierarchical directory structure.
  For example, the directory 
    {:full-name ///a/e, :dir-size 584}
  exists within the sequence
   ({:name e, :items [{:name i, :size 584}]})
  so the function returns true (this validation removes the base path ///a from the directory)."
  [hierarchical-directory-structure full-name directory]
  (let [normalized-directory-name (str/replace (:full-name directory) (str full-name "/") "")]
    (some #(= normalized-directory-name (:name %)) hierarchical-directory-structure)))

(defn- total-size-of-directories-list 
  "Returns the full list with all directories and their size.
    ({:full-name ///a, :dir-size 94853}
     {:full-name ///a/e, :dir-size 584}
     {:full-name ///d, :dir-size 24933642}
     {:full-name //, :dir-size 48381165}) "
  [directory base-directory-name]
  (let [full-directory-name                 (str base-directory-name "/" (:name directory))
        items                               (:items directory)
        sub-directories                     (filter directory? items)
        all-sub-directories-size-list       (reduce (fn [acc item]
                                                      (concat acc (total-size-of-directories-list item full-directory-name))) [] sub-directories)
        directory-matched?                  (partial directory-matched-in-list? sub-directories full-directory-name)
        directly-sub-directories-size-list  (filter directory-matched? all-sub-directories-size-list)
        sub-directories-size                (reduce (fn [acc item]
                                                      (+ acc (:dir-size item))) 0 directly-sub-directories-size-list)
        files                               (filter file? items)
        files-size                          (reduce (fn [acc item]
                                                      (+ acc (:size item))) 0 files)]
    (conj all-sub-directories-size-list {:full-name full-directory-name :dir-size (+ files-size sub-directories-size)})))

(defn total-size-at-most [filesystem at-most-directory-value]
  (let [all-directories-list  (total-size-of-directories-list filesystem "")
        filtered              (filter #(< (:dir-size %) at-most-directory-value) all-directories-list)]
    (reduce (fn [acc item]
              (+ acc (:dir-size item))) 0 filtered)))

(defn total-size-filesystem [filesystem]
  (->> (total-size-of-directories-list filesystem "")
       (filter #(= (:full-name %) "//"))
       first
       :dir-size
       int))

(defn smallest-directory-size-enough-for-update [filesystem total-disk-space update-space-needed]
  (let [current-used-size       (total-size-filesystem filesystem)
        current-available-size  (- total-disk-space current-used-size)
        space-needed            (- update-space-needed current-available-size)
        all-directories-list    (total-size-of-directories-list filesystem "")
        directory-candidates    (filter #(>= (:dir-size %) space-needed) all-directories-list)]
    (reduce (fn [acc item]
              (min acc (:dir-size item))) total-disk-space directory-candidates)))