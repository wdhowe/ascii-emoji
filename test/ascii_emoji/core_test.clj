(ns ascii-emoji.core-test
  (:require [clojure.test :refer :all]
            [ascii-emoji.core :as emoji]
            [clojure.string :as string]))

(def faux-emoji-db
  "A test emoji-db"
  {:dudes {:description "ASCII dudes performing actions, such as flipping a table."
           :shrugdude1 "¯\\_(ツ)_/¯"
           :tableflip1 "(╯°□°）╯︵ ┻━┻"
           :tableflip2 "(┛◉Д◉)┛彡┻━┻"}
   :symbols {:description "Symbols such as copyright."
             :check1 "✔"
             :cloud1 "☁"
             :copy1 "©"}})

(deftest test-names
  (testing "Verify names listing."
    (with-redefs [emoji/emoji-db faux-emoji-db]
      (are [func result] (= func result)
      ;; all category names
        (count (emoji/names)) 2
        (some #(= :dudes %) (emoji/names)) true
      ;; category exists
        (count (emoji/names :dudes)) 3
        (some #(= :tableflip1 %) (emoji/names :dudes)) true
      ;; category does not exist
        (count (emoji/names :nothing)) 0
        (some #(= :none %) (emoji/names :nothing)) nil))))

(deftest test-describe
  (testing "Verify descriptions."
    (with-redefs [emoji/emoji-db faux-emoji-db]
      (are [func result] (= func result)
      ;; all descriptions
        (count (emoji/describe)) 2
        (contains? (emoji/describe) :dudes) true
        (contains? (emoji/describe) :symbols) true
      ;; category exists
        (> (count (:dudes (emoji/describe :dudes))) 0) true
        (string/includes? (str (emoji/describe :dudes)) "dudes performing actions") true
      ;; category does not exist
        (count (:nothing (emoji/describe :nothing))) 0
        (string/includes? (str (emoji/describe :nothing)) "some description") false))))

(deftest test-show
  (testing "Verify showing emoji strings."
    (with-redefs [emoji/emoji-db faux-emoji-db]
      (are [func result] (= func result)
      ;; all categories, exists
        (not= nil (emoji/show :tableflip1)) true
        (not= nil (emoji/show "tableflip2")) true
      ;; all categories, does not exist
        (emoji/show :nothing) nil
      ;; specific category, exists
        (not= nil (emoji/show :tableflip1 :dudes)) true
        (not= nil (emoji/show "tableflip2" :dudes)) true
      ;; specific category, does not exist
        (emoji/show :backflip :dudes) nil))))

(deftest test-show-all
  (testing "Verify showing all emojis."
    (with-redefs [emoji/emoji-db faux-emoji-db]
      (are [func result] (= func result)
      ;; all emojis from the db
        (contains? (emoji/show-all) :dudes) true
        (contains? (emoji/show-all) :symbols) true
      ;; all emojis from a category
        (contains? (emoji/show-all :dudes) :tableflip1) true
        (contains? (emoji/show-all :symbols) :copy1) true
      ;; all emojis from a category that does not exist
        (emoji/show-all :nothing) nil))))

(deftest test-search
  (testing "Verify searching the emoji-db."
    (with-redefs [emoji/emoji-db faux-emoji-db]
      (are [func result] (= func result)
      ;; search entire db
        (count (emoji/search "table")) 4
        (count (emoji/search "shrug")) 2
        (count (emoji/search "nothing")) 0
      ;; search specific category
        (count (emoji/search "table" :dudes)) 4
        (count (emoji/search "shrug" :dudes)) 2
        (count (emoji/search "┻━┻" :dudes)) 4
      ;; search category that does not exist
        (count (emoji/search "table" :nothing)) 0))))
