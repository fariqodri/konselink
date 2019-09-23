package controllers

import (
	"encoding/json"
	"fmt"
	"konselink/models"
	u "konselink/utils"
	"net/http"
)

var CreateArticle = func(w http.ResponseWriter, r *http.Request) {

	article := &models.Article{}
	err := json.NewDecoder(r.Body).Decode(article) //decode the request body into struct and failed if any error occur
	if err != nil {
		fmt.Println(err.Error())
		u.Respond(w, u.Message(false, "Invalid request"))
		return
	}

	resp := article.Create() //Create article
	u.Respond(w, resp)
}

var ListArticles = func(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()

	category := queryValues.Get("category")
	findAllArticleParams := &models.FindAllArticleParams{Category:category}
	article := &models.Article{}
	data := article.ListArticles(findAllArticleParams) //Get account
	resp := u.Message(true, "success")
	resp["data"] = data
	u.Respond(w, resp)
}