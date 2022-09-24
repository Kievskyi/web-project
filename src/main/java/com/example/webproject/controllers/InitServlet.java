package com.example.webproject.controllers;


import com.example.webproject.models.Step;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/initServlet")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String logicServletURL = "/logic";
        HttpSession session = request.getSession();
        String usernameFromRequest = request.getParameter("username");
        session.setAttribute("username", usernameFromRequest);
        String countOfGames = null;

        Cookie [] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("countOfGames")) {
                countOfGames = cookie.getValue();
            }
        }

        if (countOfGames == null) {
            countOfGames = "0";
            Cookie countOfGamesCookie = new Cookie("countOfGames", countOfGames);
            countOfGamesCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            countOfGamesCookie.setPath("/");
            response.addCookie(countOfGamesCookie);
            session.setAttribute("countOfGames", countOfGames);
        } else  {
            session.setAttribute("countOfGames", countOfGames);
        }

        Step currentStep = extractStepFromSession(session);
        session.setAttribute("step", currentStep);

        response.sendRedirect(logicServletURL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private int extractCountOfGames(HttpSession session) {
        Integer countOfGames = (Integer) session.getAttribute("countOfGames");
        return countOfGames == null ? 0 : countOfGames;
    }

    private Step extractStepFromSession(HttpSession session) {
        Step currentStep = (Step) session.getAttribute("step");

        if (currentStep != Step.FIRST) {
            currentStep = Step.FIRST;
        }
        return currentStep;
    }
}