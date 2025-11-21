(ns leiningen.git-tag
  (:import (java.io File)
           (org.eclipse.jgit.api Git TagCommand)))

(defn git-tag
  "Creates and pushes a git tag based on project version"
  [project & args]
  (let [current-dir-file (File. ".")
        git (Git/open current-dir-file)
        version (:version project)
        tag-name (format "v%s" version)]
    (try
      (let [tag-command ^TagCommand (.tag git)]
        (.setName tag-command tag-name)
        (.call tag-command))

      (-> (.push git)
          (.setRemote "origin")
          (.add tag-name)
          (.call))

      (catch Exception e
        (println (format "Error tagging: %s" (.getMessage e)))))))