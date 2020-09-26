(ns ascii-emoji.core
  "ASCII Emoji core emoji-db and helper functions."
  (:gen-class)
  (:require [ascii-emoji.config :as config]
            [clojure.string :as string]))

(def emoji-db
  "A map of ascii emojis. Data shape is:
   {:category1 {:description 'goes here.'
                :name1 'emoji-here'
                :name2 'emoji-here'}
    :category2 ...}"
  (config/build-db))

(comment
  emoji-db)

(defn names
  "Get a list of emoji category names (no args) or
   get a list of emoji names from a given `category`.
   Returns a seq of keywords."
  ([]
   (keys emoji-db))
  ([category]
   (let [em-keys (keys ((keyword category) emoji-db))]
     (remove #(= % :description) em-keys))))

(comment
  (names) ;names of categories
  (names :dudes) ;names of emojis from :dudes category
  (names :nothing)) ;category does not exist

(defn describe
  "Describe all categories (no args) or
   describe the passed in `category`.
   Returns a map of the category and description."
  ([]
   (into {} (map describe (keys emoji-db))))
  ([category]
   {(keyword category) (-> emoji-db
                           ((keyword category))
                           (:description))}))

(comment
  (describe) ;describes all categories
  (describe :dudes) ;describe :dudes category
  (describe "symbols") ;describe :symbols category
  (describe :nothing)) ;category does not exist

(defn show
  "Show the first result of the named `emoji` across all categories or
   show the named `emoji` from the specified `category`.
   Returns a string emoji or nil."
  ([emoji]
   (let [find-emoji (partial show (keyword emoji))]
     (first (keep find-emoji (keys emoji-db)))))
  ([emoji category]
   (get-in emoji-db [(keyword category) (keyword emoji)])))

(comment
  (show :tableflip1) ;string of the emoji
  (show "tableflip2") ;string of the emoji
  (show :nothing) ;does not exist
  (show "tableflip1" :dudes) ;string of the emoji from category
  (show :tableflip1 :dudes) ;string of the emoji from category
  (show "tableflip" :dudes)) ;doest not exist

(defn show-all
  "Show all of the emojis from the emoji-db (no args) or
   show the emojis from the specified `category`.
   Returns a map."
  ([]
   emoji-db)
  ([category]
   ((keyword category) emoji-db)))

(comment
  (show-all) ;all emojis
  (show-all :dudes)) ;all emojis from a category

(defn search
  "Search the entire emoji-db for the named `emoji` or
   search for the named `emoji` in the specified `category`.
   Returns a seq of results."
  ([emoji]
   (let [search-all (partial search (name emoji))]
     (flatten (keep search-all (keys emoji-db)))))
  ([emoji category]
   (flatten (filter #(string/includes? % (name emoji))
                    (-> emoji-db
                        ((keyword category))
                        (dissoc :description))))))

(comment
  (search "table" :dudes) ;multiple results
  (search "shrug" :dudes) ;single result
  (search "nothing" :dudes) ;no result
  (search "┻━┻" :dudes) ;searching for a part of the emoji itself
  (search "shrug") ;search all categories
  (search "table")) ;search all categories
