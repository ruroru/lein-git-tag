(ns leiningen.git-tag
  (:import (java.io File)
           (org.eclipse.jgit.api Git TagCommand)
           (org.eclipse.jgit.transport UsernamePasswordCredentialsProvider)))

(defn git-tag
  "Creates and pushes a git tag based on project version"
  [project & args]
  (let [current-dir-file (File. ".")
        git (Git/open current-dir-file)
        version (:version project)
        tag-name (format "v%s" version)
        github-token (System/getenv "GITHUB_TOKEN")]

    (try
      (let [tag-command ^TagCommand (.tag git)]
        (.setName tag-command tag-name)
        (.setForceUpdate tag-command true)
        (.call tag-command))

      (println (format "Created tag: %s" tag-name))

      (let [credentials (UsernamePasswordCredentialsProvider.
                          "x-access-token"
                          github-token)]
        (-> (.push git)
            (.setRemote "origin")
            (.add tag-name)
            (.setForce true)
            (.setCredentialsProvider credentials)
            (.call)))

      (println (format "Pushed tag: %s" tag-name))

      (catch Exception e
        (println (format "Error tagging: %s" (.getMessage e)))))))