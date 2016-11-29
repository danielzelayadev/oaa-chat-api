(ns api.utils)
(require '[clj-time.core :as t])

(def email-regex #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
(def password-regex #"(?=.*[A-Z])(?=.*\d.*\d)[A-Za-z\d]{8,}")
(def username-regex #".{4,}")

(defn mand [& vals]
  "Returns the first map or the last value."
  (loop [[val & remaining] vals]
    (if (or (map? val) (empty? remaining))
      val
      (recur remaining))))

(defn res [status body]
  {:status status :body body})

(defn success [body] (res 200 body))
(defn bad-request [body] (res 400 body))
(defn unauthorized [body] (res 401 body))
(defn forbidden [body] (res 403 body))
(defn not-found [body] (res 404 body))
(defn server-err [body] (res 500 body))


(defn age [bday]
  "Calculate your age based on the DateTime bday."
  (let [today (t/today)
        bd (t/day bday)
        bm (t/month bday)
        by (t/year bday)
        td (t/day today)
        tm (t/day today)
        ty (t/day today)
        est (- ty by)]
    (if (or (> tm bm) (and (= tm bm) (>= td bd)))
      est
      (dec est))))