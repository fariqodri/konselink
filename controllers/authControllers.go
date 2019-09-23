package controllers

import (
	"encoding/json"
	"konselink/models"
	u "konselink/utils"
	"net/http"
)

var CreateAccount = func(w http.ResponseWriter, r *http.Request) {

	account := &models.Account{}
	err := json.NewDecoder(r.Body).Decode(account) //decode the request body into struct and failed if any error occur
	if err != nil {
		u.Respond(w, u.Message(false, "Invalid request"))
		return
	}

	resp := account.Create() //Create account
	u.Respond(w, resp)
}

var UpdateAccount = func(w http.ResponseWriter, r *http.Request) {

	userID := r.Context().Value("user") . (uint) //Grab the id of the user that send the request
	account := &models.Account{}
	err := json.NewDecoder(r.Body).Decode(account) //decode the request body into struct and failed if any error occur
	if err != nil {
		u.Respond(w, u.Message(false, "Invalid request"))
		return
	}

	resp := account.Update(userID) //Update account
	u.Respond(w, resp)
}

var GetAccount = func(w http.ResponseWriter, r *http.Request) {

	userID := r.Context().Value("user") . (uint) //Grab the id of the user that send the request
	account := &models.Account{}
	resp := account.Get(userID) //Get account
	u.Respond(w, resp)
}

var Authenticate = func(w http.ResponseWriter, r *http.Request) {

	account := &models.Account{}
	err := json.NewDecoder(r.Body).Decode(account) //decode the request body into struct and failed if any error occur
	if err != nil {
		u.Respond(w, u.Message(false, "Invalid request"))
		return
	}

	resp := models.Login(account.Email, account.Password)
	u.Respond(w, resp)
}