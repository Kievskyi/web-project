package com.example.webproject.controllers;

import com.example.webproject.models.AnswerOptions;
import com.example.webproject.models.Choose;
import com.example.webproject.models.Questions;
import com.example.webproject.models.Step;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ControllerServlet", value = "/logic")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String questPageURL = "/questPage.jsp";
        final String winPageURL = "/winPage.jsp";
        final String loosePageURL = "/loosePage.jsp";
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        AnswerOptions answers = new AnswerOptions();
        Questions questions = new Questions();
        String questionForCurrentStep;
        List<String> answersForCurrentStep;
        int usersChoice = convertChoiceToInteger(request);
        Step currentStep ;

        if (usersChoice == 0) {
            currentStep = (Step) session.getAttribute("step");
        } else {
            currentStep = (Step) session.getAttribute("step");
            currentStep = currentStep.next();
        }

        questionForCurrentStep = questions.getQuestionsForCurrentStep(currentStep);
        answersForCurrentStep = answers.getAnswersForCurrentStep(currentStep);
        session.setAttribute("answers", answersForCurrentStep);
        session.setAttribute("question", questionForCurrentStep);

        if (usersChoice == 0) {
            RequestDispatcher dispatcher = context.getRequestDispatcher(questPageURL);
            dispatcher.forward(request, response);
        }

        String isLastStep = (String) context.getAttribute("isLastStep");
        if (isLastStep.equals("yes") && checkWin(currentStep, answers.getAllAnswersFromMap(), answersForCurrentStep.get(usersChoice - 1)) == Choose.WIN) {
            RequestDispatcher dispatcher = context.getRequestDispatcher(winPageURL);
            dispatcher.forward(request, response);
        }

        if (checkWin(currentStep, answers.getAllAnswersFromMap(), answersForCurrentStep.get(usersChoice - 1)) == Choose.LOOSE) {
            RequestDispatcher dispatcher = context.getRequestDispatcher(loosePageURL);
            dispatcher.forward(request, response);
        }

        session.setAttribute("step", currentStep);

        RequestDispatcher dispatcher = context.getRequestDispatcher(questPageURL);
        dispatcher.forward(request, response);
    }

    private int convertChoiceToInteger(HttpServletRequest request) {
        String choice = request.getParameter("choice");

        if (choice == null) {
            return 0;
        }
        boolean isNumeric = choice.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(choice) : 0;
    }

    private Choose checkWin(Step step, Map<Step, Map<Choose, String>> answersMap, String answer) {

        Choose isWin = null;

        for (Map.Entry<Step, Map<Choose, String>> answerFromMap : answersMap.entrySet()) {
            if (answerFromMap.getKey() == step) {
                Map<Choose, String> map = answerFromMap.getValue();
                for (Map.Entry<Choose, String> map1 : map.entrySet()) {
                    if (map1.getValue().equals(answer)) {
                        if (map1.getKey() == Choose.WIN) {
                            isWin = Choose.WIN;
                        } else {
                            isWin = Choose.LOOSE;
                        }
                    }
                }
            }
        }
        return isWin;
    }
}