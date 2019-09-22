package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"konselink/app"
	"konselink/controllers"
	"net/http"
	"os"
)

func main() {

	router := mux.NewRouter()

	router.Use(app.JwtAuthentication) //attach JWT auth middleware

	router.HandleFunc("/api/users", controllers.CreateAccount).Methods("POST")
	router.HandleFunc("/api/users", controllers.UpdateAccount).Methods("PUT")
	router.HandleFunc("/api/users/login", controllers.Authenticate).Methods("POST")



	//router.NotFoundHandler = app.NotFoundHandler

	port := os.Getenv("PORT")
	if port == "" {
		port = "8000" //localhost
	}

	fmt.Println(port)

	err := http.ListenAndServe(":" + port, router) //Launch the app, visit localhost:8000/api
	if err != nil {
		fmt.Print(err)
	}
}