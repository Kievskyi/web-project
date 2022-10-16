package com.example.webproject.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InitServletTest {
    private InitServlet mockServlet;

    @BeforeEach
    void init() {
        mockServlet = spy(InitServlet.class);
    }


    @Test
    void ifNewPlayer_ThenCreateNewCookie() throws IOException {
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        Cookie[] cookieContainer = new Cookie[1];
        Cookie emptyCookie = new Cookie("test", "test");
        cookieContainer[0] = emptyCookie;
        when(mockRequest.getCookies()).thenReturn(cookieContainer);
        when(mockRequest.getSession()).thenReturn(mockSession);

        mockServlet.doGet(mockRequest, mockResponse);

        verify(mockResponse, times(1)).addCookie(any());
    }

    @Test
    void checkTheCorrectParamsOfNewCookies() throws IOException {
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        Cookie[] cookieContainer = new Cookie[1];
        Cookie emptyCookie = new Cookie("countOfGames", "0");
        emptyCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
        emptyCookie.setPath("/");
        cookieContainer[0] = emptyCookie;
        when(mockRequest.getCookies()).thenReturn(cookieContainer);
        when(mockRequest.getSession()).thenReturn(mockSession);


        mockServlet.doGet(mockRequest, mockResponse);

        Cookie cookie = extractCookies(mockRequest, "countOfGames");

        assertEquals("0", cookie.getValue());
        assertEquals("/", cookie.getPath());
        assertEquals(31104000, cookie.getMaxAge());
    }

    @Test
    void ifPlayerIsNotNew_ThenNewCookieWontBeCreated() throws IOException {
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        Cookie[] cookieContainer = new Cookie[1];
        Cookie emptyCookie = new Cookie("countOfGames", "test");
        cookieContainer[0] = emptyCookie;
        when(mockRequest.getCookies()).thenReturn(cookieContainer);
        when(mockRequest.getSession()).thenReturn(mockSession);

        mockServlet.doGet(mockRequest, mockResponse);

        verify(mockResponse, never()).addCookie(any());
    }

    @Test
    void whenCallDoPost_ThenCallDoGet() throws IOException {
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        Cookie[] cookieContainer = new Cookie[1];
        Cookie emptyCookie = new Cookie("countOfGames", "test");
        cookieContainer[0] = emptyCookie;

        when(mockRequest.getCookies()).thenReturn(cookieContainer);
        when(mockRequest.getSession()).thenReturn(mockSession);

        mockServlet.doPost(mockRequest, mockResponse);
        verify(mockServlet).doGet(mockRequest,mockResponse);
    }


    @AfterEach
    void destroy() {
        mockServlet = null;
    }

    Cookie extractCookies(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                cookie = c;
            }
        }
        return cookie;
    }
}