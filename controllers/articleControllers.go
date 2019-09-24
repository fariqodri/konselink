package controllers

import (
	"encoding/json"
	"fmt"
	"konselink/models"
	u "konselink/utils"
	"net/http"
	"strconv"
	"strings"
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

var UpdateArticle = func(w http.ResponseWriter, r *http.Request) {
	urlPath := r.URL.Path
	sArticleID := strings.Split(urlPath, "/")[3]

	articleID, err := strconv.Atoi(sArticleID)
	if err != nil {
		u.Respond(w, u.Message(false, "Error"))
		return
	}
	article := &models.Article{}
	err = json.NewDecoder(r.Body).Decode(article) //decode the request body into struct and failed if any error occur
	if err != nil {
		fmt.Println(err.Error())
		u.Respond(w, u.Message(false, "Invalid request"))
		return
	}
	resp := article.Update(uint(articleID)) //Create article
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

var GetArticle = func(w http.ResponseWriter, r *http.Request) {
	urlPath := r.URL.Path
	sArticleID := strings.Split(urlPath, "/")[3]

	articleID, err := strconv.Atoi(sArticleID)
	if err != nil {
		u.Respond(w, u.Message(false, "Error"))
		return
	}
	articles := &models.Article{}
	resp := articles.Get(uint(articleID)) //Get article
	u.Respond(w, resp)
}

var DeleteArticle =  func(w http.ResponseWriter, r *http.Request) {
	urlPath := r.URL.Path
	sArticleID := strings.Split(urlPath, "/")[3]

	articleID, err := strconv.Atoi(sArticleID)
	if err != nil {
		u.Respond(w, u.Message(false, "Error"))
		return
	}
	articles := &models.Article{}
	resp := articles.Delete(uint(articleID)) //Get article
	u.Respond(w, resp)
}