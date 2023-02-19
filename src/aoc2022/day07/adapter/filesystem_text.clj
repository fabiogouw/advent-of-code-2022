(ns aoc2022.day07.adapter.filesystem-text
  (:require [clojure.string :as str]))

(defn- root-dir [filesystem-walker line]
  {:filesystem {:name "/" :items []}
   :current-location []})

(defn- dir-entered [filesystem-walker line]
  (let [dir-name (str/replace line "$ cd " "")]
    (update-in filesystem-walker [:current-location] conj dir-name)))

(defn- dir-exited [filesystem-walker line]
  (update-in filesystem-walker [:current-location] (comp vec drop-last)))

(defn- add-into [root-directory item address]
  (loop [directory  root-directory
         address    address
         update-ks  [:items]]
    (if (empty? address)
      (update-in root-directory update-ks conj item)
      (let [address-part (first address)
            [[index directory]] (keep-indexed (fn [index value]
                                              (if (= (:name value) address-part) [index value]))
                                            (:items directory))
            update-ks (concat update-ks [index :items])]
        (recur directory (rest address) update-ks)))))

(defn- dir-added [filesystem-walker line]
  (let [dir-name  (str/replace line "dir " "")
        address   (:current-location filesystem-walker)]
    (update-in filesystem-walker [:filesystem] add-into {:name dir-name :items []} address)))

(defn- file-added [filesystem-walker line]
  (let [parts  (str/split line #" ")
        size   (-> parts first bigint int)
        file-name (second parts)
        address   (:current-location filesystem-walker)]
    (update-in filesystem-walker [:filesystem] add-into {:name file-name :size size} address)))

(defn- starts-with-number? [line]
  (> (count (re-matches #"^\d.*" line)) 0))

(defn text->filesystem-structure [input]
  (loop [lines              (str/split-lines input)
         filesystem-walker  {:filesystem {}
                             :current-location []}]
    (if (empty? lines)
        (:filesystem filesystem-walker)
      (let [current-line (first lines)
            what-to-do (cond
                         (str/starts-with? current-line "$ cd /")   root-dir
                         (str/starts-with? current-line "$ cd ..")  dir-exited
                         (str/starts-with? current-line "$ cd")     dir-entered
                         (str/starts-with? current-line "dir")      dir-added
                         (starts-with-number? current-line)         file-added
                         :else (fn [a b] a))] ; $ ls is ignored
        (let [filesystem-walker (what-to-do filesystem-walker current-line)]
          (recur (rest lines) filesystem-walker))))))