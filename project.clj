(defproject ascii-emoji "0.1.0"
  
  ;;; Project Metadata
  :description "Use ASCII Emoji, in Clojure."
  :url "https://github.com/wdhowe/ascii-emoji"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  
  ;;; Dependencies, Plugins
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :plugins [[lein-codox "0.10.7"]]
  
  ;;; Running Project Code
  :repl-options {:init-ns ascii-emoji.core}
  :resource-paths ["resources"])
