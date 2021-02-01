package com.nayan.sampleproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nayan.sampleproject.model.Example;
import com.nayan.sampleproject.repository.DocRepository;

public class DocViewModel extends AndroidViewModel {
    private DocRepository docRepository;
    private LiveData<Example> exampleLiveData;

    public DocViewModel(@NonNull Application application) {
        super(application);

        docRepository=new DocRepository();
        this.exampleLiveData=docRepository.getResponse();
    }

    public LiveData<Example> getExampleLiveData(){
        return exampleLiveData;
    }
}
