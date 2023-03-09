package service;

import Domain.Question;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Question> getAll(){return repo.getQuestions();}

    public void ReadfromDB() {repo.readFromDB();}
    public ArrayList<Question> sortbyscore()
    {
        return repo.getQuestions()
                .stream()
                .sorted(Comparator.comparing(o->o.getScore()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Question> filter(String searchtext, int givenscore)
    {
        return repo.getQuestions()
                .stream()
                .filter(question -> question.getText().contains(searchtext) && question.getScore()>givenscore)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteQuestion(Question q) {repo.remove(q);}

}
