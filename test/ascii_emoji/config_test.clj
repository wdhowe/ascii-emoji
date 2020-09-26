(ns ascii-emoji.config-test
  (:require [clojure.test :refer :all]
            [ascii-emoji.config :as config]))

(deftest test-file-list
  (testing "Verify listing of test files."
    (let [edn-files (config/file-list "test/ascii_emoji/data/" ".edn")
          exe-files (config/file-list "test/ascii_emoji/data/" ".exe")]
      ;; existing files
      (is (= 2 (count edn-files)))
      (some #{"src/ascii_emoji/data/symbols.edn"} edn-files)
      ;; files don't exist
      (is (= 0 (count exe-files)))
      (is (empty? exe-files)))))

(deftest test-load-edn
  (testing "Verify edn file loading."
    (let [file (config/load-edn "test/ascii_emoji/data/dudes.edn")
          nofile (config/load-edn "test/ascii_emoji/data/none.edn")]
      ;; file exists
      (is (contains? file :dudes))
      (is (contains? (:dudes file) :shrugdude1))
      ;; file does not exist
      (is (= nil nofile)))))

(deftest test-build-db
  (testing "Verify building the mapped db."
    (let [db (config/build-db)]
      (is (contains? db :dudes))
      (is (contains? (:dudes db) :shrugdude1))
      (is (contains? db :symbols))
      (is (contains? (:symbols db) :copy1))
      (is (= "(╯°□°）╯︵ ┻━┻" (get-in db [:dudes :tableflip1]))))))
