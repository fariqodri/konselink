package models

import "github.com/jinzhu/gorm"

type ArticleCategory struct {
	gorm.Model
	CategoryID uint
	ArticleID  uint
}
