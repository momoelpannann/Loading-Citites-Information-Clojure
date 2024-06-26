;;Mohamed Elpannann 40251343
;; Assignment 3 348 Summer 2024
;; This is the main entry point of the program. It loads the city data from the "cities.txt" file
;; and starts the main menu interface by calling the `main-menu` function from the `menu` namespace.
(ns app
  (:require [db]
            [menu]))

;; Load the city data and start the main menu.
(def cities-db (db/load-data "cities.txt"))

(menu/main-menu cities-db)
