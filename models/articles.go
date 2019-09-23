package models

import (
	"github.com/jinzhu/gorm"
	u "konselink/utils"
)

//a struct to rep article
type Article struct {
	gorm.Model
	Title string `json:"title"`
	Writer string `json:"writer"`
	Thumbnail string `json:"thumbnail"`
	Banner string `json:"banner"`
	Content string `json:"content"`
	Categories []string `json:"category" gorm:"-"`
}

type FindAllArticleParams struct {
	Category string
}

// Checks if categories is one of the five
func (article *Article) Validate() (map[string] interface{}, bool) {
	var categories []Category
	GetDB().Find(&categories)
	var catArr []string
	for _, elem := range categories {
		catArr = append(catArr, elem.Name)
	}
	for _, elem := range article.Categories {
		if !Contains(catArr, elem) {
			return u.Message(false, "Invalid Category"), false
		}
	}
	return u.Message(false, "Requirement passed"), true
}

func (article *Article) Create() map[string] interface{} {
	if resp, ok := article.Validate(); !ok {
		return resp
	}
	GetDB().Create(article)

	if article.ID <= 0 {
		return u.Message(false, "Failed to create account, connection error.")
	}

	for _, elem := range article.Categories{
		category := &Category{}
		err := GetDB().Table("categories").Where("name = ?", elem).First(category).Error
		if err != nil && err != gorm.ErrRecordNotFound {
			return u.Message(false, "Connection db error in category. Please retry")
		}
		articleCategoryMap := &ArticleCategory{
			CategoryID: category.ID,
			ArticleID:  article.ID,
		}
		GetDB().Create(articleCategoryMap)

	}

	response := u.Message(true, "Article has been created")
	response["article"] = article
	return response
}

func (article *Article) ListArticles(params *FindAllArticleParams) []*Article {
	categoryName := params.Category
	var articles []*Article
	if categoryName == "" {
		GetDB().Find(&articles)
	} else {
		// Find all articles with the filtered category
		GetDB().Joins("JOIN article_categories on article_categories.article_id=articles.id").
			Joins("JOIN categories on article_categories.category_id=categories.id").
			Where("categories.name=?",categoryName).Group("articles.id").Find(&articles)
	}

	// Find all categories of an article
	for _, singleArticle := range articles {
		var categories []*Category
		GetDB().Joins("JOIN article_categories on article_categories.category_id=categories.id").
			Joins("JOIN articles on article_categories.article_id=articles.id").
			Where("articles.id=?", singleArticle.ID).Group("categories.id").Find(&categories)
		var articleCat []string
		for _, cat :=  range categories {
			articleCat = append(articleCat, cat.Name)
		}
		singleArticle.Categories = articleCat
	}
	return articles
}

func (article *Article) Get(articleID uint) map[string]interface{} {
	err := GetDB().Table("articles").Where("id = ?", articleID).First(article).Error
	if err != nil && err != gorm.ErrRecordNotFound {
		return u.Message(false, "Connection db Article error. Please retry")
	}
	if article.ID <= 0 {
		return u.Message(false, "Failed to find Article, Not Found.")
	}
	var categories []*Category
	GetDB().Joins("JOIN article_categories on article_categories.category_id=categories.id").
		Joins("JOIN articles on article_categories.article_id=articles.id").
		Where("articles.id=?", article.ID).Group("categories.id").Find(&categories)
	var articleCat []string
	for _, cat :=  range categories {
		articleCat = append(articleCat, cat.Name)
	}
	article.Categories = articleCat
	response := u.Message(true, "Article has been found")
	response["article"] = article
	return response
}

// Contains tells whether a contains x.
func Contains(a []string, x string) bool {
	for _, n := range a {
		if x == n {
			return true
		}
	}
	return false
}