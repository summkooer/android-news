package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

//observable ui state: liveData<NewsResponse>
public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    //event
    public void setCountryInput(String country) {
        //data change logic
        countryInput.setValue(country);
    }

    //event
    public void setFavoriteArticleInput(Article article) {

        repository.favoriteArticle(article);
    }


    public LiveData<NewsResponse> getTopHeadlines() {
        //country input livedata -> switch/translate -> top headline livedata
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
        //:: call function
    }
}

