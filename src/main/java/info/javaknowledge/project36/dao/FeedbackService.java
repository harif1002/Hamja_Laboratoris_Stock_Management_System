/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.dao;

import info.javaknowledge.project36.model.Feedback;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public interface FeedbackService {
    public String insertFeedback(Feedback f);

    public String updateFeedback(Feedback f);

    public String deleteFeedback(String feedbackid);

    public String viewFeedback();

    public Feedback viewOneFeedback(String feedbackid);
}
