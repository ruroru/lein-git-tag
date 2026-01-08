(defproject org.clojars.jj/lein-git-tag "1.0.1-SNAPSHOT"
  :description "Leiningen plugin to add version git tag"
  :url "https://github.com/ruroru/lein-git-tag"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.eclipse.jgit/org.eclipse.jgit "7.4.0.202509020913-r"]]
  :repl-options {:init-ns lein-git-tag.core}

  :deploy-repositories [["clojars" {:url      "https://repo.clojars.org"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass}]]

  :plugins [[org.clojars.jj/strict-check "1.1.0"]
            [org.clojars.jj/bump "1.0.4"]
            [org.clojars.jj/bump-md "1.1.0"]
            [org.clojars.jj/lein-git-tag "1.0.0"]
            ]

  )
