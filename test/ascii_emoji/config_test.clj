(ns ascii-emoji.config-test
  (:require [clojure.test :refer :all]
            [ascii-emoji.config :as config]
            [clojure.string :as string]))

(deftest test-full-paths
  (testing "Verify listing of test files."
    (let [edn-files (config/full-paths (config/file-list))
          no-files (config/full-paths [])]
      ;; existing files
      (is (= 3 (count edn-files)))
      (is (string/includes? (first edn-files) "dudes.edn"))
      ;; files don't exist
      (is (= 0 (count no-files)))
      (is (empty? no-files)))))

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
