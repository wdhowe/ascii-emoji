(ns ascii-emoji.config
  "ASCII Emoji configuration and data loaders."
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn file-list
  "Return a vector of edn file names containing ascii emojis."
  []
  ["data/dudes.edn"
   "data/objects.edn"
   "data/symbols.edn"])

(comment
  (file-list))

(defn full-paths
  "Determine the full file paths given a sequence of `files` names."
  [files]
  (->> files
       (map io/resource)
       (map str)))

(comment
  (full-paths (file-list))
  (full-paths []))

(defn load-edn
  "Load edn files from an io/reader `source` (filename or io/resource).
   Orig fn: https://clojuredocs.org/clojure.edn/read#example-5a68f384e4b09621d9f53a79"
  [source]
  (try
    (with-open [r (io/reader source)]
      (edn/read (java.io.PushbackReader. r)))

    (catch java.io.IOException e
      (printf "Couldn't open '%s': %s\n" source (.getMessage e)))
    (catch RuntimeException e
      (printf "Error parsing edn file '%s': %s\n" source (.getMessage e)))))

(comment
  (load-edn (io/resource "data/dudes.edn")) ;load the dudes.edn file
  (load-edn (io/resource "path/doesnt/exist/file.edn"))) ;file does not exist (nil)

(defn build-db
  "Build the emoji-db. Load all edn data files into a map.
   Use default project file-list or
   specify the `files` as a sequence."
  ([]
   (build-db (file-list)))
  ([files]
   (let [edn-list (full-paths files)]
     (into {} (map load-edn edn-list)))))

(comment
  (build-db))
