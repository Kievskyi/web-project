package com.example.webproject.controllers;

import com.example.webproject.models.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerServletTest {
    private ControllerServlet mockServlet;

    @BeforeEach
    void init() {
        mockServlet = new ControllerServlet();
    }

    @Test
    void whenGameIsStarted_ThenStartFromFirstRound() throws ServletException, IOException {
        final String questPageURL = "/questPage.jsp";
        final String loosePageURL = "/loosePage.jsp";
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);


        when(request.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(context.getRequestDispatcher(loosePageURL)).thenReturn(dispatcher);
        when(context.getAttribute("isLastStep")).thenReturn("no");
        when(request.getParameter("choice")).thenReturn("1");
        when(session.getAttribute("step")).thenReturn(Step.FIRST);
        when(request.getSession()).thenReturn(session);

        mockServlet.doGet(request, response);

        HttpSession resultSession = request.getSession();
        Step expected = Step.FIRST;
        Step actual = (Step) resultSession.getAttribute("step");

        assertEquals(expected, actual);
    }

    @Test
    void ifRightAnswer_ThenNextStep() throws ServletException, IOException {
        final String questPageURL = "/questPage.jsp";
        final String loosePageURL = "/loosePage.jsp";
        final String winPageURL = "/winPage.jsp";
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(context.getRequestDispatcher(loosePageURL)).thenReturn(dispatcher);
        when(context.getRequestDispatcher(winPageURL)).thenReturn(dispatcher);
        when(context.getAttribute("isLastStep")).thenReturn("no");
        when(request.getParameter("choice")).thenReturn("2");
        when(session.getAttribute("step")).thenReturn(Step.FIRST);
        when(request.getSession()).thenReturn(session);

        mockServlet.doGet(request, response);

        verify(session).setAttribute("step", Step.SECOND);
    }

    @Test
    void ifWrongAnswer_ThenForwardToLoosePage() throws ServletException, IOException {
        final String questPageURL = "/questPage.jsp";
        final String loosePageURL = "/loosePage.jsp";
        final String winPageURL = "/winPage.jsp";
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(context.getRequestDispatcher(loosePageURL)).thenReturn(dispatcher);
        when(context.getAttribute("isLastStep")).thenReturn("no");
        when(request.getParameter("choice")).thenReturn("1");
        when(session.getAttribute("step")).thenReturn(Step.FIRST);
        when(request.getSession()).thenReturn(session);

        mockServlet.doGet(request, response);

        verify(dispatcher, atLeast(1)).forward(request, response);
    }

    @Test
    void ifLastStepAndRightAnswer_ThenForwardToWinPage() throws ServletException, IOException {
        final String questPageURL = "/questPage.jsp";
        final String loosePageURL = "/loosePage.jsp";
        final String winPageURL = "/winPage.jsp";
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(questPageURL)).thenReturn(dispatcher);
        when(context.getRequestDispatcher(winPageURL)).thenReturn(dispatcher);
        when(context.getAttribute("isLastStep")).thenReturn("yes");
        when(request.getParameter("choice")).thenReturn("2");
        when(session.getAttribute("step")).thenReturn(Step.THIRD);
        when(request.getSession()).thenReturn(session);

        mockServlet.doGet(request, response);

        verify(dispatcher, atLeast(1)).forward(request, response);
    }



    @AfterEach
    void destroy() {
        mockServlet = null;
    }
}