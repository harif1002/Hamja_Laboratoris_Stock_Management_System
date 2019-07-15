/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.FeedbackService;
import info.javaknowledge.project36.model.Feedback;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertFeedback(Feedback f) {
    Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.save(f);
        t.commit();
        s.close();
        return null;    }

    @Override
    public String updateFeedback(Feedback f) {
 Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.update(f);
        t.commit();
        s.close();
        return null;    }

    @Override
    public String deleteFeedback(String feedbackid) {
Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Feedback e = (Feedback) s.get(Feedback.class, feedbackid);
        s.delete(e);
        t.commit();
        s.close();

        return null;    }

    @Override
    public String viewFeedback() {
Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        List<Feedback> feedbacklist = s.createQuery("from Feedback").list();
        Gson g = new Gson();
        String feedbacklistgson = g.toJson(feedbacklist);
        t.commit();
        s.close();
        return feedbacklistgson;    }

    @Override
    public Feedback viewOneFeedback(String feedbackid) {
 Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Feedback e = (Feedback) s.get(Feedback.class, feedbackid);
        t.commit();
        s.close();
        Gson g = new Gson();
        String feedbackgson = g.toJson(e);
        return e;    }

}
