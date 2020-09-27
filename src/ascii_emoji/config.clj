(ns ascii-emoji.config
  "ASCII Emoji configuration and data loaders."
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(defn file-list
  "Return a lazy-seq of files from `file-path` that end in `file-ext`."
  [file-path file-ext]
  (->> (io/file file-path)
       (file-seq)
       (map str)
       (filter #(string/ends-with? % file-ext))))

(comment
  (file-list (io/resource "data") ".edn") ;list of files ending in '.edn'
  (file-list (io/resource "data") ".exe")) ;list of files ending in '.exe' (don't exist)

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
   Use default project file-path and file-ext or
   specify the `file-path` and file-ext`"
  ([]
   (build-db (io/resource "data") ".edn"))
  ([file-path file-ext]
   (let [edn-list (file-list file-path file-ext)]
     (into {} (map load-edn edn-list)))))

(comment
  (build-db))
