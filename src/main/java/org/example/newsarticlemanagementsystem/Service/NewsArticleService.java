package org.example.newsarticlemanagementsystem.Service;

import org.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticleArrayList = new ArrayList<>();


    public ArrayList<NewsArticle> getAllNewsArticles(){
        return newsArticleArrayList;
    }

    public NewsArticle getArticleById(String id){
        for (NewsArticle newsArticle: newsArticleArrayList){
            if (newsArticle.getId().equalsIgnoreCase(id)){
                return newsArticle;
            }
        }
        return null;
    }

    public void addArticle(NewsArticle newsArticle){
        newsArticle.setPublished(false);
        newsArticle.setPublishDate(LocalDate.ofEpochDay(-1970));    //default date: 1964-08-10
        newsArticleArrayList.add(newsArticle);
    }

    public boolean updateArticle(String id, NewsArticle newsArticle){
        for (NewsArticle newsArticleLoopVar : newsArticleArrayList){
            if (newsArticleLoopVar.getId().equalsIgnoreCase(id)){
                newsArticleArrayList.set(newsArticleArrayList.indexOf(newsArticleLoopVar),newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteArticle(String id){
        for (NewsArticle newsArticle: newsArticleArrayList){
            if (newsArticle.getId().equalsIgnoreCase(id)){
                newsArticleArrayList.remove(newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean publishArticle(String id){
        for (NewsArticle newsArticle: newsArticleArrayList){
            if (newsArticle.getId().equalsIgnoreCase(id)){
                newsArticle.setPublished(true);
                newsArticle.setPublishDate(LocalDate.now());
                return true;
            }
        }
        return false;
    }

    //using java stream
    public ArrayList<NewsArticle> getPublishedArticles(){
        ArrayList<NewsArticle> filteredArray = newsArticleArrayList.stream()
                .filter(newsArticle -> newsArticle.isPublished())
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredArray.isEmpty()){
            return null;
        }
        return filteredArray;
    }

    public ArrayList<NewsArticle> getArticleByCategory(String category){
        ArrayList<NewsArticle> filteredArray = newsArticleArrayList.stream()
                .filter(newsArticle -> newsArticle.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredArray.isEmpty()){
            return null;
        }
        return filteredArray;
    }


}
